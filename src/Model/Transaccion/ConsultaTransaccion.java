/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Transaccion;

import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author ricar
 */
public class ConsultaTransaccion extends Conexion{
    
    
    public boolean acreditar(Transaccion t, double monto)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL ACREDITAR(?,?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            Date d = new Date();
            
            //call.setDate(1, );
            call.setInt(1, t.getUsuario_id_usuario());
            call.setInt(2, t.getTerminal_id_terminal());
            call.setInt(3, t.getCuenta_no_cuenta());
            call.setInt(4, 1);
            call.setDouble(5, monto);
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            return false;
        }
        finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean debitar(Transaccion t)
    {
        Connection con = getConexion();
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
        finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean acreditar(Transaccion cretidito, Transaccion debito)
    {
        Connection con = getConexion();
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
        finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
