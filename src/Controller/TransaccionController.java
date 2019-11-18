/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.B2;
import Model.Transaccion.ConsultaTransaccion;
import Model.Transaccion.Transaccion;

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

            return consultas.acreditar(new Transaccion(cuenta, monto));

        } catch (Exception e) {

//            return consultas.save(new Usuario(nombre, password, rol_id_rol));
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return false;
    }

    public boolean Debitar(int cuenta, double monto) {
        try {

            return consultas.debitar(new Transaccion(cuenta, monto));

        } catch (Exception e) {

//            return consultas.save(new Usuario(nombre, password, rol_id_rol));
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return false;
    }

    public boolean Transferencia(int no_cuenta_origen, int no_cuenta_destino, double monto) {

        try {

            return consultas.transferir(new Transaccion(no_cuenta_origen, no_cuenta_destino, monto));

        } catch (Exception e) {

//            return consultas.save(new Usuario(nombre, password, rol_id_rol));
            B2.GuiController.mensajeConsola(e.getMessage());
        }
        return false;
    }
}
