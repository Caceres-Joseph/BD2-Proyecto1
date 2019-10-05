/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Roles;

import Model.BD.BDOpciones;
import Model.BD.ColumnaTabla;
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
public class ConsultasRoles extends Conexion{
    
    public boolean save(Rol rol)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_ROL(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, rol.getNombre());
            call.setInt(2, rol.getEstado_rol());
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
    
    public boolean update(Rol rol)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_ROL(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, rol.getId_rol());
            call.setString(2, rol.getNombre());
            call.setInt(3, rol.getEstado_rol());
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
    
    
    public Rol findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM rol WHERE id_rol=? AND estado_rol=1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Rol r = null;
            if(rs.next())
            {
                r = new Rol(rs.getString("nombre"));
                r.setId_rol(rs.getInt("id_rol"));
                r.setEstado_rol(rs.getInt("estado_rol"));
            }
            return r;
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
            String sql = "SELECT * FROM rol ORDER BY  id_rol DESC";
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
    
    public ArrayList<Rol> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite)
    {
        Connection con = getConexion();
        try {
            ArrayList<Rol> roles = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement ps = null;
            ArrayList<ColumnaTabla> columnas = new ArrayList<>();
            columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.NAC,"estado_rol", "1", BDOpciones.OperadorAritmeticos.EQUAL));
            if(OpcLimite!=BDOpciones.LimitOp.NO_LIMIT)
            {
                columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.AND,"ROWNUM", String.valueOf(limite), BDOpciones.OperadorAritmeticos.EQUAL));
            }
            String sql = "SELECT * FROM rol WHERE "+BDOpciones.getFilters(columnas)+" ORDER BY id_rol "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Rol r = new Rol(rs.getString("nombre"));
                r.setEstado_rol(rs.getInt("estado_rol"));
                r.setId_rol(rs.getInt("id_rol"));
                roles.add(r);
            }
            return roles;
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
    
    public ArrayList<Rol> listDataLike(BDOpciones.Orden Opcorden, String LikeString)
    {
        Connection con = getConexion();
        try {
            ArrayList<Rol> roles = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "SELECT * FROM rol WHERE nombre LIKE '%"+LikeString+"%' AND estado_rol=1 ORDER BY id_rol "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Rol r = new Rol(rs.getString("nombre"));
                r.setEstado_rol(rs.getInt("estado_rol"));
                r.setId_rol(rs.getInt("id_rol"));
                roles.add(r);
            }
            return roles;
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
