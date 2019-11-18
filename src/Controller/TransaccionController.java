/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.B2;
import Model.Transaccion.ConsultaTransaccion;
import Model.Transaccion.Depositar;

/**
 *
 * @author ricar
 */
public class TransaccionController {

    ConsultaTransaccion consultas;

    public TransaccionController() {
        consultas = new ConsultaTransaccion();
    }

    public boolean Acreditar(int cuenta, double monto) {
        try {

            return consultas.acreditar(new Depositar(cuenta, monto));

        } catch (Exception e) {

//            return consultas.save(new Usuario(nombre, password, rol_id_rol));
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return false;
    }

    public boolean Debitar(int no_cuenta, double monto) {
        try {
            return true;
        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());

        }
        return true;
    }

    public boolean Transferencia(int no_cuenta_origen, int no_cuenta_destino, double monto) {
        try {
            return false;
        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return true;
    }
}
