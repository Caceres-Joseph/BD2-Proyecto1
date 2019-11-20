/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Compesacion;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class LecturaConciliacion implements Initializable {

    @FXML
    private JFXTextField txtDocumento;

    @FXML
    private JFXTextField txtIdLote;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXTextField txtEstado;

    @FXML
    private TableView<?> tb;

    @FXML
    private TableColumn<?, ?> tc0;

    @FXML
    private TableColumn<?, ?> tc1;

    @FXML
    private TableColumn<?, ?> tc2;

    @FXML
    private TableColumn<?, ?> tc3;

    @FXML
    private TableColumn<?, ?> tc4;
    @FXML
    void clckAceptar(ActionEvent event) {

    }

    @FXML
    void clckCancelar(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
