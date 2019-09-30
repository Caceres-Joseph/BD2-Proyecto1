/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Roles;

import Controller.RolesController;
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
public class TablaRol {

    private ObservableList<elementoTabla> contenidoTabla = FXCollections.observableArrayList();
    private TableView<elementoTabla> tbRol;

    private TableColumn tcNo;
    private TableColumn tcRol;

    public TablaRol(TableView<elementoTabla> tbRol, TableColumn tcNo, TableColumn tcRol) {
        this.tbRol = tbRol;
        this.tcNo = tcNo;
        this.tcRol = tcRol;
        inicializarTabla();
    }

    public void inicializarColumnas() {

        tcNo.setCellValueFactory(
                new PropertyValueFactory<>("No"));

        tcRol.setCellValueFactory(
                new PropertyValueFactory<>("Rol"));

    }

    public void mostrarBancos() {

        //limpiando tabla
        contenidoTabla.clear();

        elementoTabla nuevoItem = new elementoTabla(String.valueOf("1"), String.valueOf("Banco de guatemala"));
        contenidoTabla.add(nuevoItem);

    }

    /**
     * Inicializa la tabla
     */
    public void inicializarTabla() {
        inicializarColumnas();
        tbRol.setItems(contenidoTabla);

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
        ArrayList<Rol> listaRoles = r.listRoles();

        for (int i = 0; i < listaRoles.size(); i++) {
            Rol temp = listaRoles.get(i);
            elementoTabla nuevoItem = new elementoTabla(String.valueOf(temp.getId_rol()), String.valueOf(temp.getNombre()));
            contenidoTabla.add(nuevoItem);
        }
    }

    public class elementoTabla {

        public SimpleStringProperty no = new SimpleStringProperty();
        public SimpleStringProperty rol = new SimpleStringProperty();

        public elementoTabla() {

        }

        public elementoTabla(String no, String stack) {
            this.no = new SimpleStringProperty(no);
            this.rol = new SimpleStringProperty(stack);
        }

        public String getNo() {
            return no.get();
        }

        public String getRol() {
            return rol.get();
        }
    }
}
