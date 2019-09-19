/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Permisos.ConsultasPermiso;
import Model.Permisos.Permiso;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class PermisosController {
    
    ConsultasPermiso consultas;
    
    public PermisosController()
    {
        consultas = new ConsultasPermiso();
    }
    
    /**
     * Funcion para guardar un Permiso en la BD
     * @param nombre
     * @param descripcion
     * @return 
     */
    public boolean createPermiso(String nombre, String descripcion)
    {
        try {
            return consultas.save(new Permiso(nombre, descripcion));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para actualizar un permiso
     * @param id_permiso
     * @param nombre
     * @param descripcion
     * @return 
     */
    public boolean updatePermiso(int id_permiso, String nombre, String descripcion)
    {
        try {
            Permiso p = new Permiso(nombre, descripcion);
            p.setId_permiso(id_permiso);
            return consultas.update(p);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para poder encontrar un permiso en especifico
     * @param id
     * @return 
     */
    public Permiso getPermiso(int id)
    {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Lista todos los permisos
     * @return 
     */
    public ArrayList<Permiso> listPermisos()
    {
        try {
            ArrayList<Permiso> permisos = new ArrayList<>();
            ResultSet rs = consultas.listItems();
            while(rs.next())
            {
                Permiso p = new Permiso(rs.getString("nombre"),rs.getString("descripcion"));
                p.setId_permiso(rs.getInt("id_permiso"));
                permisos.add(p);
            }
            return permisos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    
    
}
