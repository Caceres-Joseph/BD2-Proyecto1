/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Transaccion;

import Main.B2;

/**
 *
 * @author Notebook
 */
public class Depositar {

    int id_transaccion;
    int usuario_id_usuario;
    int terminal_id_terminal;
    int cuenta_no_cuenta;
    int estado_transaccion;
    double monto;

    public Depositar(int cuenta_no_cuenta, double monto) {

        
        this.terminal_id_terminal = B2.usuario.getId_terminal();
        this.usuario_id_usuario = B2.usuario.getId_usuario();
        this.cuenta_no_cuenta = cuenta_no_cuenta;
        this.monto = monto;
        this.estado_transaccion = 0;
        this.id_transaccion=0;

    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public int getUsuario_id_usuario() {
        return usuario_id_usuario;
    }

    public void setUsuario_id_usuario(int usuario_id_usuario) {
        this.usuario_id_usuario = usuario_id_usuario;
    }

    public int getTerminal_id_terminal() {
        return terminal_id_terminal;
    }

    public void setTerminal_id_terminal(int terminal_id_terminal) {
        this.terminal_id_terminal = terminal_id_terminal;
    }

    public int getCuenta_no_cuenta() {
        return cuenta_no_cuenta;
    }

    public void setCuenta_no_cuenta(int cuenta_no_cuenta) {
        this.cuenta_no_cuenta = cuenta_no_cuenta;
    }

    public int getEstado_transaccion() {
        return estado_transaccion;
    }

    public void setEstado_transaccion(int estado_transaccion) {
        this.estado_transaccion = estado_transaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
    public void imprimir(){
        System.out.println("Monto:");
        System.out.println(monto);
        System.out.println("Terminal:");
        System.out.println(terminal_id_terminal);
        System.out.println("Usuario:");
        System.out.println(usuario_id_usuario);
        System.out.println("Cuenta:");
        System.out.println(cuenta_no_cuenta);
    }

}
