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
public class GerenciaQuery2 {
    
    String nombre_agencia;
    String nombre_cliente;
    int cantidad_depositos;

    public GerenciaQuery2(String nombre_agencia, String nombre_cliente, int cantidad_depositos) {
        this.nombre_agencia = nombre_agencia;
        this.nombre_cliente = nombre_cliente;
        this.cantidad_depositos = cantidad_depositos;
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

    public int getCantidad_depositos() {
        return cantidad_depositos;
    }

    public void setCantidad_depositos(int cantidad_depositos) {
        this.cantidad_depositos = cantidad_depositos;
    }
    
    
    
    
}
