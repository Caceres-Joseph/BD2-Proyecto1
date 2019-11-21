drop table lote_tmp_1 cascade constraints;
drop table cheque_tmp_1 cascade constraints;
DROP SEQUENCE chequetmp_sequence;




CREATE TABLE lote_tmp_1 (
    id_lote         INTEGER NOT NULL,
    fecha           DATE NOT NULL,
    banco           INTEGER NOT NULL,
    no_documentos   INTEGER NOT NULL,
    valor           REAL NOT NULL,
    estado          INTEGER NOT NULL
);

ALTER TABLE lote_tmp_1 ADD CONSTRAINT lote_tmp_1_pk PRIMARY KEY ( id_lote );

CREATE TABLE cheque_tmp_1 (
    id_cheque   INTEGER NOT NULL,
    fecha     DATE NOT NULL,
    cuenta      INTEGER NOT NULL,
    valor   REAL NOT NULL,
    estado  VARCHAR(32) NOT NULL,
    lote    INTEGER NOT NULL,
    referencia INTEGER NOT NULL,
    correlativo INTEGER NOT NULL
);

ALTER TABLE cheque_tmp_1 ADD CONSTRAINT cheque_tmp_1_pk PRIMARY KEY ( id_cheque );

ALTER TABLE cheque_tmp_1
    ADD CONSTRAINT cheque_tmp_1_fk FOREIGN KEY ( lote )
        REFERENCES lote_tmp_1 ( id_lote )
            ON DELETE CASCADE;


-- INSERTAR REGISTRO NUEVO LOTE////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_LOTE_TMP(
	p_id_lote IN lote_tmp_1.ID_LOTE%TYPE,
	p_id_banco IN lote_tmp_1.BANCO%TYPE,
    p_no_docs IN lote_tmp_1.NO_DOCUMENTOS%TYPE,
    p_monto IN lote_tmp_1.VALOR%TYPE,
    p_estado IN lote_tmp_1.ESTADO%TYPE
)
IS
BEGIN
	INSERT INTO lote_tmp_1(id_lote, fecha, banco, no_documentos, valor, estado) VALUES(p_id_lote, TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), p_id_banco, p_no_docs, p_monto, p_estado);
	COMMIT;
END;
/


-- INSERTAR REGISTRO NUEVO CHEQUE COMPENSADO////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_CHEQUE_TMP(
	p_correlativo IN cheque_tmp_1.ID_CHEQUE%TYPE,
	p_id_lote IN cheque_tmp_1.LOTE%TYPE,
    p_estado IN cheque_tmp_1.ESTADO%TYPE,
    p_monto IN cheque_tmp_1.VALOR%TYPE,
    p_cuenta IN cheque_tmp_1.CUENTA%TYPE,
    p_referencia IN cheque_tmp_1.REFERENCIA%TYPE
)
IS
BEGIN
	INSERT INTO cheque_tmp_1(correlativo, fecha, cuenta, valor, estado, lote, referencia) VALUES(p_correlativo, TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), p_cuenta, p_monto, p_estado, p_id_lote, p_referencia);
	COMMIT;
END;
/

-- INSERTAR REGISTRO NUEVO CHEQUE COMPENSADO////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE VERIFICAR_LOTE(
    p_id_lote IN lote_tmp_1.ID_LOTE%TYPE
)
IS
    cantidad_cheques lote_tmp_1.NO_DOCUMENTOS%TYPE;
    monto_total_lote lote_tmp_1.VALOR%TYPE;

    cheques_lote lote_tmp_1.NO_DOCUMENTOS%TYPE;
    monto_lote lote_tmp_1.VALOR%TYPE;

    lote_existe INTEGER;

