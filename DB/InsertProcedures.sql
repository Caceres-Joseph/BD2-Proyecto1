-- INSERT STORE PROCEDURES

-- INSERTAR REGISTRO BRANCO/////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_BANCO(
	p_nombre IN BANCO.NOMBRE%TYPE,
	p_estado_banco IN BANCO.ESTADO_BANCO%TYPE
)
IS
BEGIN
	INSERT INTO BANCO(NOMBRE, ESTADO_BANCO) VALUES (p_nombre, p_estado_banco);
	COMMIT;
END;
/

-- INSERTAR REGISTRO AGENCIA////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_AGENCIA(
	p_nombre IN AGENCIA.NOMBRE%TYPE,
	p_direccion IN AGENCIA.DIRECCION%TYPE,
	p_id_banco IN AGENCIA.BANCO_ID_BANCO%TYPE,
	p_estado_agencia IN AGENCIA.ESTADO_AGENCIA%TYPE
)
IS
BEGIN
	INSERT INTO AGENCIA(NOMBRE, DIRECCION, BANCO_ID_BANCO, ESTADO_AGENCIA) VALUES(p_nombre, p_direccion, p_id_banco, p_estado_agencia);
	COMMIT;
END;
/

-- INSERTAR REGISTRO CLIENTE////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_CLIENTE(
	p_dpi_cliente IN CLIENTE.DPI_CLIENTE%TYPE,
	p_nombre IN CLIENTE.NOMBRE%TYPE,
	p_apellido IN CLIENTE.APELLIDO%TYPE,
	p_direccion IN CLIENTE.DIRECCION%TYPE,
	p_correo IN CLIENTE.CORREO%TYPE,
	p_telefono IN CLIENTE.TELEFONO%TYPE,
	p_fecha_nacimiento IN CLIENTE.FECHA_NACIMIENTO%TYPE,
	p_estado_cliente IN CLIENTE.ESTADO_CLIENTE%TYPE,
    p_foto_cliente IN CLIENTE.FOTO%TYPE,
    p_firma_cliente IN CLIENTE.FIRMA%TYPE
	
)
IS
BEGIN
	INSERT INTO CLIENTE(DPI_CLIENTE,NOMBRE,APELLIDO,DIRECCION,CORREO, TELEFONO, FECHA_NACIMIENTO, ESTADO_CLIENTE, FOTO,FIRMA) 
	VALUES(p_dpi_cliente, p_nombre, p_apellido, p_direccion, p_correo, p_telefono, p_fecha_nacimiento, p_estado_cliente,p_foto_cliente,p_firma_cliente);
	COMMIT;
END;
/

-- INSERTAR REGISTRO CUENTA/////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_CUENTA(
	p_saldo IN CUENTA.SALDO%TYPE,
	p_banco_id_banco IN CUENTA.BANCO_ID_BANCO%TYPE,
	p_tipo_cuenta IN CUENTA.TIPO_CUENTA_ID_TIPO%TYPE,
	p_estado_cuenta IN CUENTA.ESTADO_CUENTA%TYPE
)
IS
BEGIN
	INSERT INTO CUENTA(SALDO, BANCO_ID_BANCO, TIPO_CUENTA_ID_TIPO, ESTADO_CUENTA) VALUES(p_saldo, p_banco_id_banco, p_tipo_cuenta, p_estado_cuenta);
	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE INSERT_CUENTA_CLIENTE(
	p_saldo IN CUENTA.SALDO%TYPE,
	p_banco_id_banco IN CUENTA.BANCO_ID_BANCO%TYPE,
	p_tipo_cuenta IN CUENTA.TIPO_CUENTA_ID_TIPO%TYPE,
	p_estado_cuenta IN CUENTA.ESTADO_CUENTA%TYPE,
	p_dpi_cliente IN MANCOMUNADA.CLIENTE_DPI_CLIENTE%TYPE
)
IS
	p_no_cuenta CUENTA.NO_CUENTA%TYPE;
BEGIN
	INSERT INTO CUENTA(SALDO, BANCO_ID_BANCO, TIPO_CUENTA_ID_TIPO, ESTADO_CUENTA) VALUES(p_saldo, p_banco_id_banco, p_tipo_cuenta, p_estado_cuenta);
	SELECT cuenta_sequence.currval INTO p_no_cuenta FROM dual;
	INSERT INTO MANCOMUNADA(CLIENTE_DPI_CLIENTE, CUENTA_NO_CUENTA, ESTADO_MANCOMUNADA) VALUES(p_dpi_cliente, p_no_cuenta, 1);
	COMMIT;
END;
/

