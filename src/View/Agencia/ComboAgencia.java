/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Agencia;

import Controller.BancosController;
import Model.BD.BDOpciones;
import Model.Bancos.Banco;
import View.Roles.TablaRol;
import com.jfoenix.controls.JFXComboBox;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 *
 * @author Notebook
 */
public class ComboAgencia {

    private ObservableList<Banco> contenidoTabla = FXCollections.observableArrayList();
    private JFXComboBox<Banco> cbAgencia;

    public int id_Banco = -1;

    public ComboAgencia(JFXComboBox<Banco> cbAgencia) {

        this.cbAgencia = cbAgencia;
        inicializarTabla();

        StringConverter<Banco> converter = new StringConverter<Banco>() {
            @Override
            public String toString(Banco bank) {
                return bank.getNombre();
            }

            @Override
            public Banco fromString(String id) {
                return contenidoTabla.stream()
                        //.filter(item -> item.getNo().equals(id))
                        .filter(item -> item.getId_banco() == Integer.valueOf(id))
                        //.collect(Collectors.toList()).get(0);
                        .collect(Collectors.toList()).get(0);
            }

        };

        cbAgencia.setConverter(converter);

        // Print the name of the Bank that is selected
        cbAgencia.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {

            Banco elemento = cbAgencia.getSelectionModel().selectedItemProperty().get();
            System.out.println(elemento.getId_banco());
            System.out.println(elemento.getNombre());

            this.id_Banco = elemento.getId_banco();

        });

    }

    /**
     * Muesta el contenido de Stack en la tabla
     *
     * @param Stack
     */
    
    public void mostrarBancos(BancosController b) {
        //limpiando tabla
        contenidoTabla.clear();
        ArrayList<Banco> listarBancos = b.listBancos(BDOpciones.LimitOp.NO_LIMIT, BDOpciones.Orden.DESC, -1);
        //ArrayList<Banco> listarBancos = b.listBancosTest();
        for (int i = 0; i < listarBancos.size(); i++) {
            Banco temp = listarBancos.get(i);
            contenidoTabla.add(temp);
        }
    }
     
    /**
     * Inicializa la tabla
     */
    public void inicializarTabla() {

        cbAgencia.setItems(contenidoTabla);

    }

    public void clean() {
        contenidoTabla.clear();
    }


}
