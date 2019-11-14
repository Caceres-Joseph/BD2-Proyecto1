CREATE OR REPLACE TRIGGER trigger_saldo_total
AFTER UPDATE ON CUENTA
FOR EACH ROW
DECLARE
  total CUENTA.SALDO_TOTAL%TYPE;
  reserva CUENTA.SALDO_TOTAL%TYPE;
  disponible CUENTA.SALDO_TOTAL%TYPE;
BEGIN
  --SELECT CUENTA.SALDO INTO disponible FROM CUENTA WHERE CUENTA.NO_CUENTA = :old.NO_CUENTA;
  --SELECT CUENTA.SALDO_RESERVA INTO reserva FROM CUENTA WHERE CUENTA.NO_CUENTA = :old.NO_CUENTA;
  --total := reserva + disponible;
  total := :old.SALDO + :old.SALDO_RESERVA;
  UPDATE CUENTA
  SET SALDO_TOTAL = total
  WHERE NO_CUENTA = :old.NO_CUENTA;
  COMMIT;
END;
