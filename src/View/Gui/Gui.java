/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui;

import View.Gui.Componentes.ideVistaArbol;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class Gui extends ideVistaArbol {

    @FXML
    private JFXTabPane tabClases;

    @FXML
    private TableView<?> tbErrores;

    @FXML
    private TableColumn<?, ?> tcAmbito;

    @FXML
    private TableColumn<?, ?> tcLinea;

    @FXML
    private TableColumn<?, ?> tcColumna;

    @FXML
    private TableColumn<?, ?> tcTipo;

    @FXML
    private TableColumn<?, ?> tcDescripcion;

    @FXML
    private TextArea txtConsola;

    @FXML
    private TableView<?> tbDasmStack;

    @FXML
    private TableColumn<?, ?> tcDstackNo;

    @FXML
    private TableColumn<?, ?> tcDStackStack;

    @FXML
    private TableView<?> tbDasmHeap;

    @FXML
    private TableColumn<?, ?> tcDheapNo;

    @FXML
    private TableColumn<?, ?> tcDheap;

    @FXML
    private TableView<?> tbDasmAux;

    @FXML
    private TableColumn<?, ?> tcDauxNo;

    @FXML
    private TableColumn<?, ?> tcDaux;

    @FXML
    private JFXTabPane tabClases1;

    @FXML
    private TextArea txtDasm;

    @FXML
    private WebView wbSalidaDasm;

    @FXML
    void clckAbrirCarpeta(ActionEvent event) {

    }

    @FXML
    void clckAcercaDe(ActionEvent event) {

    }

    @FXML
    void clckBtnSiguientePaso(ActionEvent event) {

    }

    @FXML
    void clckCerrar(ActionEvent event) {

    }

    @FXML
    void clckContinuar(ActionEvent event) {

    }

    @FXML
    void clckDepurar(ActionEvent event) {

    }

    @FXML
    void clckEjecutar(ActionEvent event) {

    }

    @FXML
    void clckEstadoEjecucion(ActionEvent event) {

    }

    @FXML
    void clckGuardar(ActionEvent event) {

    }

    @FXML
    void clckNuevaCarpeta(ActionEvent event) {

    }

    @FXML
    void clckNuevoArchivo(ActionEvent event) {

    }

    @FXML
    void clckSalir(ActionEvent event) {

    }

    @FXML
    void clckSiguienteLinea(ActionEvent event) {

    }

    @FXML
    void clckTabSimbolosDasm2(MouseEvent event) {

    }

    @FXML
    void clckTerminarTodo(ActionEvent event) {

    }

    @FXML
    void clckVistaArbol(MouseEvent event) {
        enventoClickVistaArbol(event);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
