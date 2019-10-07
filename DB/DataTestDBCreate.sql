
-- Generado por Oracle SQL Developer Data Modeler 19.2.0.182.1216
--   en:        2019-09-14 17:06:06 CST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g
CREATE TABLE agencia (
    id_agencia       INTEGER NOT NULL,
    nombre           VARCHAR2(32 CHAR) NOT NULL,
    direccion        VARCHAR2(32 CHAR) NOT NULL,
    banco_id_banco   INTEGER NOT NULL,
    estado_agencia   INTEGER NOT NULL
);

ALTER TABLE agencia ADD CONSTRAINT agencia_pk PRIMARY KEY ( id_agencia );

CREATE TABLE banco (
    id_banco   INTEGER NOT NULL,
    nombre     VARCHAR2(32 CHAR),
    estado_banco   INTEGER NOT NULL
);

ALTER TABLE banco ADD CONSTRAINT banco_pk PRIMARY KEY ( id_banco );

CREATE TABLE cliente (
    dpi_cliente   INTEGER NOT NULL,
    nombre       VARCHAR2(32 CHAR) NOT NULL,
    apellido     VARCHAR2(32 CHAR) NOT NULL,
    direccion    VARCHAR2(32 CHAR) NOT NULL,
    correo       VARCHAR2(32 CHAR) NOT NULL,
    telefono     VARCHAR2(32 CHAR) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    estado_cliente   INTEGER NOT NULL,
    foto    VARCHAR2(50 CHAR) NOT NULL,
    firma   VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( dpi_cliente );

CREATE TABLE cuenta (
    no_cuenta             INTEGER NOT NULL,
    saldo                 REAL NOT NULL,
    banco_id_banco        INTEGER NOT NULL,
    tipo_cuenta_id_tipo   INTEGER NOT NULL,
    estado_cuenta         INTEGER NOT NULL
);

ALTER TABLE cuenta ADD CONSTRAINT cuenta_pk PRIMARY KEY ( no_cuenta );

CREATE TABLE mancomunada (
    cliente_dpi_cliente   INTEGER NOT NULL,
    cuenta_no_cuenta     INTEGER NOT NULL,
    estado_mancomunada   INTEGER NOT NULL
);

ALTER TABLE mancomunada ADD CONSTRAINT mancomunada_pk PRIMARY KEY ( cliente_dpi_cliente,
                                                                    cuenta_no_cuenta);

CREATE TABLE permiso (
    id_permiso    INTEGER NOT NULL,
    nombre        VARCHAR2(32 CHAR) NOT NULL,
    descripcion   VARCHAR2(32 CHAR),
    estado_permiso   INTEGER NOT NULL
);

ALTER TABLE permiso ADD CONSTRAINT permiso_pk PRIMARY KEY ( id_permiso );

CREATE TABLE rol (
    id_rol   INTEGER NOT NULL,
    nombre   VARCHAR2(32 CHAR) NOT NULL,
    estado_rol   INTEGER NOT NULL
);

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

CREATE TABLE rol_permiso (
    rol_id_rol           INTEGER NOT NULL,
    permiso_id_permiso   INTEGER NOT NULL,
    estado_rol_permiso   INTEGER NOT NULL
);

ALTER TABLE rol_permiso ADD CONSTRAINT rol_permiso_pk PRIMARY KEY ( rol_id_rol,
                                                                    permiso_id_permiso );

CREATE TABLE terminal (
    id_terminal          INTEGER NOT NULL,
    agencia_id_agencia   INTEGER NOT NULL,
    usuario_id_usuario   INTEGER NOT NULL,
    estado_terminal      INTEGER NOT NULL
);

ALTER TABLE terminal ADD CONSTRAINT terminal_pk PRIMARY KEY ( id_terminal );

CREATE TABLE tipo_cuenta (
    id_tipo   INTEGER NOT NULL,
    nombre    VARCHAR2(32 CHAR) NOT NULL,
    estado_tipo_cuenta   INTEGER NOT NULL
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
    cuenta_no_cuenta       INTEGER NOT NULL,
    estado_transaccion     INTEGER NOT NULL
);

ALTER TABLE transaccion ADD CONSTRAINT transaccion_pk PRIMARY KEY ( id_transaccion );

CREATE TABLE usuario (
    id_usuario   INTEGER NOT NULL,
    usuario      VARCHAR2(32 CHAR) NOT NULL,
    password     VARCHAR2(32 CHAR) NOT NULL,
    estado_usuario INTEGER NOT NULL,
    rol_permiso_rol_id_rol           INTEGER NOT NULL,
    rol_permiso_permiso_id_permiso   INTEGER NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

CREATE TABLE chequera (
    id_chequera   INTEGER NOT NULL,
    no_cuenta      INTEGER NOT NULL,
    estado_chequera INTEGER NOT NULL
);

ALTER TABLE chequera ADD CONSTRAINT chequera_pk PRIMARY KEY ( id_chequera );

CREATE TABLE cheque (
    id_cheque   INTEGER NOT NULL,
    id_chequera      INTEGER NOT NULL,
    estado_cheque     VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE cheque ADD CONSTRAINT cheque_pk PRIMARY KEY ( id_cheque );

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
    ADD CONSTRAINT mancomunada_cliente_fk FOREIGN KEY ( cliente_dpi_cliente )
        REFERENCES cliente ( dpi_cliente )
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

ALTER TABLE usuario
    ADD CONSTRAINT usuario_rol_permiso_fk FOREIGN KEY ( rol_permiso_rol_id_rol,
                                                        rol_permiso_permiso_id_permiso )
        REFERENCES rol_permiso ( rol_id_rol,
                                 permiso_id_permiso )
            ON DELETE CASCADE;
            
ALTER TABLE chequera
    ADD CONSTRAINT chequera_cuenta_fk FOREIGN KEY ( no_cuenta )
        REFERENCES cuenta ( no_cuenta )
            ON DELETE CASCADE;

ALTER TABLE cheque
    ADD CONSTRAINT cheque_chequera_fk FOREIGN KEY ( id_chequera )
        REFERENCES chequera ( id_chequera )
            ON DELETE CASCADE;

-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            12
-- CREATE INDEX                             0
-- ALTER TABLE                             24
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
-- INSERTING DATA
--INSERTAR CLIENTE ---------------------------------------------------------------------------------------------------3 CLIENTES
INSERT INTO CLIENTE(dpi_cliente,nombre, apellido, direccion, correo, telefono, fecha_nacimiento, estado_cliente,foto,firma) values 
(1,'Daniel','Garcia', 'Guatemala', 'danielgarcia0976@gmail.com','12345678','10-Sep-02',1,'foto','firma');
COMMIT;
INSERT INTO CLIENTE(dpi_cliente,nombre, apellido, direccion, correo, telefono, fecha_nacimiento, estado_cliente,foto,firma) values 
(2,'Ricardo','Cutz', 'Guatemala', 'cutz@gmail.com','12345678','12-Sep-02',1,'foto','firma');
COMMIT;
INSERT INTO CLIENTE(dpi_cliente,nombre, apellido, direccion, correo, telefono, fecha_nacimiento, estado_cliente,foto,firma) values 
(3,'Joseph','Caceres', 'Guatemala', 'caceres@gmail.com','12345678','13-Sep-02',1,'foto','firma');
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR BANCO -------------------------------------------------------------------------------------------------------1 BANCOS
INSERT INTO BANCO(id_banco, nombre, estado_banco) values
(1,'Banrural',1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR AGENCIA ---------------------------------------------------------------------------------------------------3 AGENCIAS
INSERT INTO AGENCIA(id_agencia, nombre, direccion,banco_id_banco,estado_agencia) values
(1, 'Agencia1','Zona 1', 1, 1);
COMMIT;
INSERT INTO AGENCIA(id_agencia, nombre, direccion,banco_id_banco,estado_agencia) values
(2, 'Agencia2','Zona 2', 1, 1);
COMMIT;
INSERT INTO AGENCIA(id_agencia, nombre, direccion,banco_id_banco,estado_agencia) values
(3, 'Agencia3','Zona 3', 1, 1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR TIPO CUENTA----------------------------------------------------------------------------------------------------1 TIPO
INSERT INTO TIPO_CUENTA(id_tipo, nombre, estado_tipo_cuenta) values
(1, 'Ahorro', 1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR CUENTA -----------------------------------------------------------------------------------------------------3 CUENTAS
INSERT INTO CUENTA(no_cuenta, saldo, banco_id_banco, tipo_cuenta_id_tipo, estado_cuenta) values
(123, 5000.00,1,1,1);
COMMIT;
INSERT INTO CUENTA(no_cuenta, saldo, banco_id_banco, tipo_cuenta_id_tipo, estado_cuenta) values
(456, 10000.00,1,1,1);
COMMIT;
INSERT INTO CUENTA(no_cuenta, saldo, banco_id_banco, tipo_cuenta_id_tipo, estado_cuenta) values
(789, 15000.00,1,1,1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR MANCOMUNADA ------------------------------------------------------------------------------------------------3 CUENTAS
INSERT INTO MANCOMUNADA(cliente_dpi_cliente,cuenta_no_cuenta,estado_mancomunada) values
(1,123,1);
COMMIT;
INSERT INTO MANCOMUNADA(cliente_dpi_cliente,cuenta_no_cuenta,estado_mancomunada) values
(2,456,1);
COMMIT;
INSERT INTO MANCOMUNADA(cliente_dpi_cliente,cuenta_no_cuenta,estado_mancomunada) values
(3,789,1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------



--INSERTAR CHEQUERA -------------------------------------------------------------------------------------------------3 CHEQUERAS
INSERT INTO CHEQUERA(id_chequera,no_cuenta,estado_chequera) values
(1,123,1);
COMMIT;
INSERT INTO CHEQUERA(id_chequera,no_cuenta,estado_chequera) values
(2,456,1);
COMMIT;
INSERT INTO CHEQUERA(id_chequera,no_cuenta,estado_chequera) values
(3,789,1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR CHEQUE -----------------------------------------------------------------------------------------------------3 CHEQUES
INSERT INTO CHEQUE(id_cheque,id_chequera,estado_cheque) values
(1,1,1);
COMMIT;
INSERT INTO CHEQUE(id_cheque,id_chequera,estado_cheque) values
(2,2,1);
COMMIT;
INSERT INTO CHEQUE(id_cheque,id_chequera,estado_cheque) values
(3,3,1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR PERMISO ----------------------------------------------------------------------------------------------------1 PERMISO
INSERT INTO PERMISO(id_permiso,nombre, descripcion, estado_permiso) values
(1,'permiso 1','descripcion de permisos',1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR ROL ------------------------------------------------------------------------------------------------------------1 ROL
INSERT INTO ROL(id_rol, nombre, estado_rol) values
(1,'ROL 1', 1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR ROL_PERMISO --------------------------------------------------------------------------------------------1 ROL_PERMISO
INSERT INTO ROL_PERMISO(rol_id_rol, permiso_id_permiso, estado_rol_permiso) values
(1,1,1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR USUARIO ---------------------------------------------------------------------------------------------------3 USUARIOS
INSERT INTO USUARIO(id_usuario, usuario, password,estado_usuario, rol_permiso_rol_id_rol,rol_permiso_permiso_id_permiso) values
(1,'user1','123',1,1,1);
COMMIT;
INSERT INTO USUARIO(id_usuario, usuario, password,estado_usuario, rol_permiso_rol_id_rol,rol_permiso_permiso_id_permiso) values
(2,'user2','123',1,1,1);
COMMIT;
INSERT INTO USUARIO(id_usuario, usuario, password,estado_usuario, rol_permiso_rol_id_rol,rol_permiso_permiso_id_permiso) values
(3,'user3','123',1,1,1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR TERMINAL ------------------------------------------------------------------------------------------------3 TERMINALES
INSERT INTO TERMINAL(id_terminal, agencia_id_agencia, usuario_id_usuario, estado_terminal) values
(1,1,1,1);
COMMIT;
INSERT INTO TERMINAL(id_terminal, agencia_id_agencia, usuario_id_usuario, estado_terminal) values
(2,2,2,1);
COMMIT;
INSERT INTO TERMINAL(id_terminal, agencia_id_agencia, usuario_id_usuario, estado_terminal) values
(3,3,3,1);
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------

--INSERTAR TRANSACCION ------------------------------------------------------------------------------------------5 TRANSACCIONES
---------------------------------------------------DEPOSITOS EN EFECTIVO
--DEPOSITOS A CUENTA 123 -AGENCIA 1
INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(1,'10-Sep-02','monetaria','deposito',5000.00, 8000.00, 101,1,1,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(2,'11-Sep-02','monetaria','deposito',8000.00, 11000.00, 101,1,1,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(3,'12-Sep-02','monetaria','deposito',11000.00, 18000.00, 101,1,1,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(4,'13-Sep-02','monetaria','deposito',18000.00, 28000.00, 101,1,1,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(5,'14-Sep-02','monetaria','deposito',28000.00, 50000.00, 101,1,1,123,1);
COMMIT;

--DEPOSITOS A CUENTA 456 -AGENCIA 2
INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(6,'10-Sep-02','monetaria','deposito',10000.00, 11000.00, 101,2,2,456,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(7,'11-Sep-02','monetaria','deposito',11000.00, 12000.00, 101,1,1,456,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(8,'12-Sep-02','monetaria','deposito',12000.00, 18000.00, 101,2,2,456,1);
COMMIT;

--DEPOSITOS A CUENTA 789 -AGENCIA 3
INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(9,'10-Sep-02','monetaria','deposito',15000.00, 18000.00, 101,2,2,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(10,'11-Sep-02','monetaria','deposito',18000.00, 20000.00, 101,1,1,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(11,'12-Sep-02','monetaria','deposito',20000.00, 25000.00, 101,3,3,789,1);
COMMIT;

---------------------------------------------------DEPOSITOS CON CHEQUE
--DEPOSITOS EN AGENCIA 1
INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(12,'10-Sep-02','cheque','deposito',15000.00, 18000.00, 101,1,1,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(13,'11-Sep-02','cheque','deposito',18000.00, 20000.00, 101,1,1,456,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(14,'12-Sep-02','cheque','deposito',20000.00, 25000.00, 101,1,1,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(15,'10-Sep-02','cheque','deposito',15000.00, 18000.00, 101,1,1,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(16,'11-Sep-02','cheque','deposito',18000.00, 20000.00, 101,1,1,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(17,'12-Sep-02','cheque','deposito',20000.00, 25000.00, 101,1,1,789,1);
COMMIT;

--DEPOSITOS EN AGENCIA 2
INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(18,'10-Sep-02','cheque','deposito',10000.00, 18000.00, 101,2,2,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(19,'11-Sep-02','cheque','deposito',11000.00, 20000.00, 101,2,2,456,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(20,'12-Sep-02','cheque','deposito',23000.00, 25000.00, 101,2,2,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(21,'10-Sep-02','cheque','deposito',1000.00, 18000.00, 101,2,2,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(22,'11-Sep-02','cheque','deposito',10000.00, 20000.00, 101,2,2,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(23,'12-Sep-02','cheque','deposito',2000.00, 25000.00, 101,2,2,789,1);
COMMIT;


--DEPOSITOS EN AGENCIA 3
INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(24,'10-Sep-02','cheque','deposito',8000.00, 28000.00, 101,3,3,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(26,'12-Sep-02','cheque','deposito',100.00, 25030.00, 101,3,3,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(27,'10-Sep-02','cheque','deposito',9000.00, 18300.00, 101,3,3,123,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(28,'11-Sep-02','cheque','deposito',8000.00, 50000.00, 101,3,3,789,1);
COMMIT;

INSERT INTO TRANSACCION(id_transaccion,fecha, tipo, naturaleza, saldo_inicial, saldo_final, codigo_autorizacion, usuario_id_usuario, terminal_id_terminal, cuenta_no_cuenta, estado_transaccion) values
(29,'12-Sep-02','cheque','deposito',100.00, 500.30, 101,3,3,789,1);
--------------------------------------------------------------------------------------------------------------------------------
COMMIT;
