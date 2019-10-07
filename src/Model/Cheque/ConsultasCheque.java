/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cheque;

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
public class ConsultasCheque extends Conexion{
    
    public boolean update(Cheque cheque)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL UPDATE_CHEQUE(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, cheque.getId_cheque());
            call.setInt(1, cheque.getId_chequera());
            call.setInt(1, cheque.getEstado_cheque());
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
    
    public ArrayList<Cheque> listDataByChequeraID(BDOpciones.Orden Opcorden, BDOpciones.LimitOp OpcLimite, int limite, int IdChequera)
    {
        Connection con = getConexion();
        try {
            ArrayList<Cheque> cheques = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList<ColumnaTabla> columnas = new ArrayList<>();
            columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.NAC,"id_chequera", String.valueOf(IdChequera), BDOpciones.OperadorAritmeticos.EQUAL));
            if(OpcLimite!=BDOpciones.LimitOp.NO_LIMIT)
            {
                columnas.add(new ColumnaTabla(BDOpciones.OperadoresLogicos.AND,"ROWNUM", String.valueOf(limite), BDOpciones.OperadorAritmeticos.EQUAL));
            }
            String sql = "SELECT * FROM cheques WHERE "+BDOpciones.getFilters(columnas)+
                    " ORDER BY id_cheque "+BDOpciones.getOrder(Opcorden);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Cheque c = new Cheque(rs.getInt("id_cheque"), rs.getInt("id_chequera"), rs.getInt("estado_cheque"));
                cheques.add(c);
            }
            return cheques;
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
