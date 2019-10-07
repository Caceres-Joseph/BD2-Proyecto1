/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cuentas;

import Model.BD.BDOpciones;
import Model.BD.ColumnaTabla;
import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.Cuentas.CuentaBancoTipo;
/**
 *
 * @author ricar
 */
public class ConsultasCuenta extends Conexion{
    
    
    public boolean save(Cuenta cuenta)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_CUENTA(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setDouble(1, cuenta.getSaldo());
            call.setInt(2, cuenta.getBanco_id_banco());
            call.setInt(3, cuenta.getTipo_cuenta_id_tipo());
            call.setInt(4, cuenta.getEstado_cuenta());
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
    
    public boolean update(Cuenta cuenta)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_CUENTA(?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, cuenta.getNo_cuenta());
            call.setDouble(2, cuenta.getSaldo());
            call.setInt(3, cuenta.getBanco_id_banco());
            call.setInt(4, cuenta.getTipo_cuenta_id_tipo());
            call.setInt(5, cuenta.getEstado_cuenta());
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
    
    
    public Cuenta findById(int id)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM cuenta WHERE no_cuenta=? AND estado_cuenta=1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Cuenta c = null;
            if(rs.next())
            {
                c = new Cuenta(rs.getDouble("saldo"), rs.getInt("bando_id_banco"), rs.getInt("tipo_cuenta_id_cuenta"));
                c.setNo_cuenta(rs.getInt("no_cuenta"));
                c.setEstado_cuenta(rs.getInt("estado_cuenta"));
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
            String sql = "SELECT * FROM cuenta ORDER BY  no_cuenta DESC";
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
    
    public ArrayList<Cuenta> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite)
    {
        Connection con = getConexion();
        try {
            ArrayList<Cuenta> cuentas = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList<ColumnaTabla> columnas = new ArrayList<>();
            columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.NAC,"estado_cuenta", "1", BDOpciones.OperadorAritmeticos.EQUAL));
            if(OpcLimite!=BDOpciones.LimitOp.NO_LIMIT)
            {
                columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.AND,"ROWNUM", String.valueOf(limite), BDOpciones.OperadorAritmeticos.LOWER_EQUAL));
            }
            String sql = "SELECT * FROM cuenta WHERE "+BDOpciones.getFilters(columnas)+" ORDER BY no_cuenta "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Cuenta c = new Cuenta(rs.getDouble("saldo"), rs.getInt("banco_id_banco"), rs.getInt("tipo_cuenta_id_tipo"));
                c.setNo_cuenta(rs.getInt("no_cuenta"));
                c.setEstado_cuenta(rs.getInt("estado_cuenta"));
                cuentas.add(c);
            }
            return cuentas;
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
    
    public ArrayList<Cuenta> listDataLike(BDOpciones.Orden Opcorden, String LikeString)
    {
        Connection con = getConexion();
        try {
            ArrayList<Cuenta> cuentas = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM cuenta WHERE no_cuenta LIKE '%"+LikeString+"%' AND estado_cuenta=1 ORDER BY no_cuenta "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Cuenta c = new Cuenta(rs.getDouble("saldo"), rs.getInt("id_banco"), rs.getInt("tipo_cuenta_id_tipo"));
                c.setNo_cuenta(rs.getInt("no_cuenta"));
                c.setEstado_cuenta(rs.getInt("estado_cuenta"));
                cuentas.add(c);
            }
            return cuentas;
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
    
    public ArrayList<CuentaBancoTipo> listCuentaTipo(int dpi_cliente, int estado)
    {
        Connection con = getConexion();
        try {
            ArrayList<CuentaBancoTipo> cuentas = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql ="SELECT CLIENTE.DPI_CLIENTE AS DPI, CLIENTE.NOMBRE, CLIENTE.APELLIDO, CUENTA.NO_CUENTA, CUENTA.SALDO, BANCO.NOMBRE AS BANCO, TIPO_CUENTA.NOMBRE AS TIPO, CUENTA.ESTADO_CUENTA "+
                        "FROM CUENTA, BANCO, TIPO_CUENTA, CLIENTE, MANCOMUNADA "+
                        "WHERE "+
                        "CUENTA.TIPO_CUENTA_ID_TIPO = TIPO_CUENTA.ID_TIPO AND "+
                        "CUENTA.BANCO_ID_BANCO = BANCO.ID_BANCO AND "+
                        "CUENTA.NO_CUENTA = MANCOMUNADA.CUENTA_NO_CUENTA AND " +
                        "CLIENTE.DPI_CLIENTE = MANCOMUNADA.CLIENTE_DPI_CLIENTE AND "+
                        "CUENTA.ESTADO_CUENTA = ? AND " +
                        "CUENTA.NO_CUENTA = ?"
                        ;
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, dpi_cliente);
            rs = ps.executeQuery();
            while(rs.next())
            {
                CuentaBancoTipo cb = new CuentaBancoTipo(rs.getInt("dpi"), rs.getString("nombre"), 
                        rs.getString("apellido"), 
                        rs.getInt("no_cuenta"), rs.getDouble("saldo"), 
                        rs.getString("banco"), 
                        rs.getString("tipo"), 
                        rs.getInt("estado_cuenta"));
                cuentas.add(cb);
            }
            return cuentas;
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
