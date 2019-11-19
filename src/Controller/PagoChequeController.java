/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.PagoCheque.ConsultaPagoCheque;
import Model.PagoCheque.PagoCheque;

/**
 *
 * @author Daniel
 */
public class PagoChequeController {
    
    private final ConsultaPagoCheque pagoCheque;
    
    public PagoChequeController(){
        pagoCheque = new ConsultaPagoCheque();
    }
    
    /**
     * Función utilizada para crear una nueva transacción de cheques
     * @param codigo_cheque cheque con el que se hará la transacción
     * @param no_cuenta_origen numero de cuenta origen al que pertenece la transacción
     * @param monto monto del cheque
     * @param terminal terminal que está ejecutando la transaccion
     * @param id_usuario id del usuario que ejecuta la transacción
     * @return 
     */
    public boolean createTransaccionCheque(int codigo_cheque, int no_cuenta_origen, double monto, int terminal, int id_usuario){
        try{
             return pagoCheque.save(new PagoCheque(codigo_cheque,no_cuenta_origen,monto,terminal,id_usuario));
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
}
