/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Permisos;

import Model.BD.Conexion;
import java.sql.CallableStatement;
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
            String cmd = "{CALL INSERT_PERMISO(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, permiso.getNombre());
            call.setString(2, permiso.getDescripcion());
            call.execute();
            call.close();
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
            String cmd = "{CALL UPDATE_PERMISO(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, permiso.getId_permiso());
            call.setString(2, permiso.getNombre());
            call.setString(3, permiso.getDescripcion());    
            call.execute();
            call.close();
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
    
    public ResultSet listItems()
    {
        Connection con = getConexion();
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "SELECT * FROM permiso ORDER BY  id_permiso DESC";
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
