/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.B2;
import Model.PagoCheque.ConsultaPagoCheque;
import Model.PagoCheque.PagoCheque;

/**
 *
 * @author Daniel
 */
public class PagoChequeController {

    private final ConsultaPagoCheque pagoCheque;

    public PagoChequeController() {
        pagoCheque = new ConsultaPagoCheque();
    }

    /**
     * Función utilizada para crear una nueva transacción de cheques
     *
     * @param codigo_cheque cheque con el que se hará la transacción
     * @param no_cuenta_origen numero de cuenta origen al que pertenece la
     * transacción
     * @param monto monto del cheque 
     * @return
     */
    public boolean createTransaccionCheque(int codigo_cheque, int no_cuenta_origen, double monto) {
        try {

            int id_terminal = B2.usuario.getId_terminal();
            int id_usuario = B2.usuario.getId_usuario();
            
            return pagoCheque.save(new PagoCheque(codigo_cheque, no_cuenta_origen, monto, id_terminal, id_usuario));
        } catch (Exception e) {
            System.out.println(e);
            
            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        }
    }

}
