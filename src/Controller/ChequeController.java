/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.B2;
import Model.Cheque.Cheque;
import Model.Cheque.ConsultasCheque;
import Model.Chequera.Chequera;
import Model.Chequera.ConsultasChequera;
import Model.Transaccion.Transaccion;

/**
 *
 * @author Notebook
 */
public class ChequeController {
    
    ConsultasChequera consultas;
    ConsultasCheque cheque;
    
    public ChequeController(){
        this.consultas=new ConsultasChequera();
        this.cheque=new ConsultasCheque();
    }

    public boolean CrearChequera(int no_cuenta_origen, int estado) {

        try {

            return consultas.createChequera(new Chequera(no_cuenta_origen, estado));

        } catch (Exception e) { 
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return false;
    }
    
    
    
    public boolean EstadoCheque(int id_cheque,int no_cuenta ,int estado) {

        try {

            return cheque.estadoCheque(new Cheque(id_cheque, no_cuenta, id_cheque));

        } catch (Exception e) { 
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return false;
    }
}
