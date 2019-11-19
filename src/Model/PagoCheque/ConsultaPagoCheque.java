/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.PagoCheque;

import Main.B2;
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
     * Método utilizado para crear una nueva transacción utilizando cheques.
     *
     * @param pagoCheque
     * @return
     */
    public boolean save(PagoCheque pagoCheque) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL TRANSFERIR_CHEQUE(?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, pagoCheque.getNo_cuenta_origen());
            call.setDouble(2, pagoCheque.getMonto());
            call.setInt(3, pagoCheque.getId_usuario());
            call.setInt(4, pagoCheque.getTerminal());
            call.setInt(5, pagoCheque.getCodigo_cheque());

            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);

            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {

                B2.GuiController.mensajeConsola(e.getMessage());
                System.err.println(e);
            }
        }
    }

}