BEGIN
	SELECT COUNT(*) INTO cantidad_cheques FROM  cheque_tmp_1 WHERE lote = p_id_lote;
    SELECT SUM(valor) INTO monto_total_lote FROM cheque_tmp_1 WHERE lote = p_id_lote;

    SELECT COUNT(*) INTO lote_existe FROM lote_tmp_1 WHERE lote_tmp_1.id_lote = p_id_lote;

    SELECT no_documentos INTO cheques_lote FROM lote_tmp_1 WHERE lote_tmp_1.id_lote = p_id_lote;

    SELECT valor INTO monto_lote FROM lote_tmp_1 WHERE lote_tmp_1.id_lote = p_id_lote;

    DBMS_OUTPUT.PUT_LINE(cantidad_cheques);
    DBMS_OUTPUT.PUT_LINE(monto_total_lote);
    DBMS_OUTPUT.PUT_LINE(lote_existe);
    DBMS_OUTPUT.PUT_LINE(cheques_lote);
    DBMS_OUTPUT.PUT_LINE(monto_lote);
    IF lote_existe = 1 THEN

        IF cantidad_cheques = cheques_lote AND monto_total_lote = monto_lote THEN
            UPDATE lote_tmp_1 SET estado = 1 WHERE id_lote = p_id_lote;
        END IF;

    ELSE
        -- ALGUNA DE LAS CUENTAS NO EXISTE
        raise_application_error(-20456,'El lote no existe');
    END IF;

	COMMIT;
END;
/




-- TRIGGER AUTOINCREMENTABLE PARA CHEQUE TEMPORAL
CREATE SEQUENCE chequetmp_sequence;
    CREATE OR REPLACE TRIGGER cheque_tmp_1_on_insert
        BEFORE INSERT ON cheque_tmp_1
        FOR EACH ROW
    BEGIN
        SELECT chequetmp_sequence.nextval
        INTO :new.id_cheque
        FROM dual;
END;
/

-- INSERTAR REGISTRO NUEVO CHEQUE COMPENSADO////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE OPERAR_LOTE(
    p_id_lote IN lote_tmp_1.ID_LOTE%TYPE,
    p_usuario_id_usuario IN USUARIO.ID_USUARIO%TYPE,
    p_terminal_id_terminal IN TERMINAL.ID_TERMINAL%TYPE
)
IS
-- DECLARACIÓN DEL CURSOR
CURSOR c1 IS
    SELECT *
    FROM cheque_tmp_1
    WHERE lote = p_id_lote;
    -- PARAMETROS DEL CURSOR 
    c_id_cheque cheque_tmp_1.id_cheque%TYPE;
    c_fecha cheque_tmp_1.fecha%TYPE;
    c_cuenta cheque_tmp_1.cuenta%TYPE;
    c_valor cheque_tmp_1.valor%TYPE;
    c_estado cheque_tmp_1.estado%TYPE;
    c_lote cheque_tmp_1.lote%TYPE;
    c_referencia cheque_tmp_1.referencia%TYPE;
    c_correlativo cheque_tmp_1.correlativo%TYPE;  
    -- PARAMETROS QUE RECIBE LA TRANSACCION 
    p_no_cuenta_origen  CUENTA.NO_CUENTA%TYPE;
    monto CUENTA.SALDO%TYPE;

    p_correlativo CHEQUE.CORRELATIVO%TYPE;
    -- PARAMETROS DENTRO DEL PROCEDIMIENTO
    saldo_inicial_origen CUENTA.SALDO%TYPE;
    saldo_final_origen CUENTA.SALDO%TYPE;
    estado_cheque CHEQUE.ESTADO_CHEQUE%TYPE;
    cuenta_existe INTEGER;
    num_chequeras INTEGER;
    rango INTEGER;
    id_chequera CHEQUERA.ID_CHEQUERA%TYPE;
    cheque_existe INTEGER;
    estado_cuenta CUENTA.ESTADO_CUENTA%TYPE;
    reserva CUENTA.SALDO%TYPE;
    disponible CUENTA.SALDO%TYPE;
