/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Clientes;

import Controller.ClientesController;
import Main.B2;
import Model.Clientes.Cliente;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class ListarCliente implements Initializable {

    
    TablaCliente tablaCliente;
    ClientesController r =new ClientesController();
    
    
    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private TableView<Cliente> tbTabla;

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
    private TableColumn tc5;
    
    @FXML
    void clckEditar(ActionEvent event) {

        try {
            this.editarCliente();
        } catch (IOException ex) {
            B2.GuiController.mensajeConsola("Error al editar - " + ex.getMessage());
        }
    }

    @FXML
    void clckEliminar(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Inicializando tabla
        tablaCliente = new TablaCliente(tbTabla, tc0, tc1, tc2, tc3, tc4, tc5);
        //Cargando la tabla con datos
        tablaCliente.mostrar(r);
        //Imprimiendo mensaje en consola
        B2.GuiController.mensajeConsola("Listando clientes");
    }    
 
    
    /*
    +--------------------------------------
    | Metodo para precargar los datos y abrir el formulario
    +---------------------------------------
     */
    public void editarCliente() throws IOException {

        Cliente selected = tbTabla.getSelectionModel().getSelectedItem();

        //Validación
        if (selected == null) {
            B2.GuiController.mensajeConsola("No ha seleccionado un item de la tabla");
            return;
        }

        //hay que abrir el otro formulario para ediatar
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Clientes/NuevoCliente.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        //Enviando los valroes
        NuevoCliente controlador = (NuevoCliente) loader.getController();
        controlador.initData(selected);

        //Renderizando la vista
        B2.GuiController.renderizar(root);
        

    }
}
