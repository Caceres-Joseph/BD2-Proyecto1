/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Compesacion;

import Controller.RecibirConciliacionController;
import Main.B2;
import Model.ReConciliacion.Lote;
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
public class ArchivoConciliado implements Initializable {
    
    RecibirConciliacionController con_Recibir = new RecibirConciliacionController();
    public TablaLotesConciliados tabla; 
    
    
    
   @FXML
    private TableView<Lote> tb;

    @FXML
    private TableColumn tc0;

    @FXML
    private TableColumn tc1;

    @FXML
    private TableColumn tc2;

    @FXML
    private TableColumn tc3;

    @FXML
    private TableColumn tc4;

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
     
            //Inicializando tabla
            tabla = new TablaLotesConciliados(tb, tc0, tc1, tc2, tc3, tc4);
            //Cargando la tabla con datos
            tabla.mostrar(con_Recibir);
            //Imprimiendo mensaje en consola
            B2.GuiController.mensajeConsola("Listando lotes");
    }     
}
