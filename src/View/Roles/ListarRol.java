/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Roles;

import Controller.RolesController;
import Main.B2;
import static Main.B2.GuiController;
import View.Gui.Gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class ListarRol implements Initializable {

    RolesController r = new RolesController();
    public TablaRol tablaRols;

    @FXML
    public TableView<TablaRol.elementoTabla> tbRol;

    @FXML
    public TableColumn tcId;

    @FXML
    public TableColumn tcRol;

    @FXML
    void clckEditar(ActionEvent event) throws IOException {
        TablaRol.elementoTabla selected = tbRol.getSelectionModel().getSelectedItem();
        if (selected == null) {
            B2.GuiController.mensajeConsola("No ha seleccionado un item de la tabla");
            return;
        }

        //hay que abrir el otro formulario para ediatar
        //Parent root = FXMLLoader.load(getClass().getResource("/View/Login/login.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Roles/NuevoRol.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        //Enviando los valroes
        NuevoRol controlador = (NuevoRol) loader.getController();
        controlador.initData(Integer.valueOf(selected.getNo()), selected.getRol());

        B2.GuiController.renderizar(root);

    }

    @FXML
    void clckEliminar(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tablaRols = new TablaRol(tbRol, tcId, tcRol);

        tablaRols.mostrar(r);
        B2.GuiController.mensajeConsola("Listando roles");
    }

}
