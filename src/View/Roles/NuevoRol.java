/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Roles;

import Main.B2;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevoRol implements Initializable {

    
    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;

    
    
    @FXML
    public JFXTextField txtNombreRol;

    @FXML
    void clckAceptar(ActionEvent event) {

        String txtNombreRol = this.txtNombreRol.getText();
        if (this.id_rol == -1) {

            if (insertarRol(txtNombreRol)) {

                B2.GuiController.mensajeConsola("Rol insertado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar el rol");
            }
        } else {

            if (editarRol(txtNombreRol, id_rol)) {

                B2.GuiController.mensajeConsola("Rol actualizado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar el rol");
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

    public int id_rol = -1;
    public String nombre = "";

    public void initData(int id_rol, String nombre) {
        this.id_rol = id_rol;
        this.nombre = nombre;
        
        this.txtNombreRol.setText(nombre);
    }

    /*
    +--------------------------------------
    | Metodos de inserción o modificación
    +---------------------------------------
    
     */
    public boolean insertarRol(String nombre) {

        return true;
    }

    public boolean editarRol(String nombre, int id_rol) {
        return true;
    }
}
