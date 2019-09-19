/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Agencia;

import Main.B2;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Controller.AgenciasController;
/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevaAgencia implements Initializable {
    AgenciasController a = new AgenciasController();
    int id_agen = -1;
    //int id_banco = -1;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtDIreccion;

    ComboAgencia comboAgencia;
    @FXML
    private JFXComboBox<ComboAgencia.elementoCombo> cbBancos;

    @FXML
    void clckAceptar(ActionEvent event) {

        ComboAgencia.elementoCombo elemento = cbBancos.getSelectionModel().selectedItemProperty().get();
        System.out.println(elemento.getNo());
        System.out.println(elemento.getValue());

        String txtNombreRol = this.txtNombre.getText();
        String txtDireccion = this.txtDIreccion.getText();

        if (comboAgencia.id_Banco == -1) {

            B2.GuiController.mensajeConsola("Debe seleccionar un banco");
            return;
        }

        if (this.id_agen == -1) {

            if (insertar(txtNombreRol, txtDireccion, comboAgencia.id_Banco)) {

                B2.GuiController.mensajeConsola("Agencia insertada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar la Agencia");
            }
        } else {

            if (editar(txtNombreRol, txtDireccion, comboAgencia.id_Banco)) {

                B2.GuiController.mensajeConsola("Agencia actualizada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar la Agencia");
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        comboAgencia = new ComboAgencia(cbBancos);
        comboAgencia.mostrarBancos();
    }

    /*
    +--------------------------------------
    | Metodos de inserción o modificación
    +---------------------------------------
    
     */
    public boolean insertar(String nombre, String direccion, int id_Banco) {

        return a.createAgencia(nombre, direccion, id_Banco);
    }

    public boolean editar(String nombre, String direccion, int id_Banco) {
        return a.updateAgencia(id_agen, nombre, direccion, id_Banco);
    }

}
