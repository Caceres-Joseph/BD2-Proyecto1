/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.TipoCuenta;

import Controller.TipoCuentasController;
import Model.BD.BDOpciones;
import Model.TipoCuentas.TipoCuenta;
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
public class TablaTipoCuenta {
    
    private final ObservableList<TipoCuenta> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<TipoCuenta> tb;

    private final TableColumn tc0;
    private final TableColumn tc1; 

    public TablaTipoCuenta(
            TableView<TipoCuenta> tb,
            TableColumn tc0,
            TableColumn tc1
    ) {
        this.tb = tb;
        this.tc0 = tc0;
        this.tc1 = tc1; 

        inicializarTabla("Id_tipo", "Nombre");
    }

    public void inicializarColumnas(String text0, String text1) {

        tc0.setCellValueFactory(
                new PropertyValueFactory<>(text0));
        tc1.setCellValueFactory(
                new PropertyValueFactory<>(text1)); 
    }

    /**
     * Inicializa la tabla
     */
    private void inicializarTabla(String text0, String text1) {

        inicializarColumnas(text0, text1);

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
    void mostrar(TipoCuentasController r) {

        //limpiando tabla
        contenidoTabla.clear();

        //Llenando la tabla
        ArrayList<TipoCuenta> listaTipoCuenta = r.listTipoCuentas(BDOpciones.LimitOp.NO_LIMIT, BDOpciones.Orden.DESC, -1);
        contenidoTabla.addAll(listaTipoCuenta); 
    }
}
