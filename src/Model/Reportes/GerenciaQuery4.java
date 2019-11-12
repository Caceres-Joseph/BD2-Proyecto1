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
public class GerenciaQuery4 {
    int dpi_cliente;
    String nombre_cliente;

    public GerenciaQuery4(int dpi_cliente, String nombre_cliente) {
        this.dpi_cliente = dpi_cliente;
        this.nombre_cliente = nombre_cliente;
    }

    public int getDpi_cliente() {
        return dpi_cliente;
    }

    public void setDpi_cliente(int dpi_cliente) {
        this.dpi_cliente = dpi_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
    
    
    
    
}
