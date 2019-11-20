/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Compesacion;

import Controller.AgenciaBancoController;
import Controller.GeneracionConciliacionController;
import Model.Agencias.AgenciaBanco;
import Model.LoteTMP2.LoteTMP2;
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
public class TablaConciliacion {

    private final ObservableList<LoteTMP2> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<LoteTMP2> tb;

    private final TableColumn tc0;
    private final TableColumn tc1;
    private final TableColumn tc2;
    private final TableColumn tc3;
    private final TableColumn tc4;

    public TablaConciliacion(
            TableView<LoteTMP2> tb,
            TableColumn tc0,
            TableColumn tc1,
            TableColumn tc2,
            TableColumn tc3,
            TableColumn tc4
    ) {
        this.tb = tb;
        this.tc0 = tc0;
        this.tc1 = tc1;
        this.tc2 = tc2;
        this.tc3 = tc3;
        this.tc4 = tc4;

        
        /*
        
    private int id_lote_2;
    private int total_documentos;
    private double total_monto;
    private int id_banco;
    private int estado_lote;
    private String bancoName;
    private String estado_loteString;
        */
        inicializarTabla("Id_lote_2", "Total_documentos", "Total_monto", "BancoName", "Estado_loteString");
    }

    public void inicializarColumnas(String text0, String text1, String text2, String text3, String text4) {

        tc0.setCellValueFactory(
                new PropertyValueFactory<>(text0));
        tc1.setCellValueFactory(
                new PropertyValueFactory<>(text1));
        tc2.setCellValueFactory(
                new PropertyValueFactory<>(text2));
        tc3.setCellValueFactory(
                new PropertyValueFactory<>(text3));
        tc4.setCellValueFactory(
                new PropertyValueFactory<>(text4));
    }

    /**
     * Inicializa la tabla
     */
    private void inicializarTabla(String text0, String text1, String text2, String text3, String text4) {

        inicializarColumnas(text0, text1, text2, text3, text4);

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
    
    void mostrar(GeneracionConciliacionController con) {

        //limpiando tabla
        contenidoTabla.clear();

        //Llenando la tabla
        ArrayList<LoteTMP2> lista = con.listarLotes();
        contenidoTabla.addAll(lista); 
    }

}