BEGIN
    open c1;
    LOOP
        FETCH c1 INTO c_id_cheque, c_fecha, c_cuenta, c_valor, c_estado, c_lote, c_referencia, c_correlativo;

        EXIT WHEN c1%NOTFOUND;
    
    p_no_cuenta_origen := c_cuenta;
    monto := c_valor;
    p_correlativo := c_correlativo;
        
    SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen; -- COMPROBACION DE QUE LA CUENTA EXISTE
  	IF cuenta_existe = 1 THEN
      SELECT CUENTA.ESTADO_CUENTA INTO estado_cuenta FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
      IF estado_cuenta = 1 THEN
        -- TOMANDO SALDO INICIAL
        SELECT CUENTA.SALDO INTO saldo_inicial_origen FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen FOR UPDATE;
        -- VERIFICANDO QUE EL CHEQUE EXISTE
        SELECT COUNT(*) INTO num_chequeras FROM CHEQUERA WHERE CHEQUERA.NO_CUENTA = p_no_cuenta_origen;
        rango := (num_chequeras*100); -- CALCULANDO LOS CHEQUES
        IF p_correlativo <= rango THEN
          -- OBTENGO EL ID DE LA CHEQUERA
          SELECT CHEQUERA.ID_CHEQUERA INTO id_chequera FROM CHEQUERA WHERE p_correlativo <= CHEQUERA.RANGO_SUP AND p_correlativo >= CHEQUERA.RANGO_INF AND CHEQUERA.NO_CUENTA = p_no_cuenta_origen;
          -- VERIFICANDO SI EL CHEQUE YA EXISTE EN LA TABLA DE CHEQUES
          SELECT COUNT(*) INTO cheque_existe FROM CHEQUE WHERE CHEQUE.CORRELATIVO = p_correlativo AND CHEQUE.ID_CHEQUERA = id_chequera;
          IF cheque_existe = 0 THEN
            -- PUEDO PROCEDER A TRANSFERIR
            IF saldo_inicial_origen >= monto THEN
              -- ES VALIDA LA TRANSACCION
              -- DEBITO DEL ORIGEN...
              saldo_final_origen := saldo_inicial_origen - monto;
              INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
                VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'debito', saldo_inicial_origen, saldo_final_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1);
              UPDATE CUENTA SET SALDO = saldo_final_origen WHERE NO_CUENTA = p_no_cuenta_origen;
              ------------------------------------------
              SELECT CUENTA.SALDO, CUENTA.SALDO_RESERVA INTO disponible, reserva FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
              UPDATE CUENTA SET SALDO_TOTAL = (disponible+reserva) WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
              ------------------------------------------
              INSERT INTO CHEQUE(CORRELATIVO, ID_CHEQUERA, ESTADO_CHEQUE) VALUES(p_correlativo, id_chequera, 4); -- INSERTANDO CHEQUE PAGADO
              
              -- DEBO DE ACTUALIZAR EL CHEQUE EN MI TABLA TEMPORAL EL CHEQUE SE COBRÓ CON ÉXITO
                 
              UPDATE cheque_tmp_1 SET estado = 'OK' WHERE id_cheque = c_id_cheque; -- ESTADO 1 (CHEQUE COBRADO CON EXITO)
              COMMIT;
            ELSE
              raise_application_error(-20456,'Saldo no es suficiente para cubrir el monto solicitado');

              
              -- DEBO DE ACTUALIZAR EL CHEQUE EN MI TABLA TEMPORAL EL CHEQUE NO SE COBRÓ CON ÉXITO POR SALDO INSUFICIENTE
              
              UPDATE cheque_tmp_1 SET estado = 'No cobrado por saldo insuficiente' WHERE id_cheque = c_id_cheque; -- ESTADO 2 (CHEQUE NO COBRADO POR SALDO INSUFICIENTE)
              ROLLBACK;
            END IF;
          ELSE
            -- EL CHEQUE YA EXISTE DEBO VERIFICAR SU ESTADO
           
              -- EL CHEQUE FUE REPORTADO COMO ROBADO
              INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION,RECHAZADO,RAZON_RECHAZO)
                VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'debito', saldo_inicial_origen, saldo_inicial_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1,1,'CHEQUE REPORTADO: '||estado_cheque);
                
                -- DEBO DE ACTUALIZAR EL CHEQUE EN MI TABLA TEMPORAL EL CHEQUE NO SE COBRÓ CON ÉXITO POR CHEQUE ROBADO
                
                UPDATE cheque_tmp_1 SET estado = 'Cheque ya reportado' WHERE id_cheque = c_id_cheque;
         END IF;
        ELSE
          raise_application_error(-20456, 'Cheque no existe en el sistema para la cuenta especificada');
          
          -- DEBO DE ACTUALIZAR EL CHEQUE EN MI TABLA TEMPORAL EL CHEQUE NO EXISTENTE
          
          UPDATE cheque_tmp_1 SET estado = 'Cheque no cobrado fuera de rango' WHERE id_cheque = c_id_cheque; -- ESTADO 7 (CHEQUE NO COBRADO NO EXISTENTE)
        END IF;
      ELSE
        raise_application_error(-20456, 'La cuenta especificada no esta activa');
        
        -- DEBO DE ACTUALIZAR EL CHEQUE EN MI TABLA TEMPORAL EL CHEQUE NO SE COBRÓ CON ÉXITO POR CUENTA INACTIVA
        
        UPDATE cheque_tmp_1 SET estado = 'No cobrado por cuenta inactiva' WHERE id_cheque = c_id_cheque; -- ESTADO 8 (CHEQUE NO COBRADO CUENTA INACTIVA)
      END IF;
    ELSE
				-- LA CUENTA NO EXISTE
				raise_application_error(-20456, 'Cuenta especificada no existe');
               
                -- DEBO DE ACTUALIZAR EL CHEQUE EN MI TABLA TEMPORAL EL CHEQUE NO SE COBRÓ CON ÉXITO POR CUENTA INEXISTENTE
                
                UPDATE cheque_tmp_1 SET estado = 'No cobrado por cuenta inexistente' WHERE id_cheque = c_id_cheque; -- ESTADO 9 (CHEQUE NO COBRADO REPORTADO COMO PERDIDO)
    END IF;
    
    end loop;
  close c1;
  
  UPDATE lote_tmp_1 SET estado = 2 WHERE id_lote = p_id_lote; -- ESTADO 1 (CHEQUE COBRADO CON EXITO)
