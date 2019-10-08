/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Roles_Permiso;

/**
 *
 * @author ricar
 */
public class RolPermiso {
    private int rol_id_rol;
    private int permiso_id_permiso;
    private int estado_rol_permiso;
    private String permiso;
    private String rol;
    
    public RolPermiso(int rol_id_rol, int permiso_id_permiso, int estado_rol_permiso)
    {
        this.rol_id_rol = rol_id_rol;
        this.permiso_id_permiso = permiso_id_permiso;
        this.estado_rol_permiso = estado_rol_permiso;
    }

    public int getRol_id_rol() {
        return rol_id_rol;
    }

    public void setRol_id_rol(int rol_id_rol) {
        this.rol_id_rol = rol_id_rol;
    }

    public int getPermiso_id_permiso() {
        return permiso_id_permiso;
    }

    public void setPermiso_id_permiso(int permiso_id_permiso) {
        this.permiso_id_permiso = permiso_id_permiso;
    }

    public int getEstado_rol_permiso() {
        return estado_rol_permiso;
    }

    public void setEstado_rol_permiso(int estado_rol_permiso) {
        this.estado_rol_permiso = estado_rol_permiso;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
