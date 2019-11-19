/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Transaccion;

import Main.B2;
import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author ricar
 */
public class ConsultaTransaccion extends Conexion {

    public boolean acreditar(Transaccion t) {
        Connection con = getConexion();
        try {
            t.imprimir();
            String cmd = "{CALL ACREDITAR(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
       
 
            call.setInt(1, t.getCuenta_no_cuenta_destino());
            call.setDouble(2, t.getMonto());
            call.setInt(3, t.getUsuario_id_usuario());
            call.setInt(4, t.getTerminal_id_terminal()); 
            call.execute();
             
            call.close();
            
            return true;
        } catch (Exception e) {

            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);

                B2.GuiController.mensajeConsola(e.getMessage());
            }
        }
    }

    public boolean debitar(Transaccion t) {
        Connection con = getConexion();
        try {
            t.imprimir();
            String cmd = "{CALL DEBITAR(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
       
 
            call.setInt(1, t.getCuenta_no_cuenta_destino());
            call.setDouble(2, t.getMonto());
            call.setInt(3, t.getUsuario_id_usuario());
            call.setInt(4, t.getTerminal_id_terminal()); 
            call.execute();
             
            call.close();
            
            return true;
        } catch (Exception e) {

            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);

                B2.GuiController.mensajeConsola(e.getMessage());
            }
        }
    }
    
    
    public boolean transferir(Transaccion t) {
        Connection con = getConexion();
        try {
            t.imprimir();
            String cmd = "{CALL TRANSFERIR(?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
       
 
            call.setInt(1, t.getCuenta_no_cuenta_origen());
            call.setInt(2, t.getCuenta_no_cuenta_destino());
            call.setDouble(3, t.getMonto());
            call.setInt(4, t.getUsuario_id_usuario());
            call.setInt(5, t.getTerminal_id_terminal()); 
            call.execute();
             
            call.close();
            
            return true;
        } catch (Exception e) {

            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);

                B2.GuiController.mensajeConsola(e.getMessage());
            }
        }
    }
 
}
