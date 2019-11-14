-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE CAMBIAR_ESTADO_CUENTA(
  p_no_cuenta IN CUENTA.NO_CUENTA%TYPE,
  p_estado_cuenta IN CUENTA.NO_CUENTA%TYPE
)
IS
  cuenta_existe INTEGER;
BEGIN
  SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta;
  IF cuenta_existe = 1 THEN
    UPDATE CUENTA SET CUENTA.ESTADO_CUENTA = p_estado_cuenta WHERE CUENTA.NO_CUENTA = p_no_cuenta;
    COMMIT;
  ELSE
    raise_application_error(-20456,'Cuenta no existe!');
  END IF;
END;
/
-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE INSERT_CHEQUERA(
	p_no_cuenta IN CHEQUERA.NO_CUENTA%TYPE,
	p_estado_chequera IN CHEQUERA.ESTADO_CHEQUERA%TYPE
)
IS
	cuenta_existe INTEGER;
  estado_cuenta CUENTA.ESTADO_CUENTA%TYPE;
	num_chequeras INTEGER;
	r_inf CHEQUERA.RANGO_INF%TYPE;
	r_sup CHEQUERA.RANGO_SUP%TYPE;
BEGIN
	-- VERIFICANDO QUE LA CUENTA EXISTE...
	SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta;
	IF cuenta_existe = 1 THEN
    SELECT CUENTA.ESTADO_CUENTA INTO estado_cuenta FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta;
    IF estado_cuenta = 1 THEN
      SELECT COUNT(*) INTO num_chequeras FROM CHEQUERA WHERE CHEQUERA.NO_CUENTA = p_no_cuenta; --CONTANDO CHQUERAS
      r_inf := (num_chequeras * 100) + 1; -- CALCULANDO RANGO INFERIOR
      r_sup := r_inf + 99; -- CALCULANDO RANGO SUPERIOR
      INSERT INTO CHEQUERA(NO_CUENTA, ESTADO_CHEQUERA, RANGO_INF, RANGO_SUP) VALUES(p_no_cuenta, p_estado_chequera, r_inf, r_sup); -- CREANDO LA CHEQUERA
      COMMIT; -- COMMITIANDO LOS CAMBIOS
    ELSE
      raise_application_error(-20456, 'La cuenta especificada esta bloqueada, no puede realizar operaciones');
    END IF;
	ELSE
		raise_application_error(-20456,'Chequera no solicitada, Cuenta indicada no existe!');
		ROLLBACK;
	END IF;
	-- FIN VERIFICANDO
END;
/
-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSFERIR(
    p_no_cuenta_origen IN CUENTA.NO_CUENTA%TYPE,
    p_no_cuenta_destino IN CUENTA.NO_CUENTA%TYPE,
    monto IN CUENTA.SALDO%TYPE,
    p_usuario_id_usuario IN USUARIO.ID_USUARIO%TYPE,
    p_terminal_id_terminal IN TERMINAL.ID_TERMINAL%TYPE
)
IS
    saldo_inicial_origen CUENTA.SALDO%TYPE;
    saldo_final_origen CUENTA.SALDO%TYPE;

    saldo_inicial_destino CUENTA.SALDO%TYPE;
    saldo_final_destino CUENTA.SALDO%TYPE;

		origen_existe INTEGER;
		destino_existe INTEGER;

    origen_estado CUENTA.ESTADO_CUENTA%TYPE;
    destino_estado CUENTA.ESTADO_CUENTA%TYPE;

    disponible_origen CUENTA.SALDO%TYPE;
    reserva_origen CUENTA.SALDO%TYPE;

    disponible_destino CUENTA.SALDO%TYPE;
    reserva_destino CUENTA.SALDO%TYPE;

