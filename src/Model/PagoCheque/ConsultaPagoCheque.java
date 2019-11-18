/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.PagoCheque;

import Model.BD.Conexion;
import Model.Bancos.Banco;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author Daniel
 */
public class ConsultaPagoCheque extends Conexion {

    /**
     * Método utilizado para crear  una nueva transacción 
     * utilizando cheques.
     * @param pagoCheque
     * @return 
     */
    public boolean save(PagoCheque pagoCheque) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL TRANSFERIR_CHEQUE(?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, pagoCheque.getNo_cuenta_origen());
            call.setDouble(3, pagoCheque.getMonto());
            call.setInt(4, pagoCheque.getId_usuario());
            call.setInt(5, pagoCheque.getTerminal());
            call.setInt(6, pagoCheque.getCodigo_cheque());

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
