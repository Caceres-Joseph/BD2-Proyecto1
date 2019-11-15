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
    fecha     VARCHAR2(32 CHAR),
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
    SELECT SUM(valor) INTO monto_total_lote FROM cheque_tmp_1 WHERE lote = p_id_lote GROUP BY valor;
    
    SELECT COUNT(*) INTO lote_existe FROM lote_tmp_1 WHERE lote_tmp_1.id_lote = p_id_lote;
    
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

