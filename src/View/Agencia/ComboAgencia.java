/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Agencia;

import com.jfoenix.controls.JFXComboBox;
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

    private ObservableList<elementoCombo> contenidoTabla = FXCollections.observableArrayList();
    private JFXComboBox<elementoCombo> cbAgencia;

    public int id_Banco = -1;

    public ComboAgencia(JFXComboBox<elementoCombo> cbAgencia) {

        this.cbAgencia = cbAgencia;
        inicializarTabla();

        StringConverter<elementoCombo> converter = new StringConverter<elementoCombo>() {
            @Override
            public String toString(elementoCombo bank) {
                return bank.getValue();
            }

            @Override
            public elementoCombo fromString(String id) {
                return contenidoTabla.stream()
                        .filter(item -> item.getNo().equals(id))
                        .collect(Collectors.toList()).get(0);
            }
        };
        cbAgencia.setConverter(converter);

        // Print the name of the Bank that is selected
        cbAgencia.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {

            ComboAgencia.elementoCombo elemento = cbAgencia.getSelectionModel().selectedItemProperty().get();
            System.out.println(elemento.getNo());
            System.out.println(elemento.getValue());

            this.id_Banco = Integer.valueOf(elemento.getNo());

        });

    }

    /**
     * Muesta el contenido de Stack en la tabla
     *
     * @param Stack
     */
    public void mostrar() {

        //limpiando tabla
        contenidoTabla.clear();

        elementoCombo nuevoItem = new elementoCombo(String.valueOf("12"), String.valueOf(23));
        contenidoTabla.add(nuevoItem);

    }

    public void mostrarBancos() {

        //limpiando tabla
        contenidoTabla.clear();

        elementoCombo nuevoItem = new elementoCombo(String.valueOf("1"), String.valueOf("Banco de guatemala"));
        contenidoTabla.add(nuevoItem);

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

    public class elementoCombo {

        public SimpleStringProperty no = new SimpleStringProperty();
        public SimpleStringProperty value = new SimpleStringProperty();

        public elementoCombo() {

        }

        public elementoCombo(String no, String stack) {
            this.no = new SimpleStringProperty(no);
            this.value = new SimpleStringProperty(stack);
        }

        public String getNo() {
            return no.get();
        }

        public String getValue() {
            return value.get();
        }
    }
}
