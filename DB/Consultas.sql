--AUDITOR-----------------------------------------------------------------------------------------------------------------------
select * from transaccion WHERE fecha = '12/09/02';


SELECT ID_TRANSACCION,FECHA,TIPO,NATURALEZA,CODIGO_AUTORIZACION,USUARIO_ID_USUARIO,TERMINAL_ID_TERMINAL,CUENTA_NO_CUENTA,RECHAZADO,RAZON_RECHAZO,SALDO_FINAL-SALDO_INICIAL AS MONTO FROM transaccion;
--GERENCIA----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------CONSULTA 1
--CONSULTA SALDOS POR AGENCIA
SELECT agencia.id_agencia,agencia.nombre,SUM(SALDO_FINAL - SALDO_INICIAL) AS Saldos FROM TRANSACCION,TERMINAL,AGENCIA
WHERE transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia
GROUP BY agencia.id_agencia,agencia.nombre;

----------------------------------------------------------------------------------------------------------------------CONSULTA 2
SELECT cuenta.no_cuenta,cuenta.saldo,cliente.nombre FROM CUENTA,CLIENTE, MANCOMUNADA
WHERE cuenta.no_cuenta = mancomunada.cuenta_no_cuenta AND cliente.dpi_cliente = mancomunada.cliente_dpi_cliente;

----------------------------------------------------------------------------------------------------------------------CONSULTA 3
--CONSULTA CANTIDAD MÁXIMA DE DEPOSITOS HECHOS POR AGENCIA
SELECT agencia, nombre, cantidad FROM(
    --DETERMINAR EL MÁXIMO DE DEPOSITOS REALIZADOS EN CADA AGENCIA
    SELECT agencia, MAX(CONTEO) AS CANTIDAD FROM(
        SELECT transaccion.cuenta_no_cuenta AS no_cuenta,transaccion.naturaleza AS naturaleza,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,agencia.nombre AS agencia, COUNT(*) AS CONTEO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA
        WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.naturaleza = 'deposito' 
        AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia
        GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre
    )
    GROUP BY naturaleza,agencia),
    --DETERMINAR EL CONTEO DE DEPOSITOS POR TODOS LOS CLIENTES
    (SELECT transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre NOMBRE_AGENCIA, COUNT(*) AS CONTEO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA
    WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.naturaleza = 'deposito' 
    AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia
    GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre) 
    --BUSCAR EL CLIENTE QUE HIZO CADA DEPOSITO MÁXIMO
WHERE agencia = NOMBRE_AGENCIA AND cantidad = conteo;

----------------------------------------------------------------------------------------------------------------------CONSULTA 4
--CONSULTA DE CANTIDAD PAGADA EN CHEQUES

SELECT agencia,nombre, maximo FROM(
    SELECT agencia, MAX(PAGADO) AS MAXIMO FROM(
        SELECT transaccion.cuenta_no_cuenta AS no_cuenta,transaccion.naturaleza AS naturaleza,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,agencia.nombre AS agencia, SUM(SALDO_FINAL - SALDO_INICIAL) AS PAGADO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA
        WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.tipo = 'cheque' 
        AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia
        GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre
    )    
    GROUP BY agencia),
    
    (SELECT transaccion.cuenta_no_cuenta AS no_cuenta,transaccion.naturaleza AS naturaleza,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,agencia.nombre AS NOMBRE_AGENCIA, SUM(SALDO_FINAL - SALDO_INICIAL) AS PAGADO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA
    WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.tipo = 'cheque' 
    AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia
    GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre)
WHERE agencia = NOMBRE_AGENCIA AND maximo = pagado;


----------------------------------------------------------------------------------------------------------------------CONSULTA 5
--CONSULTA NUNCA HAN HECHO UN DEPOSITO EN AGENCIA 3
SELECT dpi_cliente,nombre AS dpi FROM cliente
MINUS
    SELECT dpi,nombre FROM(
    SELECT transaccion.cuenta_no_cuenta AS no_cuenta,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,COUNT(*) AS CONTEO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA
    WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.naturaleza = 'deposito' AND agencia.id_agencia = 3 
    AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia
    GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre
    );
    
select DISTINCT TERMINAL_ID_TERMINAL  from transaccion;


    
SELECT agencia.id_agencia,agencia.nombre,SUM(SALDO_FINAL - SALDO_INICIAL) AS Saldos FROM TRANSACCION,TERMINAL,AGENCIA\n" +
            "WHERE transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia\n" +
            "GROUP BY agencia.id_agencia,agencia.nombre;