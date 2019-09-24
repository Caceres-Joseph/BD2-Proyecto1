-- UPDATE STORE PROCEDURES

-- ACTUALIZAR REGISTRO BRANCO///////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_BANCO(
	p_nombre IN BANCO.NOMBRE%TYPE,
	p_id_banco IN BANCO.ID_BANCO%TYPE)
IS
BEGIN
	UPDATE BANCO SET NOMBRE=p_nombre WHERE ID_BANCO=p_id_banco;
	COMMIT;
END;
/

-- ACTUALIZAR REGISTRO AGENCIA//////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_AGENCIA(
	p_id_agencia IN AGENCIA.ID_AGENCIA%TYPE,
	p_nombre IN AGENCIA.NOMBRE%TYPE,
	p_direccion IN AGENCIA.DIRECCION%TYPE,
	p_banco_id_banco IN AGENCIA.BANCO_ID_BANCO%TYPE
)
IS 
BEGIN
	UPDATE AGENCIA SET NOMBRE=p_nombre, DIRECCION=p_direccion, BANCO_ID_BANCO=p_banco_id_banco WHERE ID_AGENCIA=p_id_agencia;
	COMMIT;
END;
/

-- ACTUALIZAR REGISTRO CLIENTE//////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_CLIENTE(
	p_dpi_cliente IN CLIENTE.DPI_CLIENTE%TYPE,
	p_nombre IN CLIENTE.NOMBRE%TYPE,
	p_direccion IN CLIENTE.DIRECCION%TYPE
)
IS
BEGIN
	UPDATE CLIENTE SET NOMBRE=p_nombre, DIRECCION=p_direccion WHERE ID_CLIENTE=p_dpi_cliente;
	COMMIT;
END;
/

-- ACTUALIZAR REGISTRO CUENTA///////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_CUENTA(
	p_no_cuenta IN CUENTA.NO_CUENTA%TYPE,
	p_saldo IN CUENTA.SALDO%TYPE,
	p_banco_id_banco IN CUENTA.BANCO_ID_BANCO%TYPE,
	p_tipo_cuenta IN CUENTA.TIPO_CUENTA_ID_TIPO%TYPE
)
IS
BEGIN
	UPDATE CUENTA SET SALDO=p_saldo, BANCO_ID_BANCO=p_banco_id_banco, TIPO_CUENTA_ID_TIPO=p_tipo_cuenta WHERE NO_CUENTA=p_no_cuenta;
	COMMIT;
END;
/

-- ACTUALIZAR REGISTRO PERMISO//////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_PERMISO(
	p_id_permiso IN PERMISO.ID_PERMISO%TYPE,
	p_nombre IN PERMISO.NOMBRE%TYPE,
	p_descripcion IN PERMISO.DESCRIPCION%TYPE
)
IS
BEGIN
	UPDATE PERMISO SET NOMBRE=p_nombre, DESCRIPCION=p_descripcion WHERE ID_PERMISO=p_id_permiso;
	COMMIT;
END;
/

-- ACTUALIZAR REGISTRO ROL//////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_ROL(
	p_id_rol IN ROL.ID_ROL%TYPE,
	p_nombre IN ROL.NOMBRE%TYPE
)
IS
BEGIN
	UPDATE ROL SET NOMBRE=p_nombre WHERE ID_ROL=p_id_rol;
	COMMIT;
END;
/

-- ACTUALIZAR TIPO CUENTA///////////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_TIPO_CUENTA(
	p_id_tipo IN TIPO_CUENTA.ID_TIPO%TYPE,
	p_nombre IN TIPO_CUENTA.NOMBRE%TYPE
)
IS 
BEGIN
	UPDATE TIPO_CUENTA SET NOMBRE= p_nombre WHERE ID_TIPO=p_id_tipo;
	COMMIT;
END;
/

-- ACTUALIZAR REGISTRO USUARIO//////////////////////////////////////////////////////////////////////////////////////////////////
CREATE OR REPLACE PROCEDURE UPDATE_USUARIO(
	p_id_usuario IN USUARIO.ID_USUARIO%TYPE,
	p_usuario IN USUARIO.USUARIO%TYPE,
	p_password IN USUARIO.PASSWORD%TYPE
)
IS
BEGIN
	UPDATE USUARIO SET USUARIO=p_usuario, PASSWORD=p_password WHERE ID_USUARIO=p_id_usuario;
	COMMIT;
END;
/






