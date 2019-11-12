/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Operaciones;

import Main.B2;
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
public class Transferencia implements Initializable {

    
    @FXML
    private JFXTextField txtDestino;

    @FXML
    private JFXTextField txtOrigen;

    @FXML
    void clckAceptar(ActionEvent event) {

        try {
            if (this.insertar(Integer.valueOf(txtOrigen.getText()), Integer.valueOf(txtDestino.getText()))) {

                B2.GuiController.mensajeConsola("Transfernacia realizada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error");
            }
        } catch (Exception e) {
            System.out.println(e);
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
    | Metodos de inserción
    +---------------------------------------
    
     */
    public boolean insertar(int origen, int destino) {

        return true;
    }

}
