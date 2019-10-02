/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Agencias;

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
public class ConsultasAgencia extends Conexion{
    
    
    public boolean save(Agencia agencia)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_AGENCIA(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, agencia.getNombre());
            call.setString(2, agencia.getDireccion());
            call.setInt(3, agencia.getId_banco());
            call.setInt(4, agencia.getEstado_agencia());
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
            String cmd = "{CALL UPDATE_AGENCIA(?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, agencia.getId_agencia());
            call.setString(2, agencia.getNombre());
            call.setString(3, agencia.getDireccion());
            call.setInt(4, agencia.getId_banco());
            call.setInt(5, agencia.getEstado_agencia());
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
                a.setEstado_agencia(rs.getInt("estado_agencia"));
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
 
    /**
     * Metodo para listar los datos del Agencia
     * @param Opcorden Orden que se desa la data DESC, ASC
     * @param OpcLimite Si se quiere limite se pondra LIMIT, De lo contrario NO_LIMIT
     * @param limite Si se definio un limite el parametro se tomara en cuenta
     * @return 
     */
    public ArrayList<Agencia> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite)
    {
        Connection con = getConexion();
        try {
            ArrayList<Agencia> agencias = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM agencia "+BDOpciones.getLimit(OpcLimite, limite)+" ORDER BY id_agencia "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Agencia a = new Agencia(rs.getString("nombre"), rs.getString("direccion"), rs.getInt("banco_id_banco"));
                a.setId_agencia(rs.getInt("id_agencia"));
                a.setEstado_agencia(rs.getInt("estado_agencia"));
                agencias.add(a);
            }
            return agencias;
        } catch (Exception e) {
            System.err.println(e);
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
    
    /**
     * Listar buscando comparando los campos
     * @param Opcorden Orden: ASC, DESC
     * @param LikeString Cadena que se desa buscar en los parametros
     * @return 
     */
    public ArrayList<Agencia> listDataLike(BDOpciones.Orden Opcorden, String LikeString)
    {
        Connection con = getConexion();
        try {
            ArrayList<Agencia> agencias = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM agencia WHERE nombre LIKE '%"+LikeString+"%' OR direccion LIKE '%"+LikeString+"%' ORDER BY id_agencia "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Agencia a = new Agencia(rs.getString("nombre"), rs.getString("direccion"), rs.getInt("banco_id_banco"));
                a.setId_agencia(rs.getInt("id_agencia"));
                a.setEstado_agencia(rs.getInt("estado_agencia"));
                agencias.add(a);
            }
            return agencias;
        } catch (Exception e) {
            System.err.println(e);
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
    
    public ArrayList<AgenciaBanco> listaData(BDOpciones.Orden Opcorden)
    {
        Connection con = getConexion();
        try
        {
            ArrayList<AgenciaBanco> agencias = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT agencia.id_agencia, agencia.nombre, agencia.direccion, agencia.banco_id_banco, agencia.estado_agencia, banco.nombre as banco_nombre" +
                        " FROM agencia, banco" + 
                        " WHERE agencia.estado_agencia = 1 AND agencia.banco_id_banco = banco.id_banco AND banco.estado_banco = 1"+
                        " ORDER BY agencia.id_agencia "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                AgenciaBanco a = new AgenciaBanco(rs.getString("nombre"), rs.getString("direccion"), rs.getInt("banco_id_banco"),rs.getString("banco_nombre"));
                a.setId_agencia(rs.getInt("id_agencia"));
                a.setEstado_agencia(rs.getInt("estado_agencia"));
                agencias.add(a);
            }
            return agencias;
        }
        catch(Exception e)
        {
            System.err.println(e);
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
