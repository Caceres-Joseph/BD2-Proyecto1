/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Chequera;

import Main.B2;
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
public class ConsultasChequera extends Conexion{
    
    public boolean createChequera(Chequera t)
    {
        Connection con = getConexion();
        try { 
//            t.imprimir();
            String cmd = "{CALL INSERT_CHEQUERA(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
       
 
            call.setInt(1, t.getNo_cuenta());
            call.setDouble(2, t.getEstado_chequera());
            
            call.execute();
             
            call.close();
            
            return true;
        } catch (Exception e) {

            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);

                B2.GuiController.mensajeConsola(e.getMessage());
            }
        }
    }
    
    public ArrayList<Chequera> listData(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite, int no_cuenta)
    {
        Connection con = getConexion();
        try {
            ArrayList<Chequera> chequeras = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList<ColumnaTabla> columnas = new ArrayList<>();
            columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.NAC,"no_cuenta", String.valueOf(no_cuenta), BDOpciones.OperadorAritmeticos.EQUAL));
            if(OpcLimite!=BDOpciones.LimitOp.NO_LIMIT)
            {
                columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.AND,"ROWNUM", String.valueOf(limite), BDOpciones.OperadorAritmeticos.LOWER_EQUAL));
            }
            String sql = "SELECT * FROM chequera WHERE "+BDOpciones.getFilters(columnas)+
                    " ORDER BY id_chequera "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Chequera c = new Chequera(rs.getInt("id_chequera"), rs.getInt("no_cuenta"), rs.getInt("estado_chequera"));
                chequeras.add(c);
            }
            return chequeras;
        } catch (Exception e) {
            System.err.println(e);
            return new ArrayList<>();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
