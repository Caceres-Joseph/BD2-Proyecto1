/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Clientes;

import java.sql.Date;

/**
 *
 * @author ricar
 */
public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;
    private Date fecha_nacimiento;
    private int estado_cliente;
    private String foto;
    private String firma;
    
    public Cliente(String nombre, String direccion)
    {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
    public Cliente(int id_cliente, String nombre, String apellido, String direccion,
                String correo, String telefono, Date fecha_nacimiento, int estado_cliente,
                String firma, String foto
            )
    {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado_cliente = estado_cliente;
        this.foto = foto;
        this.firma = firma;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getEstado_cliente() {
        return estado_cliente;
    }

    public void setEstado_cliente(int estado_cliente) {
        this.estado_cliente = estado_cliente;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }
    
    
}
