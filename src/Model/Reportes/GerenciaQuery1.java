/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Reportes;

/**
 *
 * @author Daniel
 */
public class GerenciaQuery1 {
    
    private Integer id_agencia;
    private String nombre_agencia;
    private Double saldo_final;

    public GerenciaQuery1(Integer id_agencia, String nombre_agencia, Double saldo_final) {
        this.id_agencia = id_agencia;
        this.nombre_agencia = nombre_agencia;
        this.saldo_final = saldo_final;
    }

    
    public Integer getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(Integer id_agencia) {
        this.id_agencia = id_agencia;
    }

    public String getNombre_agencia() {
        return nombre_agencia;
    }

    public void setNombre_agencia(String nombre_agencia) {
        this.nombre_agencia = nombre_agencia;
    }

    public Double getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(Double saldo_final) {
        this.saldo_final = saldo_final;
    }
    
    
    
}
