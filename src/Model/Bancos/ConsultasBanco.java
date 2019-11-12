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
import java.util.ArrayList;
import Model.BD.BDOpciones;
import Model.BD.ColumnaTabla;

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
            String cmd = "{CALL INSERT_BANCO(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, banco.getNombre());
            call.setInt(2, banco.getEstado_banco());
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
            String cmd = "{CALL UPDATE_BANCO(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setString(1, banco.getNombre());
            call.setInt(2, banco.getId_banco());
            call.setInt(3, banco.getEstado_banco());
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
            String sql = "SELECT * FROM banco WHERE id_banco=? "+BDOpciones.getByState("AND", 1, "estado_banco");
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Banco b = null;            
            if(rs.next())
            {
                b = new Banco(rs.getString("nombre"));
                b.setId_banco(rs.getInt("id_banco"));
                b.setEstado_banco(rs.getInt("estado_banco"));
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
    
    /**
     * Metodo para listar los datos del banco
     * @param Opcorden Orden que se desa la data DESC, ASC
     * @param OpcLimite Si se quiere limite se pondra LIMIT, De lo contrario NO_LIMIT
     * @param limite Si se definio un limite el parametro se tomara en cuenta
     * @return 
     */
    public ArrayList<Banco> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite)
    {
        Connection con = getConexion();
        try {
            ArrayList<Banco> bancos = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList<ColumnaTabla> columnas = new ArrayList<>();
            columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.NAC,"estado_banco", "1", BDOpciones.OperadorAritmeticos.EQUAL));
            if(OpcLimite!=BDOpciones.LimitOp.NO_LIMIT)
            {
                columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.AND,"ROWNUM", String.valueOf(limite), BDOpciones.OperadorAritmeticos.LOWER_EQUAL));
            }
            String sql = "SELECT * FROM banco WHERE "+BDOpciones.getFilters(columnas)+
                    " ORDER BY id_banco "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Banco b = new Banco(rs.getString("nombre"));
                b.setId_banco(rs.getInt("id_banco"));
                b.setEstado_banco(rs.getInt("estado_banco"));
                bancos.add(b);
            }
            return bancos;
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
    public ArrayList<Banco> listDataLike(BDOpciones.Orden Opcorden, String LikeString)
    {
        Connection con = getConexion();
        try {
            ArrayList<Banco> bancos = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM banco WHERE nombre LIKE '%"+LikeString+"%' "+BDOpciones.getByState("AND", 1, "estado_banco")+
                    " ORDER BY id_banco "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Banco b = new Banco(rs.getString("nombre"));
                b.setId_banco(rs.getInt("id_banco"));
                b.setEstado_banco(rs.getInt("estado_banco"));
                bancos.add(b);
            }
            return bancos;
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
}
