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
public class GerenciaQuery3 {
    private String nombre_agencia;
    private String nombre_cliente;
    private Double pagos;

    public GerenciaQuery3(String nombre_agencia, String nombre_cliente, Double pagos) {
        this.nombre_agencia = nombre_agencia;
        this.nombre_cliente = nombre_cliente;
        this.pagos = pagos;
    }

    public String getNombre_agencia() {
        return nombre_agencia;
    }

    public void setNombre_agencia(String nombre_agencia) {
        this.nombre_agencia = nombre_agencia;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public Double getPagos() {
        return pagos;
    }

    public void setPagos(Double pagos) {
        this.pagos = pagos;
    }
    
    
    
}