END;
/

select * from cheque_tmp_1;
-- INSERTAR REGISTRO NUEVO CHEQUE COMPENSADO////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE LIBERAR_FONDOS(
    p_no_cuenta_destino IN CUENTA.NO_CUENTA%TYPE,
    monto IN CUENTA.SALDO%TYPE,
    p_usuario_id_usuario IN USUARIO.ID_USUARIO%TYPE,
    p_terminal_id_terminal IN TERMINAL.ID_TERMINAL%TYPE,
    p_estado_operacion INTEGER,
    p_correlativo INTEGER
)
IS
    saldo_inicial_destino CUENTA.SALDO%TYPE;
    saldo_final_destino CUENTA.SALDO%TYPE;

    destino_existe INTEGER;

    destino_estado CUENTA.ESTADO_CUENTA%TYPE;

    disponible_destino CUENTA.SALDO%TYPE;
    reserva_destino CUENTA.SALDO%TYPE;
    
BEGIN
		SELECT COUNT(*) INTO destino_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
    IF destino_existe = 1 THEN
    -- VER SI ESTAN DISPONIBLES LAS CUENTAS
      
      SELECT CUENTA.ESTADO_CUENTA INTO destino_estado FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
      IF destino_estado = 1 THEN
        -- TOMANDO DE LA CUENTA DE DESTINO LOS DATOS:
          SELECT CUENTA.SALDO INTO saldo_inicial_destino FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino FOR UPDATE;
          
          SELECT CUENTA.SALDO_RESERVA INTO reserva_destino FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino FOR UPDATE;
        -- REALIZANDO COMPROBACION SI EXISTEN FONDOS
        
            
            -- ACREDITO AL DESTINO
            IF p_estado_operacion = 1 THEN
                saldo_final_destino := saldo_inicial_destino + monto;
            ELSE
                saldo_final_destino := saldo_inicial_destino;
            END IF;
    
            -- INSERTANDO LOS CREDITOS
            INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
            VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'efectivo', 'credito', saldo_inicial_destino, saldo_final_destino, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_destino, 1);
            
            ----------------------
            -- ACTUALIZO EL DESTINO LIBERO LA RESERVA
            UPDATE CUENTA SET SALDO = saldo_final_destino WHERE NO_CUENTA = p_no_cuenta_destino;
            
            IF reserva_destino - monto >= 0 THEN
                UPDATE CUENTA SET SALDO_RESERVA = reserva_destino - monto WHERE NO_CUENTA = p_no_cuenta_destino;
            ELSE
                UPDATE CUENTA SET SALDO_RESERVA = 0 WHERE NO_CUENTA = p_no_cuenta_destino;
            END IF;
            ----------------------
            SELECT CUENTA.SALDO, CUENTA.SALDO_RESERVA INTO disponible_destino, reserva_destino FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
            UPDATE CUENTA SET SALDO_TOTAL = (disponible_destino + reserva_destino) WHERE NO_CUENTA = p_no_cuenta_destino;
           --
            UPDATE CHEQUE_TMP_2 SET ESTADO_CHEQUE='RESERVA LIBERADA' WHERE CUENTA=p_no_cuenta_destino AND CORRELATIVO=p_correlativo; 
            ----------------------
            COMMIT;
      ELSE
        raise_application_error(-20456, 'No se puede realizar transaccion con cuenta(s) bloqueadas');
        ROLLBACK;
      END IF;
    ELSE
			-- ALGUNA DE LAS CUENTAS NO EXISTE
			raise_application_error(-20456,'Una de las cuentas especificadas no existe');
    END IF;
