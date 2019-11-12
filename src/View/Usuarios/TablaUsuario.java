/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Usuarios;

import Controller.UsuariosController;
import Model.BD.BDOpciones;
import Model.Usuarios.Usuario;
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
public class TablaUsuario {
    
    private final ObservableList<Usuario> contenidoTabla = FXCollections.observableArrayList();
    private final TableView<Usuario> tb;

    private final TableColumn tc0;
    private final TableColumn tc1; 

    public TablaUsuario(
            TableView<Usuario> tb,
            TableColumn tc0,
            TableColumn tc1
    ) {
        this.tb = tb;
        this.tc0 = tc0;
        this.tc1 = tc1; 

        inicializarTabla("Id_usuario", "Usuario");
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
    void mostrar(UsuariosController r) {

        //limpiando tabla
        contenidoTabla.clear();

        //Llenando la tabla
        ArrayList<Usuario> listaUsuario = r.listUsuarios(BDOpciones.LimitOp.NO_LIMIT, BDOpciones.Orden.DESC, -1);
        contenidoTabla.addAll(listaUsuario); 
    }
}
