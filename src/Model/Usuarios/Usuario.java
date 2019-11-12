/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Usuarios;

/**
 *
 * @author ricar
 */
public class Usuario {
    private int id_usuario;
    private String usuario;
    private String password;
    private int estado_usuario;
    private int rol_id_rol;
    
    public Usuario(String usuario, String password)
    {
        this.usuario = usuario;
        this.password = password;
        this.estado_usuario = 1;
    }
    
    public Usuario(String usuario, String password, int rol_id_rol)
    {
        this.usuario = usuario;
        this.password = password;
        this.estado_usuario = 1;
        this.rol_id_rol = rol_id_rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado_usuario() {
        return estado_usuario;
    }

    public void setEstado_usuario(int estado_usuario) {
        this.estado_usuario = estado_usuario;
    }

    public int getRol_id_rol() {
        return rol_id_rol;
    }

    public void setRol_id_rol(int rol_id_rol) {
        this.rol_id_rol = rol_id_rol;
    }
    
    
}
