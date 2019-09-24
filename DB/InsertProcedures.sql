-- INSERT STORE PROCEDURES

-- INSERTAR REGISTRO BRANCO/////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_BANCO(
	p_nombre IN BANCO.NOMBRE%TYPE)
IS
BEGIN
	INSERT INTO BANCO(NOMBRE) VALUES (p_nombre);
	COMMIT;
END;
/

-- INSERTAR REGISTRO AGENCIA////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_AGENCIA(
	p_nombre IN AGENCIA.NOMBRE%TYPE,
	p_direccion IN AGENCIA.DIRECCION%TYPE,
	p_id_banco IN AGENCIA.BANCO_ID_BANCO%TYPE
)
IS
BEGIN
	INSERT INTO AGENCIA(NOMBRE, DIRECCION, BANCO_ID_BANCO) VALUES(p_nombre, p_direccion, p_id_banco);
	COMMIT;
END;
/

-- INSERTAR REGISTRO CLIENTE////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_CLIENTE(
	p_nombre IN CLIENTE.NOMBRE%TYPE,
	p_direccion IN CLIENTE.DIRECCION%TYPE
)
IS
BEGIN
	INSERT INTO CLIENTE(NOMBRE, DIRECCION) VALUES(p_nombre, p_direccion);
	COMMIT;
END;
/

-- INSERTAR REGISTRO CUENTA/////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_CUENTA(
	p_saldo IN CUENTA.SALDO%TYPE,
	p_banco_id_banco IN CUENTA.BANCO_ID_BANCO%TYPE,
	p_tipo_cuenta IN CUENTA.TIPO_CUENTA_ID_TIPO%TYPE
)
IS
BEGIN
	INSERT INTO CUENTA(SALDO, BANCO_ID_BANCO, TIPO_CUENTA_ID_TIPO) VALUES(p_saldo, p_banco_id_banco, p_tipo_cuenta);
	COMMIT;
END;
/

-- INSERTAR REGISTRO PERMISO////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_PERMISO(
	p_nombre IN PERMISO.NOMBRE%TYPE,
	p_descripcion IN PERMISO.DESCRIPCION%TYPE
)
IS
BEGIN
	INSERT INTO PERMISO(NOMBRE, DESCRIPCION) VALUES(p_nombre, p_descripcion);
	COMMIT;
END;
/

-- INSERTAR REGISTRO ROL////////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_ROL(
	p_nombre IN ROL.NOMBRE%TYPE
)
IS
BEGIN
	INSERT INTO ROL(NOMBRE) VALUES(p_nombre);
	COMMIT;
END;
/

-- INSERTAR REGISTRO TIPO CUENTA////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_TIPO_CUENTA(
	p_nombre IN TIPO_CUENTA.NOMBRE%TYPE
)
IS
BEGIN
	INSERT INTO TIPO_CUENTA(NOMBRE) VALUES(p_nombre);
	COMMIT;
END;
/

-- INSERTAR REGISTRO USUARIO////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE INSERT_USUARIO(
	p_usuario IN USUARIO.USUARIO%TYPE,
	p_password IN USUARIO.PASSWORD%TYPE
)
IS
BEGIN
	INSERT INTO USUARIO(USUARIO, PASSWORD) VALUES(p_usuario, p_password);
	COMMIT;
END;
/