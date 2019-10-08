/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Terminal;

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
public class ConsultasTerminal extends Conexion{
    
    public boolean save(Terminal terminal)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_TERMINAL(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, terminal.getAgencia_id_agencia());
            call.setInt(2, terminal.getUsuario_id_usuario());
            call.setInt(3, terminal.getEstado_terminal());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public boolean update(Terminal terminal)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_TERMINAL(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, terminal.getId_terminal());
            call.setInt(2, terminal.getAgencia_id_agencia());
            call.setInt(3, terminal.getUsuario_id_usuario());
            call.setInt(4, terminal.getEstado_terminal());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public ArrayList<Terminal> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite)
    {
        Connection con = getConexion();
        try {
            ArrayList<Terminal> tcs = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement ps = null;
            ArrayList<ColumnaTabla> columnas = new ArrayList<>();
            columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.NAC,"estado_terminal", "1", BDOpciones.OperadorAritmeticos.EQUAL));
            if(OpcLimite!=BDOpciones.LimitOp.NO_LIMIT)
            {
                columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.AND,"ROWNUM", String.valueOf(limite), BDOpciones.OperadorAritmeticos.LOWER_EQUAL));
            }
            String sql = "SELECT * FROM terminal WHERE "+BDOpciones.getFilters(columnas)+" ORDER BY id_terminal "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Terminal t = new Terminal(rs.getInt("agencia_id_agencia"), rs.getInt("usuario_id_usuario"), rs.getInt("estado_terminal"));
                t.setId_terminal(rs.getInt("id_terminal"));
                tcs.add(t);
            }
            return tcs;
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