END;
/

-- INSERTAR REGISTRO NUEVO CHEQUE COMPENSADO////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE CAMBIAR_ESTADO_LOTE(
    p_id_lote IN lote_tmp_1.ID_LOTE%TYPE
)
IS
    
    
    lote_existe INTEGER;

BEGIN    
    SELECT COUNT(*) INTO lote_existe FROM lote_tmp_1 WHERE lote_tmp_1.id_lote = p_id_lote;

    IF lote_existe = 1 THEN
    
        UPDATE lote_tmp_1 SET estado = 3 WHERE id_lote = p_id_lote; -- ESTADO 3 (LOTE EXPORTADO)
    
    ELSE
        -- ALGUNA DE LAS CUENTAS NO EXISTE
        raise_application_error(-20456,'El lote no existe');
    END IF;
    
	COMMIT;
END;
/


/**
 * PRUEBAS PARA COMPENSACION
 */

/*
 SELECT * FROM CHEQUE;

CALL INSERT_CHEQUERA(123,1);
CALL INSERT_CHEQUERA(456,1);
CALL INSERT_CHEQUERA(789,1);



SELECT * FROM CHEQUERA;


CALL INSERT_LOTE_TMP(1,1,4,10000,0);
CALL INSERT_CHEQUE_TMP(1,1,1,2500.00,123,123);
CALL INSERT_CHEQUE_TMP(2,1,1,2500.00,456,123);
CALL INSERT_CHEQUE_TMP(3,1,1,2500.00,789,123);
CALL INSERT_CHEQUE_TMP(4,1,1,1500.00,789,123);
CALL INSERT_CHEQUE_TMP(5,1,1,1000.00,789,123);

CALL INSERT_LOTE_TMP(2,1,5,10000,0);
CALL INSERT_CHEQUE_TMP(1,2,0,2500.00,123,123);
CALL INSERT_CHEQUE_TMP(2,2,0,2500.00,456,123);
CALL INSERT_CHEQUE_TMP(3,2,0,2500.00,789,123);
CALL INSERT_CHEQUE_TMP(4,2,0,1500.00,789,123);
CALL INSERT_CHEQUE_TMP(5,2,0,1000.00,789,123);

CALL VERIFICAR_LOTE(2);



CALL OPERAR_LOTE(2,1,1);
SELECT * FROM USUARIO;
SELECT * FROM TERMINAL;

select * from cheque_tmp_1;
select * from lote_tmp_1;

SELECT * FROM CUENTA;

SELECT * FROM TRANSACCION;
*/





select * from cheque_tmp_1;


/*
CALL OPERAR_LOTE(2,1,1);
SELECT * FROM USUARIO;
SELECT * FROM TERMINAL;

select * from cheque_tmp_1;
select * from lote_tmp_1;

SELECT * FROM CUENTA;

SELECT * FROM TRANSACCION;
*/


