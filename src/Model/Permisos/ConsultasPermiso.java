/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Permisos;

import Model.BD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ricar
 */
public class ConsultasPermiso extends Conexion{
    
    public boolean save(Permiso permiso)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null; 
            String sql = "INSERT INTO permiso(nombre, descripcion) VALUES(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, permiso.getNombre());
            ps.setString(2, permiso.getDescripcion());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
    public boolean update(Permiso permiso)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            String sql = "UPDATE permiso SET nombre=?, descipcion=? WHERE id_permiso=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, permiso.getNombre());
            ps.setString(2, permiso.getDescripcion());
            ps.setInt(3, permiso.getId_permiso());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    public Permiso findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM permiso WHERE id_permiso=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Permiso p = null;
            if(rs.next())
            {
                p = new Permiso(rs.getString("nombre"), rs.getString("descripcion"));
                p.setId_permiso(rs.getInt("id_permiso"));
            }
            return p;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
