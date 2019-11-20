/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.LoteTMP2;

/**
 *
 * @author ricar
 */
public class LoteTMP2 {
    private int id_lote_2;
    private int total_documentos;
    private double total_monto;
    private int id_banco;
    private int estado_lote;
    private String bancoName;
    private String estado_loteString;
    
    public LoteTMP2(int total_documentos, double total_monto, int id_banco, int estado_lote)
    {
        this.total_documentos = total_documentos;
        this.total_monto = total_monto;
        this.id_banco = id_banco;
        this.estado_lote = estado_lote;
        this.estado_loteString = estadoString();
    }

    public int getId_lote_2() {
        return id_lote_2;
    }

    public void setId_lote_2(int id_lote_2) {
        this.id_lote_2 = id_lote_2;
    }

    public int getTotal_documentos() {
        return total_documentos;
    }

    public void setTotal_documentos(int total_documentos) {
        this.total_documentos = total_documentos;
    }

    public double getTotal_monto() {
        return total_monto;
    }

    public void setTotal_monto(double total_monto) {
        this.total_monto = total_monto;
    }

    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public int getEstado_lote() {
        return estado_lote;
    }

    public void setEstado_lote(int estado_lote) {
        this.estado_loteString = estadoString();
        this.estado_lote = estado_lote;
    }

    public String getBancoName() {
        return bancoName;
    }

    public void setBancoName(String bancoName) {
        this.bancoName = bancoName;
    }
    
    private String estadoString()
    {
        return this.estado_lote == 0? "Exportado":"No Exportado";
    }

    public String getEstado_loteString() {
        return estado_loteString;
    }
    
    public void imprimir(){
        
    }
}
