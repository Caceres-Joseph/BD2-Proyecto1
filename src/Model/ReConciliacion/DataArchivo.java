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
public class DataArchivo {
    private String documentos;
    private String id_lote;
    private String total;

    public DataArchivo(String documentos, String id_lote, String total) {
        this.documentos = documentos;
        this.id_lote = id_lote;
        this.total = total;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getId_lote() {
        return id_lote;
    }

    public void setId_lote(String id_lote) {
        this.id_lote = id_lote;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
    
}
