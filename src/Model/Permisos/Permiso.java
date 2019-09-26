/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Permisos;

/**
 *
 * @author ricar
 */
public class Permiso {
    private int id_permiso;
    private String nombre;
    private String descripcion;
    private int estado_permiso;
    public Permiso(String nombre, String descripcion)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado_permiso = 1;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado_permiso() {
        return estado_permiso;
    }

    public void setEstado_permiso(int estado_permiso) {
        this.estado_permiso = estado_permiso;
    }
    
    
}
