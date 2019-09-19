/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cuentas;

import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ricar
 */
public class ConsultasCuenta extends Conexion{
    
    
    public boolean save(Cuenta cuenta)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_CUENTA(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setDouble(1, cuenta.getSaldo());
            call.setInt(2, cuenta.getBanco_id_banco());
            call.setInt(3, cuenta.getTipo_cuenta_id_tipo());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
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
    
    public boolean update(Cuenta cuenta)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_CUENTA(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, cuenta.getNo_cuenta());
            call.setDouble(2, cuenta.getSaldo());
            call.setInt(3, cuenta.getBanco_id_banco());
            call.setInt(4, cuenta.getTipo_cuenta_id_tipo());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
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
    
    
    public Cuenta findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM cuenta WHERE no_cuenta=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Cuenta c = null;
            if(rs.next())
            {
                c = new Cuenta(rs.getDouble("saldo"), rs.getInt("bando_id_banco"), rs.getInt("tipo_cuenta_id_cuenta"));
                c.setNo_cuenta(rs.getInt("no_cuenta"));
            }
            return c;
        } catch (Exception e) {
            System.err.println(e);
            return null;
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
    
    public ResultSet listItems()
    {
        Connection con = getConexion();
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "SELECT * FROM cuenta ORDER BY  no_cuenta DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            System.err.println(e);
            return null;
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
