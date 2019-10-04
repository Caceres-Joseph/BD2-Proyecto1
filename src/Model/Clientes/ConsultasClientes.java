/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Clientes;

import Model.BD.BDOpciones;
import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ricar
 */
public class ConsultasClientes extends Conexion{
    
    public boolean save(Cliente cliente)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_CLIENTE(?,?,?,?,?,?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, cliente.getId_cliente());
            call.setString(2, cliente.getNombre());
            call.setString(3, cliente.getApellido());
            call.setString(4, cliente.getDireccion());
            call.setString(5, cliente.getCorreo());
            call.setString(6, cliente.getTelefono());
            call.setDate(7, cliente.getFecha_nacimiento());
            call.setInt(8, cliente.getEstado_cliente());
            call.setString(9, cliente.getFoto());
            call.setString(10, cliente.getFirma());
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
            String cmd = "{CALL UPDATE_CLIENTE(?,?,?,?,?,?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, cliente.getId_cliente());
            call.setString(2, cliente.getNombre());
            call.setString(3, cliente.getApellido());
            call.setString(4, cliente.getDireccion());
            call.setString(5, cliente.getCorreo());
            call.setString(6, cliente.getTelefono());
            call.setDate(7, cliente.getFecha_nacimiento());
            call.setInt(8, cliente.getEstado_cliente());
            call.setString(9, cliente.getFoto());
            call.setString(10, cliente.getFirma());
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
            String sql = "SELECT * FROM cliente WHERE dpi_cliente=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Cliente c = null;
            if(rs.next())
            {
                c = new Cliente(rs.getInt("dpi_cliente"), rs.getString("nombre"), rs.getString("apellido")
                        , rs.getString("direccion"), rs.getString("correo")
                        , rs.getString("telefono")
                        , rs.getDate("fecha_nacimiento"), rs.getInt("estado_cliente")
                        , rs.getString("firma"), rs.getString("foto")
                );
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
            String sql = "SELECT * FROM cliente ORDER BY  dpi_cliente DESC";
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
    
    public ArrayList<Cliente> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite)
    {
        Connection con = getConexion();
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM cliente "+BDOpciones.getLimit(OpcLimite, limite)+" OREDER BY dpi_cliente "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Cliente c = new Cliente(rs.getInt("dpi_cliente")
                        ,rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("correo")
                        , rs.getString("telefono"),rs.getDate("fecha_nacimiento"), rs.getInt("estado_cliente")
                        , rs.getString("firma"), rs.getString("foto")
                );
                clientes.add(c);
            }
            return clientes;
        } catch (Exception e) {
            return new ArrayList<>();
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
    
    public ArrayList<Cliente> listDataLike(BDOpciones.Orden Opcorden, String LikeString)
    {
        Connection con = getConexion();
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM cliente WHERE nombre LIKE '%"+LikeString+"%' OR apellido LIKE '%"+LikeString+"%' OR correo LIKE '%"+LikeString+"%'  OREDER BY dpi_cliente "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Cliente c = new Cliente(rs.getInt("dpi_cliente")
                        ,rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("correo")
                        , rs.getString("telefono"),rs.getDate("fecha_nacimiento"), rs.getInt("estado_cliente")
                        , rs.getString("firma"), rs.getString("foto")
                );
                clientes.add(c);
            }
            return clientes;
        } catch (Exception e) {
            return new ArrayList<>();
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
