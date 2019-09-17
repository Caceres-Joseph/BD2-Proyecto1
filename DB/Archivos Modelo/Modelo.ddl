-- Generado por Oracle SQL Developer Data Modeler 19.2.0.182.1216
--   en:        2019-09-14 16:59:21 CST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



DROP TABLE agencia CASCADE CONSTRAINTS;

DROP TABLE banco CASCADE CONSTRAINTS;

DROP TABLE cliente CASCADE CONSTRAINTS;

DROP TABLE cuenta CASCADE CONSTRAINTS;

DROP TABLE mancomunada CASCADE CONSTRAINTS;

DROP TABLE permiso CASCADE CONSTRAINTS;

DROP TABLE rol CASCADE CONSTRAINTS;

DROP TABLE rol_permiso CASCADE CONSTRAINTS;

DROP TABLE terminal CASCADE CONSTRAINTS;

DROP TABLE tipo_cuenta CASCADE CONSTRAINTS;

DROP TABLE transaccion CASCADE CONSTRAINTS;

DROP TABLE usuario CASCADE CONSTRAINTS;

CREATE TABLE agencia (
    id_agencia       INTEGER NOT NULL,
    nombre           VARCHAR2(32 CHAR) NOT NULL,
    direccion        VARCHAR2(32 CHAR) NOT NULL,
    banco_id_banco   INTEGER NOT NULL
);

ALTER TABLE agencia ADD CONSTRAINT agencia_pk PRIMARY KEY ( id_agencia );

CREATE TABLE banco (
    id_banco   INTEGER NOT NULL,
    nombre     VARCHAR2(32 CHAR)
);

ALTER TABLE banco ADD CONSTRAINT banco_pk PRIMARY KEY ( id_banco );

CREATE TABLE cliente (
    id_cliente   INTEGER NOT NULL,
    nombre       VARCHAR2(32 CHAR) NOT NULL,
    direccion    VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id_cliente );

CREATE TABLE cuenta (
    no_cuenta             INTEGER NOT NULL,
    saldo                 REAL NOT NULL,
    banco_id_banco        INTEGER NOT NULL,
    tipo_cuenta_id_tipo   INTEGER NOT NULL
);

ALTER TABLE cuenta ADD CONSTRAINT cuenta_pk PRIMARY KEY ( no_cuenta );

CREATE TABLE mancomunada (
    id_mancomunada       INTEGER NOT NULL,
    cliente_id_cliente   INTEGER NOT NULL,
    cuenta_no_cuenta     INTEGER NOT NULL
);

ALTER TABLE mancomunada ADD CONSTRAINT mancomunada_pk PRIMARY KEY ( id_mancomunada );

CREATE TABLE permiso (
    id_permiso    INTEGER NOT NULL,
    nombre        VARCHAR2(32 CHAR) NOT NULL,
    descripcion   VARCHAR2(32 CHAR)
);

ALTER TABLE permiso ADD CONSTRAINT permiso_pk PRIMARY KEY ( id_permiso );

CREATE TABLE rol (
    id_rol   INTEGER NOT NULL,
    nombre   VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

CREATE TABLE rol_permiso (
    rol_id_rol           INTEGER NOT NULL,
    permiso_id_permiso   INTEGER NOT NULL
);

CREATE TABLE terminal (
    id_terminal          INTEGER NOT NULL,
    agencia_id_agencia   INTEGER NOT NULL,
    usuario_id_usuario   INTEGER NOT NULL
);

ALTER TABLE terminal ADD CONSTRAINT terminal_pk PRIMARY KEY ( id_terminal );

CREATE TABLE tipo_cuenta (
    id_tipo   INTEGER NOT NULL,
    nombre    VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE tipo_cuenta ADD CONSTRAINT tipo_cuenta_pk PRIMARY KEY ( id_tipo );

CREATE TABLE transaccion (
    id_transaccion         INTEGER NOT NULL,
    fecha                  DATE NOT NULL,
    tipo                   VARCHAR2(32 CHAR) NOT NULL,
    naturaleza             VARCHAR2(32 CHAR) NOT NULL,
    saldo_inicial          REAL NOT NULL,
    saldo_final            REAL NOT NULL,
    codigo_autorizacion    INTEGER,
    rechazado              INTEGER,
    razon_rechazo          VARCHAR2(32 CHAR),
    usuario_id_usuario     INTEGER NOT NULL,
    terminal_id_terminal   INTEGER NOT NULL,
    cuenta_no_cuenta       INTEGER NOT NULL
);

ALTER TABLE transaccion ADD CONSTRAINT transaccion_pk PRIMARY KEY ( id_transaccion );

CREATE TABLE usuario (
    id_usuario   INTEGER NOT NULL,
    usuario      VARCHAR2(32 CHAR) NOT NULL,
    password     VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE agencia
    ADD CONSTRAINT agencia_banco_fk FOREIGN KEY ( banco_id_banco )
        REFERENCES banco ( id_banco )
            ON DELETE CASCADE;

ALTER TABLE cuenta
    ADD CONSTRAINT cuenta_banco_fk FOREIGN KEY ( banco_id_banco )
        REFERENCES banco ( id_banco )
            ON DELETE CASCADE;

ALTER TABLE cuenta
    ADD CONSTRAINT cuenta_tipo_cuenta_fk FOREIGN KEY ( tipo_cuenta_id_tipo )
        REFERENCES tipo_cuenta ( id_tipo )
            ON DELETE CASCADE;

ALTER TABLE mancomunada
    ADD CONSTRAINT mancomunada_cliente_fk FOREIGN KEY ( cliente_id_cliente )
        REFERENCES cliente ( id_cliente )
            ON DELETE CASCADE;

ALTER TABLE mancomunada
    ADD CONSTRAINT mancomunada_cuenta_fk FOREIGN KEY ( cuenta_no_cuenta )
        REFERENCES cuenta ( no_cuenta )
            ON DELETE CASCADE;

ALTER TABLE rol_permiso
    ADD CONSTRAINT rol_permiso_permiso_fk FOREIGN KEY ( permiso_id_permiso )
        REFERENCES permiso ( id_permiso )
            ON DELETE CASCADE;

ALTER TABLE rol_permiso
    ADD CONSTRAINT rol_permiso_rol_fk FOREIGN KEY ( rol_id_rol )
        REFERENCES rol ( id_rol )
            ON DELETE CASCADE;

ALTER TABLE terminal
    ADD CONSTRAINT terminal_agencia_fk FOREIGN KEY ( agencia_id_agencia )
        REFERENCES agencia ( id_agencia )
            ON DELETE CASCADE;

ALTER TABLE terminal
    ADD CONSTRAINT terminal_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario )
            ON DELETE CASCADE;

ALTER TABLE transaccion
    ADD CONSTRAINT transaccion_cuenta_fk FOREIGN KEY ( cuenta_no_cuenta )
        REFERENCES cuenta ( no_cuenta )
            ON DELETE CASCADE;

ALTER TABLE transaccion
    ADD CONSTRAINT transaccion_terminal_fk FOREIGN KEY ( terminal_id_terminal )
        REFERENCES terminal ( id_terminal )
            ON DELETE CASCADE;

ALTER TABLE transaccion
    ADD CONSTRAINT transaccion_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario )
            ON DELETE CASCADE;



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            12
-- CREATE INDEX                             0
-- ALTER TABLE                             23
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