BEGIN
		SELECT COUNT(*) INTO origen_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
		SELECT COUNT(*) INTO destino_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
		IF origen_existe = 1 AND destino_existe = 1 THEN
    -- VER SI ESTAN DISPONIBLES LAS CUENTAS
      SELECT CUENTA.ESTADO_CUENTA INTO origen_estado FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
      SELECT CUENTA.ESTADO_CUENTA INTO destino_estado FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
      IF origen_estado = 1 AND destino_estado = 1 THEN
        -- TOMANDO DE LA CUENTA DE ORIGEN LOS DATOS:
          SELECT CUENTA.SALDO INTO saldo_inicial_origen FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen FOR UPDATE;
        -- TOMANDO DE LA CUENTA DE DESTINO LOS DATOS:
          SELECT CUENTA.SALDO INTO saldo_inicial_destino FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino FOR UPDATE;
        -- REALIZANDO COMPROBACION SI EXISTEN FONDOS
        IF saldo_inicial_origen >= monto THEN
            -- ES VALIDA LA TRANSACCION
            -- DEBITO DEL ORIGEN...
            saldo_final_origen := saldo_inicial_origen - monto;
            -- ACREDITO AL DESTINO
            saldo_final_destino := saldo_inicial_destino + monto;
            -- INSERTANDO LOS DEBITOS
            INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
            VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'efectivo', 'debito', saldo_inicial_origen, saldo_final_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1);
            -- INSERTANDO LOS CREDITOS
            INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
            VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'efectivo', 'credito', saldo_inicial_destino, saldo_final_destino, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_destino, 1);
            UPDATE CUENTA SET SALDO = saldo_final_origen WHERE NO_CUENTA = p_no_cuenta_origen;
            -- ACTUALIZO EL ORIGEN
            SELECT CUENTA.SALDO, CUENTA.SALDO_RESERVA INTO disponible_origen, reserva_origen FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
            UPDATE CUENTA SET SALDO_TOTAL = (disponible_origen + reserva_origen) WHERE NO_CUENTA = p_no_cuenta_origen;
            ----------------------
            -- ACTUALIZO EL DESTINO
            UPDATE CUENTA SET SALDO = saldo_final_destino WHERE NO_CUENTA = p_no_cuenta_destino;
            ----------------------
            SELECT CUENTA.SALDO, CUENTA.SALDO_RESERVA INTO disponible_destino, reserva_destino FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
            UPDATE CUENTA SET SALDO_TOTAL = (disponible_destino + reserva_destino) WHERE NO_CUENTA = p_no_cuenta_destino;
            ----------------------
            COMMIT;
        ELSE
            -- NO PROCEDE LA TRANSACCION
            raise_application_error(-20456,'Saldo no es suficiente para cubrir el monto solicitado');
						ROLLBACK;
        END IF;
      ELSE
        raise_application_error(-20456, 'No se puede realizar transaccion con cuenta(s) bloqueadas');
        ROLLBACK;
      END IF;
		ELSE
			-- ALGUNA DE LAS CUENTAS NO EXISTE
			raise_application_error(-20456,'Una de las cuentas especificadas no existe');
		END IF;
END;
/
-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TRANSFERIR_CHEQUE(
    p_no_cuenta_origen IN CUENTA.NO_CUENTA%TYPE,
    monto IN CUENTA.SALDO%TYPE,
    p_usuario_id_usuario IN USUARIO.ID_USUARIO%TYPE,
    p_terminal_id_terminal IN TERMINAL.ID_TERMINAL%TYPE,
		p_correlativo IN CHEQUE.CORRELATIVO%TYPE
)
IS
    saldo_inicial_origen CUENTA.SALDO%TYPE;
    saldo_final_origen CUENTA.SALDO%TYPE;

    estado_cheque CHEQUE.ESTADO_CHEQUE%TYPE;

		cuenta_existe INTEGER;
		num_chequeras INTEGER;
		rango INTEGER;
		id_chequera CHEQUERA.ID_CHEQUERA%TYPE;

		cheque_existe INTEGER;

    estado_cuenta CUENTA.ESTADO_CUENTA%TYPE;

    reserva CUENTA.SALDO%TYPE;
    disponible CUENTA.SALDO%TYPE;
