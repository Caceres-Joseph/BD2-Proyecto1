/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BD.BDOpciones;
import Model.Usuarios.ConsultaUsuarios;
import Model.Usuarios.Usuario;
import Model.Usuarios.UsuarioSession;
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
     * @param rol_id_rol
     * @return
     */
    public boolean creatUsuario(String nombre, String password, int rol_id_rol) {
        try {
            return consultas.save(new Usuario(nombre, password, rol_id_rol));
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
     * @param rol_id_rol
     * @return
     */
    public boolean updateUsuario(int id_usuario, String nombre, String password, int rol_id_rol) {
        try {
            Usuario u = new Usuario(nombre, password, rol_id_rol);
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
    public ArrayList<Usuario> listUsuarios(BDOpciones.LimitOp limitOp, BDOpciones.Orden orden, int limite) {
        try {
            ArrayList<Usuario> usuarios = consultas.listData(orden, limitOp, limite);
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
    
    public UsuarioSession getUser(String usuario)
    {
        try {
            return consultas.usuarioEnSession(usuario);
        } catch (Exception e) {
            return null;
        }
    }
}
