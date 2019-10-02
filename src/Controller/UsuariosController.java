/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuarios.ConsultaUsuarios;
import Model.Usuarios.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ricar
 */
public class UsuariosController {

    ConsultaUsuarios consultas;

    public UsuariosController() {
        consultas = new ConsultaUsuarios();
    }

    /**
     * Funcion para crear un nuevo Usuario
     *
     * @param nombre
     * @param password
     * @return
     */
    public boolean creatUsuario(String nombre, String password) {
        try {
            return consultas.save(new Usuario(nombre, password));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Funcion para actualizar el usuario
     *
     * @param id_usuario
     * @param nombre
     * @param password
     * @return
     */
    public boolean updateUsuario(int id_usuario, String nombre, String password) {
        try {
            Usuario u = new Usuario(nombre, password);
            u.setId_usuario(id_usuario);
            return consultas.update(u);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * funcion para obtener un usuario en especifico
     *
     * @param id
     * @return
     */
    public Usuario getUsuario(int id) {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Funcion para listar todos los usuraios
     *
     * @return
     */
    public ArrayList<Usuario> listUsuarios() {
        try {
            ArrayList<Usuario> usuarios = new ArrayList<>();
            ResultSet rs = consultas.listItems();
            while (rs.next()) {
                Usuario u = new Usuario(rs.getString("usuario"), rs.getString("password"));
                u.setId_usuario(rs.getInt("id_usuario"));
                usuarios.add(u);
            }
            return usuarios;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Funcion para listar todos los usuraios TEST
     *
     * @return
     */
    public ArrayList<Usuario> listUsuariosTest() {
        try {
            ArrayList<Usuario> usuarios = new ArrayList<>();
            
            
            Usuario u = new Usuario("Ricardo", "ILoveAndrea");
            u.setId_usuario(1);
            usuarios.add(u);
            
            
            
            return usuarios;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Metodo agregado para realizar un login
     *
     * @param usuario
     * @param password
     * @return
     */
    public boolean UserLogin(String usuario, String password) {
        try {
            return consultas.usuarioLogIn(new Usuario(usuario, password));
        } catch (Exception e) {
            return false;
        }
    }
}
