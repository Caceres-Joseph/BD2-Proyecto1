/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Usuarios;

import Controller.UsuariosController;
import Main.B2;
import Model.Agencias.AgenciaBanco;
import Model.Usuarios.Usuario;
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
public class ListarUsuario implements Initializable {

    UsuariosController r = new UsuariosController();
    public TablaUsuario tablaUsuario;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private TableView<Usuario> tb;

    @FXML
    private TableColumn tc0;

    @FXML
    private TableColumn tc1;

    @FXML
    void clckEditar(ActionEvent event) {

        try {
            this.editarUsuario();
        } catch (IOException ex) {
            B2.GuiController.mensajeConsola("Error al renderizar la vista de modificar: " + ex.getMessage());
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
        tablaUsuario = new TablaUsuario(tb, tc0, tc1);
        //Cargando la tabla con datos
        tablaUsuario.mostrar(r);
        //Imprimiendo mensaje en consola
        B2.GuiController.mensajeConsola("Listando usuarios");
    }

    /*
    +--------------------------------------
    | Metodo para precargar los datos y abrir el formulario
    +---------------------------------------
     */
    public void editarUsuario() throws IOException {

        Usuario selected = tb.getSelectionModel().getSelectedItem();

        //Validación
        if (selected == null) {
            B2.GuiController.mensajeConsola("No ha seleccionado un item de la tabla");
            return;
        }

        //hay que abrir el otro formulario para ediatar
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Usuarios/NuevoUsuario.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        //Enviando los valroes
        NuevoUsuario controlador = (NuevoUsuario) loader.getController();
        controlador.initData(selected);

        //Renderizando la vista
        B2.GuiController.renderizar(root);

    }

}
