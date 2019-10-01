/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Clientes;

import Main.B2;
import com.jfoenix.controls.JFXDatePicker;
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
public class NuevoCliente implements Initializable {

    int id_Cliente = -1;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtDireccion;

    @FXML
    private JFXTextField txtApellido;

    @FXML
    private JFXTextField txtCorreo;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private JFXDatePicker txtNacimiento;

    @FXML
    void clckAceptar(ActionEvent event) {

        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();

        if (this.id_Cliente == -1) {

            if (insertar(nombre, direccion)) {

                B2.GuiController.mensajeConsola("Cliente insertado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar el Cliente");
            }
        } else {

            if (editar(nombre, direccion)) {

                B2.GuiController.mensajeConsola("Cliente actualizado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar el Cliente");
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
    public boolean insertar(String nombre, String direccion) {

        return true;
    }

    public boolean editar(String nombre, String direccion) {
        return true;
    }

}
