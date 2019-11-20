/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Compesacion;

import Controller.GeneracionConciliacionController;
import Main.B2;
import Model.LoteTMP2.LoteTMP2;
import View.Agencia.TablaAgencia;
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
public class ArchivoConciliacion implements Initializable {

    GeneracionConciliacionController con = new GeneracionConciliacionController();
    @FXML
    private TableView<LoteTMP2> tb;

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

        if (!validar()) {
            return;
        }

        try {

            if (con.exportLote(selected)) {
                con.marcarExportado(selected.getId_lote_2(), selected.getId_banco());
                B2.GuiController.mensajeConsola("Se exportó de forma correcta");
            } else {
                B2.GuiController.mensajeConsola("Ocurrió un error :(");
            }

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }
    }

    @FXML
    void clckCancelar(ActionEvent event) {

    }

    public TablaConciliacion tablaConciliacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicializando tabla
        tablaConciliacion = new TablaConciliacion(tb, tc0, tc1, tc2, tc3, tc4);
        //Cargando la tabla con datos
        tablaConciliacion.mostrar(con);
        //Imprimiendo mensaje en consola
        B2.GuiController.mensajeConsola("Listando lotes");
    }

    LoteTMP2 selected;

    public boolean validar() {

        selected = tb.getSelectionModel().getSelectedItem();

        //Validación
        if (selected == null) {
            B2.GuiController.mensajeConsola("No ha seleccionado un item de la tabla");
            return false;
        }

        return true;
    }

}
