/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cuentas;

import Model.BD.Conexion;
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
            PreparedStatement ps = null; 
            String sql = "INSERT INTO cuenta(saldo, banco_id_banco, tipo_cuenta_id_tipo) VALUES(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setDouble(1, cuenta.getSaldo());
            ps.setInt(2, cuenta.getBanco_id_banco());
            ps.setInt(3, cuenta.getTipo_cuenta_id_tipo());
            ps.execute();
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
            PreparedStatement ps = null;
            String sql = "UPDATE cuenta SET saldo=?, banco_id_banco=?, tipo_cuenta_id_tipo=?  WHERE no_cuenta=?";
            ps = con.prepareStatement(sql);    
            ps.setDouble(1, cuenta.getSaldo());
            ps.setInt(2, cuenta.getBanco_id_banco());
            ps.setInt(3, cuenta.getTipo_cuenta_id_tipo());
            ps.setInt(4, cuenta.getNo_cuenta());
            ps.execute();
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
}
