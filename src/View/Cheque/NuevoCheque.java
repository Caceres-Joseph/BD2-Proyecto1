/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cheque;

import com.jfoenix.controls.JFXComboBox;
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
public class NuevoCheque implements Initializable {

    
    @FXML
    private JFXComboBox<?> cbUsuario;

    @FXML
    private JFXComboBox<?> cbAgencia;

    @FXML
    void clckAceptar(ActionEvent event) {
        
        System.out.println("Hola mundo");

    }

    @FXML
    void clckEjemplo(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
