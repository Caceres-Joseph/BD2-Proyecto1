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
public class Lote {
    private int id_lote;
    private int id_banco;
    private int no_documentos;
    private double valor;
    private int estado;

    /**
     * Constructor de clase utilizada para crear un nuevo lote temporal
     * para recibir los cheques conciliados.
     * @param id_lote
     * @param id_banco
     * @param no_documentos
     * @param valor
     * @param estado 
     */
    
    public Lote(int id_lote, int id_banco, int no_documentos, double valor, int estado) {
        this.id_lote = id_lote;
        this.id_banco = id_banco;
        this.no_documentos = no_documentos;
        this.valor = valor;
        this.estado = estado;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public int getNo_documentos() {
        return no_documentos;
    }

    public void setNo_documentos(int no_documentos) {
        this.no_documentos = no_documentos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
