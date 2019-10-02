/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui.Tablas;

import Controller.BancosController;
import Controller.RolesController;
import Model.BD.BDOpciones;
import Model.Bancos.Banco;
import Model.Roles.Rol;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Notebook
 */
public class TablaTresColumnas {
   
    private ObservableList<Object> contenidoTabla = FXCollections.observableArrayList();
    private TableView<Object> tb;

    private TableColumn tc0;
    private TableColumn tc1;
    private TableColumn tc2;

    public TablaTresColumnas(TableView<Object> tb, TableColumn tc0, TableColumn tc1,TableColumn tc2 ) {
        this.tb = tb;
        this.tc0 = tc0;
        this.tc1 = tc1;
        this.tc2 = tc2;
        //inicializarTabla();
    }

    public void inicializarColumnas(String text0, String text1, String text2) {

        tc0.setCellValueFactory(
                new PropertyValueFactory<>(text0));
        tc1.setCellValueFactory(
                new PropertyValueFactory<>(text1));
        tc2.setCellValueFactory(
                new PropertyValueFactory<>(text2));
    }

    /**
     * Inicializa la tabla
     */
    public void inicializarTabla(String text0, String text1, String text2) {
        
        inicializarColumnas(text0, text1, text2);
        tb.setItems(contenidoTabla);

    }

    public void clean() {
        contenidoTabla.clear();
    }

    /*
    +--------------------------------------
    | Metodos Para mostrar una tabla de roles
    +---------------------------------------
     */
    void mostrar(RolesController r) {

        //limpiando tabla
        contenidoTabla.clear();
        ArrayList<Rol> listaRoles = r.listRoles(BDOpciones.LimitOp.NO_LIMIT, BDOpciones.Orden.DESC, -1);

//        for (int i = 0; i < listaRoles.size(); i++) {
//            Rol temp = listaRoles.get(i);
//            elementoTabla nuevoItem = new elementoTabla(String.valueOf(temp.getId_rol()), String.valueOf(temp.getNombre()));
//            contenidoTabla.add(nuevoItem);
//        }
    }
     
}
