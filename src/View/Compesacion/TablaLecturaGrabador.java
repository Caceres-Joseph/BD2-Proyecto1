/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Compesacion;

import Controller.GeneracionConciliacionController;
import Controller.RecibirConciliacionController;
import Model.ReConciliacion.ChequeConciliado;
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
public class TablaLecturaGrabador {

    private final ObservableList<ChequeConciliado> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<ChequeConciliado> tb;

    private final TableColumn tc0;
    private final TableColumn tc1;
    private final TableColumn tc2;
    private final TableColumn tc3;
    private final TableColumn tc4;

    public TablaLecturaGrabador(
            TableView<ChequeConciliado> tb,
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
        
    private int id_cheque;
    private int cuenta;
    private double valor;
    private int lote;
    private String estado;
    private int referencia;
         */
        inicializarTabla("Cuenta", "Referencia", "Id_cheque", "Valor", "Estado");
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


        //Llenando la tabla
//        ArrayList<ChequeConciliado> lista = con.listarLotes();
//        contenidoTabla.addAll(lista); 
    }

    void mostrar(RecibirConciliacionController con_Recibir, int idLote) {
    
        //limpiando tabla
        contenidoTabla.clear();
        
        ArrayList<ChequeConciliado> lista = con_Recibir.listCheques(idLote);
        contenidoTabla.addAll(lista); 
        
        
    }
}
