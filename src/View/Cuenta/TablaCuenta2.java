/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cuenta;

import Controller.AgenciaBancoController;
import Controller.CuentasController;
import Model.Agencias.AgenciaBanco;
import Model.Cuentas.Cuenta;
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
public class TablaCuenta2 {
    
    private final ObservableList<Cuenta> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<Cuenta> tb;

    private final TableColumn tc0;
    private final TableColumn tc1;
    private final TableColumn tc2;
    private final TableColumn tc3;

    public TablaCuenta2(
            TableView<Cuenta> tb,
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
        
        /*
        
    private int no_cuenta;
    private double saldo;
    private double saldoTotal=0;
    private double saldoReserva=0; 
    private int banco_id_banco;
    private int tipo_cuenta_id_tipo;
    private int estado_cuenta;
    
        */

        inicializarTabla("No_cuenta", "Saldo", "SaldoReserva", "SaldoTotal");
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
    void mostrar(CuentasController r) {

        //limpiando tabla
        contenidoTabla.clear();

        //Llenando la tabla
        ArrayList<Cuenta> listaAgenciaBanco = r.listCuentas();
        contenidoTabla.addAll(listaAgenciaBanco); 
    }
}
