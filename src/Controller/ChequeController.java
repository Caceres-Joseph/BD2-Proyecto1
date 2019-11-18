/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.B2;
import Model.Chequera.Chequera;
import Model.Chequera.ConsultasChequera;
import Model.Transaccion.Transaccion;

/**
 *
 * @author Notebook
 */
public class ChequeController {
    
    ConsultasChequera consultas;
    
    public ChequeController(){
        this.consultas=new ConsultasChequera();
    }

    public boolean CrearChequera(int no_cuenta_origen, int estado) {

        try {

            return consultas.createChequera(new Chequera(no_cuenta_origen, estado));

        } catch (Exception e) { 
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return false;
    }
}
