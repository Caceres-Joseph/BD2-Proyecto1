/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Clientes;
 
import Controller.ClientesController;
import Model.Clientes.Cliente; 
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
public class TablaCliente {

    private final ObservableList<Cliente> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<Cliente> tb;

    private final TableColumn tc0;
    private final TableColumn tc1;
    private final TableColumn tc2;
    private final TableColumn tc3;
    private final TableColumn tc4;
    private final TableColumn tc5;

    public TablaCliente(
            TableView<Cliente> tb,
            TableColumn tc0,
            TableColumn tc1,
            TableColumn tc2,
            TableColumn tc3,
            TableColumn tc4,
            TableColumn tc5
    ) {
        this.tb = tb;
        this.tc0 = tc0;
        this.tc1 = tc1;
        this.tc2 = tc2;
        this.tc3 = tc3;
        this.tc4 = tc4;
        this.tc5 = tc5;

        inicializarTabla("Id_cliente", "Nombre", "Apellido", "Direccion", "Correo", "Telefono");
    }

    public void inicializarColumnas(String text0, String text1, String text2, String text3, String text4, String text5) {

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
        tc5.setCellValueFactory(
                new PropertyValueFactory<>(text5));
    }

    /**
     * Inicializa la tabla
     */
    private void inicializarTabla(String text0, String text1, String text2, String text3, String text4, String text5) {

        inicializarColumnas(text0, text1, text2, text3, text4, text5);

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
    void mostrar(ClientesController r) {

        //limpiando tabla
        contenidoTabla.clear();

        //Llenando la tabla
        ArrayList<Cliente> listaCliente = r.listClientes();
        contenidoTabla.addAll(listaCliente);
    }
}
