/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Roles;

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

    /**
     * Muesta el contenido de Stack en la tabla
     *
     * @param Stack
     */
    public void mostrar() {

        //limpiando tabla
        contenidoTabla.clear();

        elementoTabla nuevoItem = new elementoTabla(String.valueOf("12"), String.valueOf(23));
        contenidoTabla.add(nuevoItem);

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
