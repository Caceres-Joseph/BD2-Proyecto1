/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TipoCuentas;

/**
 *
 * @author ricar
 */
public class TipoCuenta {
    private int id_tipo;
    private String nombre;
    
    
    public TipoCuenta(String nombre)
    {
        this.nombre = nombre;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
