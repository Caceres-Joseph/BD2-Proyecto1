/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Agencia;

import Controller.AgenciaBancoController;
import Main.B2;
import Model.Agencias.AgenciaBanco;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ListarAgencia implements Initializable {

    AgenciaBancoController r = new AgenciaBancoController();
    public TablaAgencia tablaAgencia;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private TableView<AgenciaBanco> tb;

    @FXML
    private TableColumn tc0;

    @FXML
    private TableColumn tc1;

    @FXML
    private TableColumn tc2;

    @FXML
    private TableColumn tc3;

    @FXML
    void clckEditar(ActionEvent event) {
        try {
            this.editarAgencia();
        } catch (IOException ex) {
            B2.GuiController.mensajeConsola("Error al editar - " + ex.getMessage());
        }
    }

    @FXML
    void clckEliminar(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializando tabla
        tablaAgencia = new TablaAgencia(tb, tc0, tc1, tc2, tc3);
        //Cargando la tabla con datos
        tablaAgencia.mostrar(r);
        //Imprimiendo mensaje en consola
        B2.GuiController.mensajeConsola("Listando agencias");
    }

    /*
    +--------------------------------------
    | Metodo para precargar los datos y abrir el formulario
    +---------------------------------------
     */
    public void editarAgencia() throws IOException {

        AgenciaBanco selected = tb.getSelectionModel().getSelectedItem();

        //Validación
        if (selected == null) {
            B2.GuiController.mensajeConsola("No ha seleccionado un item de la tabla");
            return;
        }

        //hay que abrir el otro formulario para ediatar
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Agencia/NuevaAgencia.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        //Enviando los valroes
        NuevaAgencia controlador = (NuevaAgencia) loader.getController();
        controlador.initData(selected);

        //Renderizando la vista
        B2.GuiController.renderizar(root);

    }

}
