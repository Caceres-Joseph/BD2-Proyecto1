/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Usuarios;

import Controller.RolesController;
import Main.B2;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Controller.UsuariosController;
import Model.Roles.Rol;
import Model.Usuarios.Usuario;
import View.Roles.ComboRol;
import com.jfoenix.controls.JFXComboBox;
/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevoUsuario implements Initializable {
    UsuariosController u = new UsuariosController();
    RolesController r=new RolesController();
    
    ComboRol comboRol;
    

    @FXML
    private JFXComboBox<Rol> cbRol;
    
    
    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtPassword2;

    @FXML
    void clckAceptar(ActionEvent event) {

        String nombre = txtNombre.getText();
        String pass1 = txtPassword.getText();
        String pass2 = txtPassword2.getText();

        
        if (comboRol.id_Rol == -1) {

            B2.GuiController.mensajeConsola("Debe seleccionar un rol");
            return;
        }
        
        
        if (!pass1.equals(pass2)) {
            B2.GuiController.mensajeConsola("Las contraseñas no coinciden");
            txtPassword.setText("");
            txtPassword2.setText("");
            return;
        }

        if (this.itemModificar == null) {

            if (insertar(nombre, pass1,comboRol.id_Rol)) {

                B2.GuiController.mensajeConsola("Usuario insertado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar el Usuario");
            }
        } else {

            if (editar(nombre, pass1,comboRol.id_Rol)) {

                B2.GuiController.mensajeConsola("Usuario actualizado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar el Usuario");
            }

        }

    }

    
    
    
    /*
    +--------------------------------------
    | Metodos de modificación y carga de datos
    +---------------------------------------
    
     */
    private Usuario itemModificar;

    public void initData(Usuario item) {
        this.itemModificar = item;
        this.txtNombre.setText(item.getUsuario());
        this.txtPassword.setText(item.getPassword());
        this.txtPassword2.setText(item.getPassword());
         
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        comboRol = new ComboRol(cbRol);
        comboRol.mostrarRols(r);
    }

    /*
    +--------------------------------------
    | Metodos de inserción o modificación
    +---------------------------------------
    
     */
    public boolean insertar(String nombre, String password, int idRol) {
        return u.creatUsuario(nombre, password,idRol);
    }

    public boolean editar(String nombre, String password, int idRol) {
        return u.updateUsuario(itemModificar.getId_usuario(), nombre, password, idRol);
    }
    
    
    

}
