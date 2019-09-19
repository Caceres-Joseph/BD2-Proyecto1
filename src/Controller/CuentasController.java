/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Cuentas.Cuenta;
import Model.Cuentas.ConsultasCuenta;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class CuentasController {
    ConsultasCuenta consultas;
    
    public CuentasController()
    {
        consultas = new ConsultasCuenta();
    }
    
    /**
     * Funcion para crear una Cuenta
     * @param saldo
     * @param banco_id_banco
     * @param tipo_cuenta_id_tipo
     * @return 
     */
    public boolean createCuenta(Double saldo, int banco_id_banco, int tipo_cuenta_id_tipo)
    {
        try {
            return consultas.save(new Cuenta(saldo, banco_id_banco, tipo_cuenta_id_tipo));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para poder actualizar la cuenta
     * @param id_cuenta
     * @param saldo
     * @param banco_id_banco
     * @param tipo_cuenta_id_tipo
     * @return 
     */
    public boolean updateCuenta(int id_cuenta, Double saldo, int banco_id_banco, int tipo_cuenta_id_tipo)
    {
        try {
            Cuenta c = new Cuenta(saldo, banco_id_banco, tipo_cuenta_id_tipo);
            c.setNo_cuenta(id_cuenta);
            return consultas.update(c);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para poder obtener la cuenta especifica
     * @param id
     * @return 
     */
    public Cuenta getCuenta(int id)
    {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Funcion que lista las cuentas en la BD
     * @return 
     */
    public ArrayList<Cuenta> listCuentas()
    {
        try {
            ArrayList<Cuenta> cuentas = new ArrayList<>();
            ResultSet rs = consultas.listItems();
            while(rs.next())
            {
                Cuenta c = new Cuenta(rs.getDouble("saldo"), rs.getInt("banco_id_banco"), rs.getInt("tipo_cuenta_id_tipo"));
                c.setNo_cuenta(rs.getInt("no_cuenta"));
                cuentas.add(c);
            }
            return cuentas;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
