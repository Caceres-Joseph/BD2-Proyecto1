/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Terminal;

import View.Agencia.*;
import Controller.CuentasController;
import Controller.RolesController;
import Controller.TerminalController;
import Model.Cuentas.Cuenta;
import Model.Roles.Rol; 
import Model.Terminal.Terminal;
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
public class TablaTerminal {
    
    private final ObservableList<Terminal> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<Terminal> tb;
 
    private final TableColumn tc1; 

    public TablaTerminal(
            TableView<Terminal> tb, 
            TableColumn tc1
    ) {
        this.tb = tb; 
        this.tc1 = tc1; 

        inicializarTabla("No_cuenta");
    }

    public void inicializarColumnas(String text1) {
 
        tc1.setCellValueFactory(
                new PropertyValueFactory<>(text1)); 
    }

    /**
     * Inicializa la tabla
     */
    private void inicializarTabla(String text1) {

        inicializarColumnas(text1);

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
    void mostrar(TerminalController r) {

        //limpiando tabla
        contenidoTabla.clear();

        //Llenando la tabla
        ArrayList<Terminal> listaTerminal = r.listTerminalTest();
        contenidoTabla.addAll(listaTerminal); 
    }
}
