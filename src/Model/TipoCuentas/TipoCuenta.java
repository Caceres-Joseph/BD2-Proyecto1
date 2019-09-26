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
    private int estado_tipo_cuenta;
    
    
    public TipoCuenta(String nombre)
    {
        this.nombre = nombre;
        this.estado_tipo_cuenta = 1;
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

    public int getEstado_tipo_cuenta() {
        return estado_tipo_cuenta;
    }

    public void setEstado_tipo_cuenta(int estado_tipo_cuenta) {
        this.estado_tipo_cuenta = estado_tipo_cuenta;
    }
    
    
}
