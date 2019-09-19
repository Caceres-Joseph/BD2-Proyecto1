/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Usuarios;

import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ricar
 */
public class ConsultaUsuarios extends Conexion{
    
    public boolean save(Usuario usuario)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_USUARIO(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, usuario.getUsuario());
            call.setString(2, usuario.getPassword());
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
    
    public boolean update(Usuario usuario)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_TIPO_CUENTA(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, usuario.getId_usuario());
            call.setString(2, usuario.getUsuario());
            call.setString(3, usuario.getPassword());
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
    
    public Usuario findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM usuario WHERE id_usuario=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Usuario us = null;
            if(rs.next())
            {
                us = new Usuario(rs.getString("usuario"), rs.getString("password"));
                us.setId_usuario(rs.getInt("id_usuario"));
            }
            return us;
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
            String sql = "SELECT * FROM usuario ORDER BY  id_usuario DESC";
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
