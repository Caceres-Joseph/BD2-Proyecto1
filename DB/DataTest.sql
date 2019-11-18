------- BANCOS ---------------------------------------
CALL INSERT_BANCO('Banco1', 1);
CALL INSERT_BANCO('Banco2', 1);
CALL INSERT_BANCO('Banco3', 1);
CALL INSERT_BANCO('Banco4', 1);
CALL INSERT_BANCO('Banco5', 1);
------- BANCOS ---------------------------------------

------- AGENCIA --------------------------------------
CALL INSERT_AGENCIA('Agencia1B1', 'Agencia1B1', 1, 1);
CALL INSERT_AGENCIA('Agencia2B1', 'Agencia2B1', 1, 1);
CALL INSERT_AGENCIA('Agencia3B1', 'Agencia1B1', 1, 1);

CALL INSERT_AGENCIA('Agencia1B2', 'Agencia1B2', 2, 1);
CALL INSERT_AGENCIA('Agencia2B2', 'Agencia2B2', 2, 1);
CALL INSERT_AGENCIA('Agencia3B2', 'Agencia1B2', 2, 1);

CALL INSERT_AGENCIA('Agencia1B3', 'Agencia1B3', 3, 1);
CALL INSERT_AGENCIA('Agencia2B3', 'Agencia2B3', 3, 1);
CALL INSERT_AGENCIA('Agencia3B3', 'Agencia1B3', 3, 1);


CALL INSERT_AGENCIA('Agencia1B4', 'Agencia1B4', 4, 1);
CALL INSERT_AGENCIA('Agencia2B4', 'Agencia2B4', 4, 1);
CALL INSERT_AGENCIA('Agencia3B4', 'Agencia1B4', 4, 1);

CALL INSERT_AGENCIA('Agencia1B5', 'Agencia1B5', 5, 1);
CALL INSERT_AGENCIA('Agencia2B5', 'Agencia2B5', 5, 1);
CALL INSERT_AGENCIA('Agencia3B5', 'Agencia1B5', 5, 1);
------- AGENCIA --------------------------------------

-------------- ROLES ---------------------------------
CALL INSERT_ROL('administrador',1);
CALL INSERT_ROL('cajero',1);
CALL INSERT_ROL('operador',1);
CALL INSERT_ROL('grabador', 1);
-------------- ROLES ---------------------------------

-------------- USUARIO -------------------------------
CALL INSERT_USUARIO('usuario1','123',1,1);
CALL INSERT_USUARIO('usuarioCajero', '123',1,2);
CALL INSERT_USUARIO('usuarioOperador','123', 1, 3);
CALL INSERT_USUARIO('usuarioGrabador','123', 1, 4);
-------------- USUARIO -------------------------------

-------------- TERMINALES ----------------------------
CALL INSERT_TERMINAL(1,1,1);
CALL INSERT_TERMINAL(1,2,1);
CALL INSERT_TERMINAL(1,3,1);
CALL INSERT_TERMINAL(1,4,1);
-------------- TERMINALES ----------------------------

-------------- TIPOS DE CUENTA -----------------------
CALL INSERT_TIPO_CUENTA('ahorro',1);
CALL INSERT_TIPO_CUENTA('monetario',1);
-------------- TIPOS DE CUENTA -----------------------

-------------- CLIENTES -------------------------------
CALL INSERT_CLIENTE(1, 'Daniel', 'Garcia', 'Ciudad', 'daniel@gmail.com', '1234-5678', '10-Sep-02', 1, 'IMAGEN', 'FIRMA');
CALL INSERT_CLIENTE(3, 'Joseph', 'Caceres', 'Ciudad', 'joseph@gmail.com', '1564-5678', '10-Sep-02', 1, 'IMAGEN', 'FIRMA');
CALL INSERT_CLIENTE(2, 'Ricardo', 'Cutz', 'Ciudad', 'Ricardo@gmail.com', '1564-5678', '10-Sep-02', 1, 'IMAGEN', 'FIRMA');
-------------- CLIENTES -------------------------------

-------------- CREAR CUENTAS --------------------------
CALL CREAR_CUENTA(5000, 1, 1, 1, 1);
CALL CREAR_CUENTA(2000, 1, 1, 1, 2);
CALL CREAR_CUENTA(3000, 1, 1, 1, 3);
-------------- CREAR CUENTAS --------------------------

-------------- CHEQUERAS ------------------------------
CALL INSERT_CHEQUERA(1, 1);
CALL INSERT_CHEQUERA(2, 1);
CALL INSERT_CHEQUERA(3, 1);
-------------- CHEQUERAS ------------------------------

-------------- CHEQUE EXTERNOS ------------------------
---------------------------------------------
-- BANCO 1
CALL CHEQUE_EXTERNO(1, 1, 10, 3400, 1, 2, 2);
CALL CHEQUE_EXTERNO(1, 1, 11, 2000, 1, 2, 2);
CALL CHEQUE_EXTERNO(2, 3, 12, 4500, 1, 2, 2);
-- 3 DOCUMENTOS, MONTO 9900
---------------------------------------------
-- BANCO 2
CALL CHEQUE_EXTERNO(3, 4, 78, 5000, 2, 2, 2);
CALL CHEQUE_EXTERNO(3, 10, 79, 15000, 2, 2, 2);
