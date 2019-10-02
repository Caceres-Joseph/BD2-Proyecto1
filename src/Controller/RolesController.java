/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.BD.BDOpciones;
import Model.Roles.Rol;
import Model.Roles.ConsultasRoles;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class RolesController {
    
    ConsultasRoles consultas;
    
    public RolesController()
    {
        consultas = new ConsultasRoles();
    }
    
    /**
     * Funcion para crear un rol
     * @param nombre
     * @return 
     */
    public boolean creatRol(String nombre)
    {
        try {
            return consultas.save(new Rol(nombre));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para poder actualizar un Rol
     * @param id
     * @param nombre
     * @return 
     */
    public boolean updateRol(int id, String nombre)
    {
        try {
            Rol r = new Rol(nombre);
            r.setId_rol(id);
            return consultas.update(r);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Busca un rol especificado por el id
     * @param id
     * @return 
     */
    public Rol getRol(int id)
    {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Funcion que lista los roles
     * @return 
     */
    public ArrayList<Rol> listRoles(BDOpciones.LimitOp limitOp, BDOpciones.Orden orden, int limite)
    {
        try {
            ArrayList<Rol> roles = consultas.listData(orden, limitOp, limite);
            return roles;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