BEGIN
		SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen; -- COMPROBACION DE QUE LA CUENTA EXISTE
		IF cuenta_existe = 1 THEN
      SELECT CUENTA.ESTADO_CUENTA INTO estado_cuenta FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
      IF estado_cuenta = 1 THEN
        -- TOMANDO SALDO INICIAL
        SELECT CUENTA.SALDO INTO saldo_inicial_origen FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen FOR UPDATE;
        -- VERIFICANDO QUE EL CHEQUE EXISTE
        SELECT COUNT(*) INTO num_chequeras FROM CHEQUERA WHERE CHEQUERA.NO_CUENTA = p_no_cuenta_origen;
        rango := (num_chequeras*100); -- CALCULANDO LOS CHEQUES
        IF p_correlativo <= rango THEN
          -- OBTENGO EL ID DE LA CHEQUERA
          SELECT CHEQUERA.ID_CHEQUERA INTO id_chequera FROM CHEQUERA WHERE p_correlativo <= CHEQUERA.RANGO_SUP AND p_correlativo >= CHEQUERA.RANGO_INF AND CHEQUERA.NO_CUENTA = p_no_cuenta_origen;
          -- VERIFICANDO SI EL CHEQUE YA EXISTE EN LA TABLA DE CHEQUES
          SELECT COUNT(*) INTO cheque_existe FROM CHEQUE WHERE CHEQUE.CORRELATIVO = p_correlativo AND CHEQUE.ID_CHEQUERA = id_chequera;
          IF cheque_existe = 0 THEN
            -- PUEDO PROCEDER A TRANSFERIR
            IF saldo_inicial_origen >= monto THEN
              -- ES VALIDA LA TRANSACCION
              -- DEBITO DEL ORIGEN...
              saldo_final_origen := saldo_inicial_origen - monto;
              INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
                VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'debito', saldo_inicial_origen, saldo_final_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1);
              UPDATE CUENTA SET SALDO = saldo_final_origen WHERE NO_CUENTA = p_no_cuenta_origen;
              ------------------------------------------
              SELECT CUENTA.SALDO, CUENTA.SALDO_RESERVA INTO disponible, reserva FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
              UPDATE CUENTA SET SALDO_TOTAL = (disponible+reserva) WHERE CUENTA.NO_CUENTA = p_no_cuenta_origen;
              ------------------------------------------
              INSERT INTO CHEQUE(CORRELATIVO, ID_CHEQUERA, ESTADO_CHEQUE) VALUES(p_correlativo, id_chequera, 4); -- INSERTANDO CHEQUE PAGADO
              COMMIT;
            ELSE
              raise_application_error(-20456,'Saldo no es suficiente para cubrir el monto solicitado');
              ROLLBACK;
            END IF;
          ELSE
            -- EL CHEQUE YA EXISTE DEBO VERIFICAR SU ESTADO
            SELECT CHEQUE.ESTADO_CHEQUE INTO estado_cheque FROM CHEQUE WHERE CHEQUE.CORRELATIVO = p_correlativo AND CHEQUE.ID_CHEQUERA = id_chequera;
            -- ROBADO (1), BLOQUEADO (2), PERDIDO (3), PAGAOD(4), ACTIVO (SOLO SI ESTÁ DENTRO DEL RANGO) (5)
            IF estado_cheque = 1 THEN
              -- EL CHEQUE FUE REPORTADO COMO ROBADO
              INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION,RECHAZADO,RAZON_RECHAZO)
                VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'debito', saldo_inicial_origen, saldo_inicial_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1,1,'REPORTADO COMO ROBADO');
            ELSIF estado_cheque = 2 THEN
              -- EL CHEQUE FUE BLOQUEADO
              INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION,RECHAZADO,RAZON_RECHAZO)
                VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'debito', saldo_inicial_origen, saldo_inicial_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1,1,'CHEQUE BLOQUEADO');
            ELSIF estado_cheque = 3 THEN
              -- EL CHEQUE FUE REPORTADO COMO PERDIDO
              INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION,RECHAZADO,RAZON_RECHAZO)
                VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'debito', saldo_inicial_origen, saldo_inicial_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1,1,'CHEQUE PERDIDO');
            ELSIF estado_cheque = 4 THEN
              -- EL CHEQUE YA FUE PAGADO
              INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION,RECHAZADO,RAZON_RECHAZO)
                VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'cheque', 'debito', saldo_inicial_origen, saldo_inicial_origen, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_origen, 1,1,'CHEQUE YA HA SIDO PAGADO');
            END IF;
          END IF;
        ELSE
          raise_application_error(-20456, 'Cheque no existe en el sistema para la cuenta especificada');
        END IF;
      ELSE
        raise_application_error(-20456, 'La cuenta especificada no esta activa');
      END IF;
		ELSE
				-- LA CUENTA NO EXISTE
				raise_application_error(-20456, 'Cuenta especificada no existe');
		END IF;
END;
/
-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ACREDITAR(
	p_no_cuenta_destino IN CUENTA.NO_CUENTA%TYPE,
	p_monto IN CUENTA.NO_CUENTA%TYPE,
	p_usuario_id_usuario IN USUARIO.ID_USUARIO%TYPE,
	p_terminal_id_terminal IN TERMINAL.ID_TERMINAL%TYPE
)
IS
	cuenta_existe INTEGER;
	saldo_final CUENTA.SALDO%TYPE;
	saldo_inicial CUENTA.SALDO%TYPE;
  estado_cuenta_destino CUENTA.ESTADO_CUENTA%TYPE;
  reserva CUENTA.SALDO%TYPE;
  disponible CUENTA.SALDO%TYPE;
