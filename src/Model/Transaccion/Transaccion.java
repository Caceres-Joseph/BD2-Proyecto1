/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Transaccion;

import java.sql.Date;

/**
 *
 * @author ricar
 */
public class Transaccion {
    int id_transaccion;
    Date fecha;
    String Tipo;
    String Naturaleza;
    double saldo_inicial;
    double saldo_final;
    int codigo_autorizacion;
    int rechazado;
    String razon_rechazo;
    int usuario_id_usuario;
    int terminal_id_terminal;
    int cuenta_no_cuenta;
    int estado_transaccion;

    public Transaccion(Date fecha, String Tipo, String Naturaleza, double saldo_inicial, double saldo_final, int codigo_autorizacion, int rechazado, String razon_rechazo, int usuario_id_usuario, int terminal_id_terminal, int cuenta_no_cuenta, int estado_transaccion) {
        this.fecha = fecha;
        this.Tipo = Tipo;
        this.Naturaleza = Naturaleza;
        this.saldo_inicial = saldo_inicial;
        this.saldo_final = saldo_final;
        this.codigo_autorizacion = codigo_autorizacion;
        this.rechazado = rechazado;
        this.razon_rechazo = razon_rechazo;
        this.usuario_id_usuario = usuario_id_usuario;
        this.terminal_id_terminal = terminal_id_terminal;
        this.cuenta_no_cuenta = cuenta_no_cuenta;
        this.estado_transaccion = estado_transaccion;
    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNaturaleza() {
        return Naturaleza;
    }

    public void setNaturaleza(String Naturaleza) {
        this.Naturaleza = Naturaleza;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public double getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(double saldo_final) {
        this.saldo_final = saldo_final;
    }

    public int getCodigo_autorizacion() {
        return codigo_autorizacion;
    }

    public void setCodigo_autorizacion(int codigo_autorizacion) {
        this.codigo_autorizacion = codigo_autorizacion;
    }

    public int getRechazado() {
        return rechazado;
    }

    public void setRechazado(int rechazado) {
        this.rechazado = rechazado;
    }

    public String getRazon_rechazo() {
        return razon_rechazo;
    }

    public void setRazon_rechazo(String razon_rechazo) {
        this.razon_rechazo = razon_rechazo;
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
    
}
