/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ChequeTMP2;
import java.sql.Date;
/**
 *
 * @author ricar
 */
public class ChequeTMP2 {
    private int id_cheque;
    private Date fecha;
    private int cuenta;
    private double valor;
    private int lote;
    private int referencia;
    private int correlativo;
    private String estado_cheque;
    
    public ChequeTMP2(Date fecha, int cuenta, double valor, int lote, int referencia, int correlativo)
    {
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.valor = valor;
        this.lote = lote;
        this.referencia = referencia;
        this.correlativo = correlativo;
        this.estado_cheque = "";
    }

    public int getId_cheque() {
        return id_cheque;
    }

    public void setId_cheque(int id_cheque) {
        this.id_cheque = id_cheque;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public String getEstado_cheque() {
        return estado_cheque;
    }

    public void setEstado_cheque(String estado_cheque) {
        this.estado_cheque = estado_cheque;
    }
    
}
