/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Usuarios;

import Model.BD.Conexion;
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
            PreparedStatement ps = null; 
            String sql = "INSERT INTO usuario(usuario, password) VALUES(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
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
    
    public boolean update(Usuario usuario)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            String sql = "UPDATE usuario SET usuario=?, password=? WHERE id_usuario=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getId_usuario());
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
}