BEGIN
	SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
	IF p_no_cuenta_destino = 1 THEN
    SELECT CUENTA.ESTADO_CUENTA INTO estado_cuenta_destino FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
    IF estado_cuenta_destino = 1 THEN
      SELECT CUENTA.SALDO INTO saldo_inicial FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
      saldo_final := saldo_inicial + p_monto;
      INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
      VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'efectivo', 'credito', saldo_inicial, saldo_final, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_destino, 1);
      UPDATE CUENTA SET SALDO = saldo_final WHERE NO_CUENTA = p_no_cuenta_destino;
      -------------------------------
      SELECT CUENTA.SALDO, CUENTA.SALDO_RESERVA INTO disponible, reserva FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
      UPDATE CUENTA SET SALDO_TOTAL = (disponible + reserva) WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
      -------------------------------
      COMMIT;
    ELSE
      raise_application_error(-20456, 'La cuenta de destino esta bloqueada, no puede acreditar');
    END IF;
	ELSE
		-- ERROR
		raise_application_error(-20456,'Acreditacion no posible, Cuenta no existe');
		ROLLBACK;
	END IF;
END;
/
-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE DEBITAR(
	p_no_cuenta_destino IN CUENTA.NO_CUENTA%TYPE,
	p_monto IN CUENTA.NO_CUENTA%TYPE,
	p_usuario_id_usuario IN USUARIO.ID_USUARIO%TYPE,
	p_terminal_id_terminal IN TERMINAL.ID_TERMINAL%TYPE
)
IS
	cuenta_existe INTEGER;
	saldo_final CUENTA.SALDO%TYPE;
	saldo_inicial CUENTA.SALDO%TYPE;
  estado_cuenta_destino CUENTA.ESTADO_CUENTA%TYPE;
  reserva CUENTA.SALDO%TYPE;
  disponible CUENTA.SALDO%TYPE;
