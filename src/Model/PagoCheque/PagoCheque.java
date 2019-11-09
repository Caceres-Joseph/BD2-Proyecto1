/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.PagoCheque;

/**
 *
 * @author Daniel
 */
public class PagoCheque {
    
    private int codigo_cheque;
    private int no_cuenta_origen;
    private double monto;
    private int terminal;
    private int id_usuario;

    /**
     * Constructor clase para realizar una transferencia utilizando un cheque.
     * @param codigo_cheque
     * @param no_cuenta_origen
     * @param monto
     * @param terminal
     * @param id_usuario 
     */
    
    public PagoCheque(int codigo_cheque, int no_cuenta_origen, double monto, int terminal, int id_usuario) {
        this.codigo_cheque = codigo_cheque;
        this.no_cuenta_origen = no_cuenta_origen;
        this.monto = monto;
        this.terminal = terminal;
        this.id_usuario = id_usuario;
    }

    public int getCodigo_cheque() {
        return codigo_cheque;
    }

    public void setCodigo_cheque(int codigo_cheque) {
        this.codigo_cheque = codigo_cheque;
    }

    public int getNo_cuenta_origen() {
        return no_cuenta_origen;
    }

    public void setNo_cuenta_origen(int no_cuenta_origen) {
        this.no_cuenta_origen = no_cuenta_origen;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}
