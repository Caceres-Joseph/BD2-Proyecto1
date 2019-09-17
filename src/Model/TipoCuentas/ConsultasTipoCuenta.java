/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TipoCuentas;

import Model.BD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ricar
 */
public class ConsultasTipoCuenta extends Conexion{
    
    public boolean save(TipoCuenta tc)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null; 
            String sql = "INSERT INTO tipo_cuenta(nombre) VALUES(?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, tc.getNombre());
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
    
    
    public boolean update(TipoCuenta tc)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            String sql = "UPDATE tipo_cuenta SET nombre=? WHERE id_tipo=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tc.getNombre());
            ps.setInt(2, tc.getId_tipo());
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
    
    
    public TipoCuenta findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM tipo_cuenta WHERE id_tipo=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            TipoCuenta tc = null;
            if(rs.next())
            {
                tc = new TipoCuenta(rs.getString("nombre"));
                tc.setId_tipo(rs.getInt("id_tipo"));
            }
            return tc;
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