BEGIN
	SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
	IF p_no_cuenta_destino = 1 THEN
    SELECT CUENTA.ESTADO_CUENTA INTO estado_cuenta_destino FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
    IF estado_cuenta_destino = 1 THEN
      SELECT CUENTA.SALDO INTO saldo_inicial FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
  		IF saldo_inicial >= p_monto THEN
  			saldo_final := saldo_inicial - p_monto;
  			INSERT INTO TRANSACCION(FECHA, TIPO, NATURALEZA, SALDO_INICIAL, SALDO_FINAL, CODIGO_AUTORIZACION, USUARIO_ID_USUARIO, TERMINAL_ID_TERMINAL, CUENTA_NO_CUENTA, ESTADO_TRANSACCION)
  			VALUES (TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS'), 'efectivo', 'debito', saldo_inicial, saldo_final, 1, p_usuario_id_usuario, p_terminal_id_terminal, p_no_cuenta_destino, 1);
  			UPDATE CUENTA SET SALDO = saldo_final WHERE NO_CUENTA = p_no_cuenta_destino;
        -------------------------------
        SELECT CUENTA.SALDO, CUENTA.SALDO_RESERVA INTO disponible, reserva FROM CUENTA WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
        UPDATE CUENTA SET SALDO_TOTAL = (disponible + reserva) WHERE CUENTA.NO_CUENTA = p_no_cuenta_destino;
        -------------------------------
  			COMMIT;
  		ELSE
  			-- ERROR
  			ROLLBACK;
  			raise_application_error(-20456,'El saldo de la cuenta es insuficiente para realizar la transaccion');
  		END IF;
    ELSE
      raise_application_error(-20456, 'La cuenta a debitar no esta activa, no puede realizar la operacion');
    END IF;
	ELSE
		-- ERROR'
		ROLLBACK;
		raise_application_error(-20456,'El debito no es posible, Cuenta no existe');
	END IF;
END;
/
-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE REPORTAR_CHEQUE(
	p_correlativo IN CHEQUE.CORRELATIVO%TYPE,
	no_cuenta IN CUENTA.NO_CUENTA%TYPE,
	estado_cheque IN CHEQUE.ESTADO_CHEQUE%TYPE
)
IS
	cuenta_existe INTEGER;
	id_chequera CHEQUERA.ID_CHEQUERA%TYPE;
	num_chequeras INTEGER;
	rango CHEQUERA.RANGO_SUP%TYPE;
	actualiza_cheque INTEGER;
  estado_cuenta CUENTA.NO_CUENTA%TYPE;
BEGIN
	--COMPROBACION DE EXISTE CUENTA
	SELECT COUNT(*) INTO cuenta_existe FROM CUENTA WHERE CUENTA.NO_CUENTA = no_cuenta;
	IF cuenta_existe = 1 THEN
    SELECT CUENTA.ESTADO_CUENTA INTO estado_cuenta FROM CUENTA WHERE CUENTA.NO_CUENTA = no_cuenta;
    IF estado_cuenta = 1 THEN
      -- COMPROBACION QUE LA CHEQUERA EXISTA
  		SELECT COUNT(*) INTO num_chequeras FROM CHEQUERA WHERE CHEQUERA.NO_CUENTA = no_cuenta;
  		rango := (num_chequeras * 100);
  		IF p_correlativo <= rango THEN
  			SELECT CHEQUERA.ID_CHEQUERA INTO id_chequera FROM CHEQUERA WHERE p_correlativo <= CHEQUERA.RANGO_SUP AND p_correlativo >= CHEQUERA.RANGO_INF AND CHEQUERA.NO_CUENTA = no_cuenta;
  			SELECT COUNT(*) INTO actualiza_cheque FROM CHEQUE WHERE CHEQUE.CORRELATIVO = p_correlativo AND CHEQUE.ID_CHEQUERA = id_chequera;
  			IF actualiza_cheque = 0 THEN
  				--EL CHEQUE NO EXISTIA EN LA BD ASI QUE LO CREO
  				INSERT INTO CHEQUE(CORRELATIVO,ID_CHEQUERA, ESTADO_CHEQUE) VALUES(p_correlativo, id_chequera, estado_cheque);
  				COMMIT;
  			ELSE
  				-- EL CHEQUE YA EXISTE EN LA BD ASI QUE ACTUALIZO EL ESTADO
  				UPDATE CHEQUE SET ESTADO_CHEQUE=estado_cheque WHERE ID_CHEQUERA = id_chequera AND CORRELATIVO=p_correlativo;
  				COMMIT;
  			END IF;
  		ELSE
  			raise_application_error(-20456,'Cheque especificado no existe');
  		END IF;
    ELSE
      raise_application_error(-20456, 'La cuenta especificada esta bloqueada');
    END IF;
	ELSE
		raise_application_error(-20456,'Cuenta solicitada no Existe');
	END IF;
END;
/
-- --------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE CREAR_CUENTA(
	p_saldo_inicial IN CUENTA.SALDO%TYPE,
	p_banco_id_banco IN CUENTA.BANCO_ID_BANCO%TYPE,
	p_tipo_cuenta IN CUENTA.TIPO_CUENTA_ID_TIPO%TYPE,
	p_estado_cuenta IN CUENTA.ESTADO_CUENTA%TYPE,
	p_dpi_cliente IN CLIENTE.DPI_CLIENTE%TYPE
)
IS
	cliente_existe INTEGER;
	recent_cuenta CUENTA.NO_CUENTA%TYPE;
BEGIN
	SELECT COUNT(*) INTO cliente_existe FROM CLIENTE WHERE CLIENTE.DPI_CLIENTE = p_dpi_cliente;
	IF cliente_existe = 1 THEN
		-- CREO SU CUENTA
		INSERT INTO CUENTA(SALDO, SALDO_TOTAL, SALDO_RESERVA, BANCO_ID_BANCO, TIPO_CUENTA_ID_TIPO, ESTADO_CUENTA) VALUES(p_saldo_inicial, p_saldo_inicial, 0, p_banco_id_banco, p_tipo_cuenta, p_estado_cuenta)
			RETURNING CUENTA.NO_CUENTA INTO recent_cuenta;
		-- HAGO LA ASOCIACION
		INSERT INTO MANCOMUNADA(CLIENTE_DPI_CLIENTE, CUENTA_NO_CUENTA, ESTADO_MANCOMUNADA) VALUES(p_dpi_cliente, recent_cuenta, 1);
		COMMIT;
	ELSE
		raise_application_error(-20456,'Cliente solicitada no existe');
	END IF;
END;
/
