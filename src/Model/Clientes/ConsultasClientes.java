/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Clientes;

import Model.BD.Conexion;
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
            PreparedStatement ps = null; 
            String sql = "INSERT INTO cliente(nombre, direccion) VALUES(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
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
    
    
    public boolean update(Cliente cliente)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            String sql = "UPDATE cliente SET nombre=?, direccion=?  WHERE id_cliente=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setInt(3, cliente.getId_cliente());
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
}
