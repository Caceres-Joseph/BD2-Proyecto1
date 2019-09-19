/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Usuarios;

import Main.B2;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Controller.UsuariosController;
/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevoUsuario implements Initializable {
    UsuariosController u = new UsuariosController();
    int id_Usuarios = -1;

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

        if (!pass1.equals(pass2)) {
            B2.GuiController.mensajeConsola("Las contraseñas no coinciden");
            txtPassword.setText("");
            txtPassword2.setText("");
            return;
        }

        if (this.id_Usuarios == -1) {

            if (insertar(nombre, pass1)) {

                B2.GuiController.mensajeConsola("Usuario insertado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar el Usuario");
            }
        } else {

            if (editar(nombre, pass1)) {

                B2.GuiController.mensajeConsola("Usuario actualizado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar el Usuario");
            }

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*
    +--------------------------------------
    | Metodos de inserción o modificación
    +---------------------------------------
    
     */
    public boolean insertar(String nombre, String password) {
        return u.creatUsuario(nombre, password);
    }

    public boolean editar(String nombre, String password) {
        return u.updateUsuario(id_Usuarios, nombre, password);
    }

}
