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
INSERT INTO USUARIO(id_usuario, usuario, password,estado_usuario, rol_id_rol) values
(1,'user1','123',1,1);
COMMIT;
INSERT INTO USUARIO(id_usuario, usuario, password,estado_usuario, rol_id_rol) values
(2,'user2','123',1,1);
COMMIT;
INSERT INTO USUARIO(id_usuario, usuario, password,estado_usuario, rol_id_rol) values
(3,'user3','123',1,1);
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
COMMIT;
--------------------------------------------------------------------------------------------------------------------------------