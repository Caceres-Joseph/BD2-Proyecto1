/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ReConciliacion;

/**
 *
 * @author Daniel
 */
public class ChequeConciliado {
    
    
    private int id_cheque;
    private int cuenta;
    private double valor;
    private int lote;
    private int estado;
    private int referencia;

    /**
     * Constructor de clase utilizada para representar los cheques que 
     * se obtienen del archivo recibido de conciliación para determinar
     * si tiene fondos y debitar de la cuenta.
     * @param id_cheque
     * @param cuenta
     * @param valor
     * @param lote
     * @param estado 
     */
    public ChequeConciliado(int id_cheque, int cuenta, double valor, int lote, int estado, int referencia) {
        this.id_cheque = id_cheque;
        this.cuenta = cuenta;
        this.valor = valor;
        this.lote = lote;
        this.estado = estado;
        this.referencia = referencia;
    }

    public int getId_cheque() {
        return id_cheque;
    }

    public void setId_cheque(int id_cheque) {
        this.id_cheque = id_cheque;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }
    
    
    
    
    
}
