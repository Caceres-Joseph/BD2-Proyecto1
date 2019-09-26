/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TipoCuentas;

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
public class ConsultasTipoCuenta extends Conexion{
    
    public boolean save(TipoCuenta tc)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_TIPO_CUENTA(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, tc.getNombre());
            call.setInt(2, tc.getEstado_tipo_cuenta());
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
    
    
    public boolean update(TipoCuenta tc)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_TIPO_CUENTA(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, tc.getId_tipo());
            call.setString(2, tc.getNombre());
            call.setInt(3, tc.getEstado_tipo_cuenta());
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
    
    
    public TipoCuenta findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM tipo_cuenta WHERE id_tipo=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            TipoCuenta tc = null;
            if(rs.next())
            {
                tc = new TipoCuenta(rs.getString("nombre"));
                tc.setId_tipo(rs.getInt("id_tipo"));
                tc.setEstado_tipo_cuenta(rs.getInt("estado_tipo_cuenta"));
            }
            return tc;
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
            String sql = "SELECT * FROM tipo_cuenta ORDER BY  id_tipo DESC";
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
    
    public ArrayList<TipoCuenta> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite)
    {
        Connection con = getConexion();
        try {
            ArrayList<TipoCuenta> tcs = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "SELECT * FROM tipo_cuenta "+BDOpciones.getLimit(OpcLimite, limite)+" ORDER BY id_tipo "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                TipoCuenta tc = new TipoCuenta(rs.getString("nombre"));
                tc.setEstado_tipo_cuenta(rs.getInt("estado_tipo_cuenta"));
                tc.setId_tipo(rs.getInt("id_tipo"));
                tcs.add(tc);
            }
            return tcs;
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
    
    public ArrayList<TipoCuenta> listDataLike(BDOpciones.Orden Opcorden, String LikeString)
    {
        Connection con = getConexion();
        try {
            ArrayList<TipoCuenta> tcs = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "SELECT * FROM tipo_cuenta WHERE nombre LIKE '%"+LikeString+"%' ORDER BY id_tipo "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                TipoCuenta tc = new TipoCuenta(rs.getString("nombre"));
                tc.setEstado_tipo_cuenta(rs.getInt("estado_tipo_cuenta"));
                tc.setId_tipo(rs.getInt("id_tipo"));
                tcs.add(tc);
            }
            return tcs;
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
