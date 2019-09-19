/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Clientes;

import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ricar
 */
public class ConsultasClientes extends Conexion{
    
    public boolean save(Cliente cliente)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_CLIENTE(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, cliente.getNombre());
            call.setString(2, cliente.getDireccion());
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
    
    
    public boolean update(Cliente cliente)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_CLIENTE(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1,cliente.getId_cliente());
            call.setString(2, cliente.getNombre());
            call.setString(3, cliente.getDireccion());
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
    
    public Cliente findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM cliente WHERE id_cliente=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Cliente c = null;
            if(rs.next())
            {
                c = new Cliente(rs.getString("nombre"), rs.getString("direccion"));
                c.setId_cliente(rs.getInt("id_cliente"));
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
            String sql = "SELECT * FROM cliente ORDER BY  id_cliente DESC";
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
