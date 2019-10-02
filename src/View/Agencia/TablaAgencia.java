/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Agencia;

import Controller.AgenciaBancoController;
import Controller.RolesController;
import Model.Agencias.AgenciaBanco;
import Model.Roles.Rol; 
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Notebook
 */
public class TablaAgencia {

    private final ObservableList<AgenciaBanco> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<AgenciaBanco> tb;

    private final TableColumn tc0;
    private final TableColumn tc1;
    private final TableColumn tc2;
    private final TableColumn tc3;

    public TablaAgencia(
            TableView<AgenciaBanco> tb,
            TableColumn tc0,
            TableColumn tc1,
            TableColumn tc2,
            TableColumn tc3
    ) {
        this.tb = tb;
        this.tc0 = tc0;
        this.tc1 = tc1;
        this.tc2 = tc2;
        this.tc3 = tc3;

        inicializarTabla("Id_agencia", "Nombre", "Direccion", "Nombre_banco");
    }

    public void inicializarColumnas(String text0, String text1, String text2, String text3) {

        tc0.setCellValueFactory(
                new PropertyValueFactory<>(text0));
        tc1.setCellValueFactory(
                new PropertyValueFactory<>(text1));
        tc2.setCellValueFactory(
                new PropertyValueFactory<>(text2));
        tc3.setCellValueFactory(
                new PropertyValueFactory<>(text3));
    }

    /**
     * Inicializa la tabla
     */
    private void inicializarTabla(String text0, String text1, String text2, String text3) {

        inicializarColumnas(text0, text1, text2, text3);

        tb.setItems(contenidoTabla);

    }

    public void clean() {
        contenidoTabla.clear();
    }

    /*
    +--------------------------------------
    | Metodo para llenar la tabla
    +---------------------------------------
     */
    void mostrar(AgenciaBancoController r) {

        //limpiando tabla
        contenidoTabla.clear();

        //Llenando la tabla
        ArrayList<AgenciaBanco> listaAgenciaBanco = r.listBancosTest();
        contenidoTabla.addAll(listaAgenciaBanco); 
    }
}
