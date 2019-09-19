/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Agencia;

import com.jfoenix.controls.JFXComboBox;
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
public class NuevaAgencia implements Initializable {

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

}
