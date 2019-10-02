/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.TipoCuenta;

import Controller.TipoCuentasController;
import Main.B2;
import Model.TipoCuentas.TipoCuenta;
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
public class ListarTipoCuenta implements Initializable {

    TipoCuentasController r = new TipoCuentasController();
    public TablaTipoCuenta tablaTipoCuenta;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private TableView<TipoCuenta> tb;

    @FXML
    private TableColumn tc0;

    @FXML
    private TableColumn tc1;

    @FXML
    void clckEditar(ActionEvent event) {

        try {
            this.editarTipoCuenta();
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
        tablaTipoCuenta = new TablaTipoCuenta(tb, tc0, tc1);
        //Cargando la tabla con datos
        tablaTipoCuenta.mostrar(r);
        //Imprimiendo mensaje en consola
        B2.GuiController.mensajeConsola("Listando tipos de cuentas");
    }    
    
    
    /*
    +--------------------------------------
    | Metodo para precargar los datos y abrir el formulario
    +---------------------------------------
     */
    public void editarTipoCuenta() throws IOException {

        TipoCuenta selected = tb.getSelectionModel().getSelectedItem();

        //Validación
        if (selected == null) {
            B2.GuiController.mensajeConsola("No ha seleccionado un item de la tabla");
            return;
        }

        //hay que abrir el otro formulario para ediatar
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TipoCuenta/NuevoTipoCuenta.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        //Enviando los valroes
        NuevoTipoCuenta controlador = (NuevoTipoCuenta) loader.getController();
        controlador.initData(selected);

        //Renderizando la vista
        B2.GuiController.renderizar(root);

    }
    
}
