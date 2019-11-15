/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ReConciliacion;

import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author Daniel
 */
public class ConsultasConciliacion extends Conexion {

    public boolean saveLote(Lote lote) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_LOTE_CONC1(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, lote.getId_lote());
            call.setInt(2, lote.getId_banco());
            call.setInt(3, lote.getNo_documentos());
            call.setDouble(4, lote.getValor());
            call.setInt(5, lote.getEstado());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
    public boolean saveCheque(ChequeConciliado lote) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_CHEQUE_CONC1(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, lote.getId_cheque());
            call.setInt(2, lote.getLote());
            call.setInt(3, lote.getEstado());
            call.setDouble(4, lote.getValor());
            call.setInt(5, lote.getCuenta());
            call.setInt(6, lote.getReferencia());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

}
