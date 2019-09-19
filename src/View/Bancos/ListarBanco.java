/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Bancos;

import Main.B2;
import View.Roles.ListarRol;
import View.Roles.NuevoRol;
import View.Roles.TablaRol;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class ListarBanco extends ListarRol {

    @FXML
    void clckEditar(ActionEvent event) throws IOException {
        TablaRol.elementoTabla selected = tbRol.getSelectionModel().getSelectedItem();
        if (selected == null) {
            B2.GuiController.mensajeConsola("No ha seleccionado un item de la tabla");
            return;
        }

        //hay que abrir el otro formulario para ediatar
        //Parent root = FXMLLoader.load(getClass().getResource("/View/Login/login.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Bancos/NuevoBanco.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        //Enviando los valroes
        NuevoBanco controlador = (NuevoBanco) loader.getController();
        controlador.initData(Integer.valueOf(selected.getNo()), selected.getRol());

        B2.GuiController.renderizar(root);

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        tablaRols = new TablaRol(tbRol, tcId, tcRol);

        tablaRols.mostrarBancos();
        B2.GuiController.mensajeConsola("Listando bancos");
    }    
    
}