-- INSERTAR REGISTRO PERMISO////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_PERMISO(
	p_nombre IN PERMISO.NOMBRE%TYPE,
	p_descripcion IN PERMISO.DESCRIPCION%TYPE,
	p_estado_permiso IN PERMISO.ESTADO_PERMISO%TYPE 
)
IS
BEGIN
	INSERT INTO PERMISO(NOMBRE, DESCRIPCION, ESTADO_PERMISO) VALUES(p_nombre, p_descripcion, p_estado_permiso);
	COMMIT;
END;
/

-- INSERTAR REGISTRO ROL////////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_ROL(
	p_nombre IN ROL.NOMBRE%TYPE,
	p_estado_rol IN ROL.ESTADO_ROL%TYPE
)
IS
BEGIN
	INSERT INTO ROL(NOMBRE, ESTADO_ROL) VALUES(p_nombre, p_estado_rol);
	COMMIT;
END;
/

-- INSERTAR REGISTRO TIPO CUENTA////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_TIPO_CUENTA(
	p_nombre IN TIPO_CUENTA.NOMBRE%TYPE,
	p_estado_tipo_cuenta IN TIPO_CUENTA.ESTADO_TIPO_CUENTA%TYPE
)
IS
BEGIN
	INSERT INTO TIPO_CUENTA(NOMBRE, ESTADO_TIPO_CUENTA) VALUES(p_nombre, p_estado_tipo_cuenta);
	COMMIT;
END;
/

-- INSERTAR REGISTRO USUARIO////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_USUARIO(
	p_usuario IN USUARIO.USUARIO%TYPE,
	p_password IN USUARIO.PASSWORD%TYPE,
	p_estado_usuario IN USUARIO.ESTADO_USUARIO%TYPE,
	p_rol_id_rol IN USUARIO.ROL_ID_ROL%TYPE
)
IS
BEGIN
	INSERT INTO USUARIO(USUARIO, PASSWORD, ESTADO_USUARIO, ROL_ID_ROL) VALUES(p_usuario, p_password, p_estado_usuario, p_rol_id_rol);
	COMMIT;
END;
/

-- INSERTAR NUEVA CHEQUERA
CREATE OR REPLACE PROCEDURE INSERT_CHEQUERA(
	p_no_cuenta IN CHEQUERA.NO_CUENTA%TYPE,
	p_estado_chequera IN CHEQUERA.ESTADO_CHEQUERA%TYPE,
	p_no_cheques INTEGER
)
IS 
	p_id_chequera CHEQUERA.ID_CHEQUERA%TYPE;
BEGIN
	INSERT INTO CHEQUERA(NO_CUENTA, ESTADO_CHEQUERA) VALUES(p_no_cuenta, p_estado_chequera);
	SELECT chequera_sequence.currval INTO p_id_chequera FROM dual;
	-- CREANDO LOS CHEQUES DE CADA CHEQUERA
	FOR lc IN 1..p_no_cheques
	LOOP
		INSERT INTO CHEQUE(ID_CHEQUERA, ESTADO_CHEQUE) VALUES(p_id_chequera, 1);
	END LOOP
	-- ALMACENANDO CAMBIOS
	COMMIT;
END;
/

-- INSERTAR NUEVA TERMINAL
CREATE OR REPLACE PROCEDURE INSERT_TERMINAL(
	p_agencia_id_agencia IN TERMINAL.AGENCIA_ID_AGENCIA%TYPE,
	p_usuario_id_usuario IN TERMINAL.USUARIO_ID_USUARIO%TYPE,
	p_estado_terminal IN TERMINAL.ESTADO_TERMINAL%TYPE
)
IS
BEGIN
	INSERT INTO TERMINAL(AGENCIA_ID_AGENCIA, USUARIO_ID_USUARIO, ESTADO_TERMINAL) VALUES(p_agencia_id_agencia, p_usuario_id_usuario, p_estado_terminal);
	COMMIT;
END;
/

-- ROL PERMISO
CREATE OR REPLACE PROCEDURE INSERT_ROL_PERMISO(
	p_rol_id_rol IN ROL_PERMISO.ROL_ID_ROL%TYPE,
	p_permiso_id_permiso IN ROL_PERMISO.PERMISO_ID_PERMISO%TYPE,
	p_estado_rol_permiso IN ROL_PERMISO.ESTADO_ROL_PERMISO%TYPE
)
IS
BEGIN
	INSERT INTO ROL_PERMISO(ROL_ID_ROL, PERMISO_ID_PERMISO, ESTADO_ROL_PERMISO) VALUES(p_rol_id_rol, p_permiso_id_permiso, p_estado_rol_permiso);
	COMMIT;
END;
/

