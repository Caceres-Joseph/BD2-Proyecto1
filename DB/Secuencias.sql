-- SECUENCIAS

-- AGENCIA
CREATE SEQUENCE agencia_sequence;
CREATE OR REPLACE TRIGGER agencia_on_insert
    BEFORE INSERT ON agencia
    FOR EACH ROW
BEGIN
    SELECT agencia_sequence.nextval
    INTO :new.id_agencia
    FROM dual;
END;


-- BANCO
CREATE SEQUENCE banco_sequence;
CREATE OR REPLACE TRIGGER banco_on_insert
    BEFORE INSERT ON banco
    FOR EACH ROW
BEGIN
    SELECT banco_sequence.nextval
    INTO :new.id_banco
    FROM dual;
END;

--CLIENTE
--CREATE SEQUENCE cliente_sequence;
--CREATE OR REPLACE TRIGGER cliente_on_insert
--    BEFORE INSERT ON cliente
--    FOR EACH ROW
--BEGIN
--    SELECT cliente_sequence.nextval
--    INTO :new.dpi_cliente
--    FROM dual;
--END;

--CUENTA
CREATE SEQUENCE cuenta_sequence;
CREATE OR REPLACE TRIGGER cuenta_on_insert
    BEFORE INSERT ON cuenta
    FOR EACH ROW
BEGIN
    SELECT cuenta_sequence.nextval
    INTO :new.no_cuenta
    FROM dual;
END;

--MANCOMUNADA
CREATE SEQUENCE mancomunada_sequence;
CREATE OR REPLACE TRIGGER mancomunada_on_insert
    BEFORE INSERT ON mancomunada
    FOR EACH ROW
BEGIN
    SELECT mancomunada_sequence.nextval
    INTO :new.id_mancomunada
    FROM dual;
END;

--PERMISO
CREATE SEQUENCE permiso_sequence;
CREATE OR REPLACE TRIGGER permiso_on_insert
    BEFORE INSERT ON permiso
    FOR EACH ROW
BEGIN
    SELECT permiso_sequence.nextval
    INTO :new.id_permiso
    FROM dual;
END;

--ROL
CREATE SEQUENCE rol_sequence;
CREATE OR REPLACE TRIGGER rol_on_insert
    BEFORE INSERT ON rol
    FOR EACH ROW
BEGIN
    SELECT rol_sequence.nextval
    INTO :new.id_rol
    FROM dual;
END;

--TERMINAL
CREATE SEQUENCE terminal_sequence;
CREATE OR REPLACE TRIGGER terminal_on_insert
    BEFORE INSERT ON terminal
    FOR EACH ROW
BEGIN
    SELECT terminal_sequence.nextval
    INTO :new.id_terminal
    FROM dual;
END;

--TIPO CUENTA 
CREATE SEQUENCE tipo_cuenta_sequence;
CREATE OR REPLACE TRIGGER tipo_cuenta_on_insert
    BEFORE INSERT ON tipo_cuenta
    FOR EACH ROW
BEGIN
    SELECT tipo_cuenta_sequence.nextval
    INTO :new.id_tipo
    FROM dual;
END;

-- TRANSACCION
CREATE SEQUENCE transaccion_sequence;
CREATE OR REPLACE TRIGGER transaccion_on_insert
    BEFORE INSERT ON transaccion
    FOR EACH ROW
BEGIN
    SELECT transaccion_sequence.nextval
    INTO :new.id_transaccion
    FROM dual;
END;

-- USUARIO
CREATE SEQUENCE usuario_sequence;
CREATE OR REPLACE TRIGGER usuario_on_insert
    BEFORE INSERT ON usuario
    FOR EACH ROW
BEGIN
    SELECT usuario_sequence.nextval
    INTO :new.id_usuario
    FROM dual;
END;
