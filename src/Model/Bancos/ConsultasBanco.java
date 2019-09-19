/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bancos;

/**
 *
 * @author ricar
 */
import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultasBanco extends Conexion{
    
    /**
     * Guarda un banco en la BD
     * @param banco
     * @return Boolean T si fue exitoso, F si no
     */
    public boolean save(Banco banco)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_BANCO(?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, banco.getNombre());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    /**
     * Actualiza un nuevo Banco
     * @param banco
     * @return T si fue exitoso, F si no lo fue
     */
    public boolean update(Banco banco)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_BANCO(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, banco.getNombre());
            call.setInt(2, banco.getId_banco());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    /**
     * Busca un banco en especifico
     * @param id
     * @return el banco que encontro
     */
    public Banco findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM banco WHERE id_banco=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Banco b = null;            
            if(rs.next())
            {
                b = new Banco(rs.getString("nombre"));
                b.setId_banco(rs.getInt("id_banco"));
            }
            return b;
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
    
    /**
     * List todos los elementos de bancos del mas reciente al menos
     * @return 
     */
    public ResultSet listItems()
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM banco ORDER BY  id_banco DESC";
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
