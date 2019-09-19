/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Agencias;

import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ricar
 */
public class ConsultasAgencia extends Conexion{
    
    
    public boolean save(Agencia agencia)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_AGENCIA(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, agencia.getNombre());
            call.setString(2, agencia.getDireccion());
            call.setInt(3, agencia.getId_banco());
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
    
    public boolean update(Agencia agencia)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_AGENCIA(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, agencia.getId_agencia());
            call.setString(2, agencia.getNombre());
            call.setString(3, agencia.getDireccion());
            call.setInt(4, agencia.getId_banco());
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
    
    public Agencia findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM agencia WHERE id_agencia=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Agencia a = null;
            if(rs.next())
            {
                a = new Agencia(rs.getString("nombre"), rs.getString("direccion"), rs.getInt("banco_id_banco"));
                a.setId_agencia(rs.getInt("id_agencia"));
            }
            return a;
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
            String sql = "SELECT * FROM agencia ORDER BY  id_agencia DESC";
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
