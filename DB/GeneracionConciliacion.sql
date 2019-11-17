DROP TABLE LOTE_TMP_2 cascade constraints;
DROP TABLE CHEQUE_TMP_2 cascade constraints;
DROP SEQUENCE chequetmp2_sequence;
DROP SEQUENCE lotetmp2_sequence;
--------------------------------------------------------------------------------
CREATE SEQUENCE chequetmp2_sequence;
CREATE OR REPLACE TRIGGER cheque_tmp_2_on_insert
  BEFORE INSERT ON CHEQUE_TMP_2
  FOR EACH ROW
  BEGIN
    SELECT chequetmp2_sequence.nextval
    INTO :new.id_cheque
    FROM dual;
END;
/
--------------------------------------------------------------------------------
CREATE SEQUENCE lotetmp2_sequence;
CREATE OR REPLACE TRIGGER lote_tmp_2_on_insert
  BEFORE INSERT ON LOTE_TMP_2
  FOR EACH ROW
  BEGIN
    SELECT lotetmp2_sequence.nextval
    INTO :new.id_lote_2
    FROM dual;
END;
/
----------- CREACION DE TABLAS PARA LA CONCILIACION ----------------------------
CREATE TABLE LOTE_TMP_2(
    id_lote_2 INTEGER NOT NULL,
    total_documentos INTEGER DEFAULT 0,
    total_monto CUENTA.SALDO%TYPE DEFAULT 0,
    id_banco BANCO.ID_BANCO%TYPE NOT NULL,
    estado_lote INTEGER DEFAULT 1
);
ALTER TABLE LOTE_TMP_2 ADD CONSTRAINT lote_tmp_2_pk PRIMARY KEY ( id_lote_2 );
ALTER TABLE LOTE_TMP_2
    ADD CONSTRAINT lote_tmp_2_fk FOREIGN KEY ( id_banco )
        REFERENCES BANCO (id_banco)
            ON DELETE CASCADE;

CREATE TABLE CHEQUE_TMP_2(
    id_cheque INTEGER NOT NULL, -- INCREMENTAL DE CHEQUE
    fecha      DATE NOT NULL, -- FECHA EN QUE SE HIZO EL COBRO
    cuenta      INTEGER NOT NULL, -- CUENTA LOCAL A ACREDITAR A RESERVA
    valor   REAL NOT NULL, -- MONTO DEL CHEQUE
    lote    INTEGER NOT NULL, -- REFERENCIA A QUE LOTE PERTENECE
    referencia INTEGER NOT NULL, -- CUENTA EXTERNA
    correlativo INTEGER NOT NULL -- NUMERO DE CHEQUE
)
ALTER TABLE CHEQUE_TMP_2 ADD CONSTRAINT cheque_tmp_2_pk PRIMARY KEY ( id_cheque );
ALTER TABLE CHEQUE_TMP_2
    ADD CONSTRAINT cheque_tmp_2_fk FOREIGN KEY ( lote )
        REFERENCES LOTE_TMP_2 ( id_lote_2 )
            ON DELETE CASCADE;
--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE CHEQUE_EXTERNO(
    p_no_cuenta_local IN CUENTA.NO_CUENTA%TYPE,
    p_no_cuenta_externa IN CUENTA.NO_CUENTA%TYPE,
    p_correlativo_cheque IN CHEQUE.CORRELATIVO%TYPE,
    p_monto IN CUENTA.SALDO%TYPE,
    p_id_banco IN BANCO.ID_BANCO%TYPE,
    p_usuario_id_usuario IN USUARIO.ID_USUARIO%TYPE,
  	p_terminal_id_terminal IN TERMINAL.ID_TERMINAL%TYPE
)
IS
  banco_existe INTEGER;
  cuenta_existe INTEGER;
  saldo_reserva CUENTA.SALDO%TYPE;
  saldo_temp CUENTA.SALDO%TYPE;
  saldo_disponible CUENTA.SALDO%TYPE;

  lote_existe INTEGER%TYPE;
  id_lote LOTE_TMP_2.ID_lOTE_2%TYPE;

  documentos LOTE_TMP_2.TOTAL_DOCUMENTOS%TYPE;
  total LOTE_TMP_2.TOTAL_MONTO%TYPE;
BEGIN
  SELECT COUNT(*) INTO banco_existe FROM BANCO WHERE BANCO.ID_BANCO = p_id_banco;
  IF banco_existe = 1 THEN
    SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_local;
    IF cuenta_existe = 1 THEN
      SELECT CUENTA.SALDO_RESERVA, CUENTA.SALDO INTO saldo_reserva, saldo_disponible FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_local FOR UPDATE;
      saldo_temp := saldo_reserva + monto;
      -- REALIZANDO LA TRANSACCION
      INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
      VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'credito', saldo_reserva, saldo_temp, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_local, 1);
      -- REALIZANDO LA ACTUALIZACION DEL SALDO
      UPDATE CUENTA SET SALDO_RESERVA = saldo_temp, SALDO_TOTAL = (saldo_temp + saldo_disponible) WHERE CUENTA.NO_CUENTA = p_no_cuenta_local;
      -- VERIFICAR SI EXISTE UN LOTE QUE NO HA SIDO EXPORTADO PARA PODER AGERGARLO, ESTADO = O NO EXPORTADO
      SELECT COUNT(*) INTO lote_existe FROM LOTE_TMP_2 WHERE ID_BANCO = p_id_banco AND ESTADO_LOTE = 0;
      IF lote_existe = 1 THEN
        -- YA HAY UN LOTE EXISTENTE, NO EXPORTADO AL CUAL SE PUEDEN AGREGAR CHEQUES
        SELECT LOTE_TMP_2.ID_LOTE_2 INTO id_lote FROM LOTE_TMP_2 WHERE ID_BANCO = p_id_banco AND ESTADO_LOTE = 0;
      ELSE
        -- NO EXISTE, ENTONCES CREO EL LOTE Y LUEGO INSERTO EL CHEQUE
        INSERT INTO LOTE_TMP_2(ID_BANCO) VALUES(p_id_banco) RETURNING LOTE_TMP_2.ID_lOTE_2 INTO id_lote;
      END IF;
      -- PUEDO INSERTAR EL CHEQUE --
      INSERT INTO CHEQUE_TMP_2(FECHA, CUENTA, VALOR, LOTE, REFERENCIA, CORRELATIVO) VALUES(TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), p_no_cuenta_local, p_monto, id_lote, p_no_cuenta_externa, p_correlativo_cheque);
      -- CALCULO LOS DATOS DEL LOTE
      SELECT COUNT(*) INTO documentos FROM CHEQUE_TMP_2 WHERE CHEQUE_TMP_2.LOTE = id_lote; -- CONTANDO DOCUMENTOS EN EL LOTE
      SELECT SUM(VALOR) INTO total FROM CHEQUE_TMP_2 WHERE CHEQUE_TMP_2.LOTE = id_lote; -- SUMANDO EL VALOR DEL LOTE
      UPDATE LOTE_TMP_2 SET total_documentos = documentos, total_monto = total WHERE LOTE_TMP_2.ID_LOTE_2;
      COMMIT; -- GUARDANDO CAMBIOS
    ELSE
      raise_application_error(-20456, 'Cuenta especificada no existe en el sistema');
    END IF;
  ELSE
    raise_application_error(-20456, 'Banco no existe en el sistema');
  END IF;
END;
/
