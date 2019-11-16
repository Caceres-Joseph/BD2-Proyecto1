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
    estado  INTEGER NOT NULL,
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
    p_id_lote IN lote_tmp_1.ID_LOTE%TYPE
)
IS

CURSOR c1 IS
    SELECT *
    FROM cheque_tmp_1
    WHERE lote = p_id_lote;

    c_id_cheque cheque_tmp_1.id_cheque%TYPE;
    c_fecha cheque_tmp_1.fecha%TYPE;
    c_cuenta cheque_tmp_1.cuenta%TYPE;
    c_valor cheque_tmp_1.valor%TYPE;
    c_estado cheque_tmp_1.estado%TYPE;
    c_lote cheque_tmp_1.lote%TYPE;
    c_referencia cheque_tmp_1.referencia%TYPE;
    c_correlativo cheque_tmp_1.correlativo%TYPE;
    
BEGIN
    open c1;    
    LOOP
        FETCH c1 INTO c_id_cheque, c_fecha, c_cuenta, c_valor, c_estado, c_lote, c_referencia, c_correlativo;
        
        EXIT WHEN c1%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE(c_id_cheque);
        DBMS_OUTPUT.PUT_LINE(c_valor);
        
    end loop;
  close c1;
END;
/

/*    
CALL INSERT_LOTE_TMP(1,1,4,10000,0);
CALL INSERT_CHEQUE_TMP(1,1,1,2500.00,123,123);
CALL INSERT_CHEQUE_TMP(2,1,1,2500.00,456,123);
CALL INSERT_CHEQUE_TMP(3,1,1,2500.00,789,123);
CALL INSERT_CHEQUE_TMP(4,1,1,1500.00,789,123);
CALL INSERT_CHEQUE_TMP(5,1,1,1000.00,789,123);

CALL INSERT_LOTE_TMP(2,1,5,10000,0);
CALL INSERT_CHEQUE_TMP(1,2,1,2500.00,123,123);
CALL INSERT_CHEQUE_TMP(2,2,1,2500.00,456,123);
CALL INSERT_CHEQUE_TMP(3,2,1,2500.00,789,123);
CALL INSERT_CHEQUE_TMP(4,2,1,1500.00,789,123);
CALL INSERT_CHEQUE_TMP(5,2,1,1000.00,789,123);

CALL VERIFICAR_LOTE(2);
*/

select * from cheque_tmp_1;






