/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Roles;

/**
 *
 * @author ricar
 */
public class Rol {
    private int id_rol;
    private String nombre;
    private int estado_rol;
    
    public Rol(String nombre)
    {
        this.nombre = nombre;
        this.estado_rol = 1;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado_rol() {
        return estado_rol;
    }

    public void setEstado_rol(int estado_rol) {
        this.estado_rol = estado_rol;
    }
    
}
