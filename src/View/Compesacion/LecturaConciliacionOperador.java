/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Compesacion;

import Controller.RecibirConciliacionController;
import Main.B2;
import static Main.B2.sta;
import Model.ReConciliacion.ChequeConciliado;
import Model.ReConciliacion.Lote;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class LecturaConciliacionOperador implements Initializable {

    RecibirConciliacionController con_Recibir = new RecibirConciliacionController();
    Lote lote;
    public TablaLecturaGrabador tabla;

    @FXML
    private JFXTextField txtDocumento;

    @FXML
    private JFXTextField txtIdLote;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXTextField txtEstado;

    @FXML
    private TableView<ChequeConciliado> tb;

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

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll();

        File file = fileChooser.showOpenDialog(sta);

        try {

            if (file != null) {
                con_Recibir.LeerConciliados(file.getAbsolutePath());

                //Enviando los valores 
                this.txtDocumento.setText(String.valueOf(lote.getNo_documentos()));
                this.txtIdLote.setText(String.valueOf(lote.getId_lote()));
                this.txtTotal.setText(String.valueOf(lote.getValor()));
                this.txtEstado.setText(String.valueOf(lote.getEstado()));

                //Obteniendo la información para colocarla en los combo box
                B2.GuiController.mensajeConsola("Lectura exitosa");

            }

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }

    }

    @FXML
    void clckCancelar(ActionEvent event) {

        if (lote == null) {
            B2.GuiController.mensajeConsola("Debe cargar un lote primero");
            return;
        }

//        //cargando la tabla 
//        //Inicializando tabla
//        tabla = new TablaLecturaGrabador(tb, tc0, tc1, tc2, tc3, tc4);
//        //Cargando la tabla con datos
//        tabla.mostrar(con_Recibir, lote.getId_lote());
//        //Imprimiendo mensaje en consola
//        B2.GuiController.mensajeConsola("Listando lotes");

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
