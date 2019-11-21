/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Terminal;

import Controller.AgenciaBancoController;
import Controller.AgenciasController;
import Model.Agencias.Agencia;
import Model.Agencias.AgenciaBanco;
import Model.BD.BDOpciones;
import com.jfoenix.controls.JFXComboBox;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

/**
 *
 * @author Notebook
 */
public class ComboAgen {
    
    private ObservableList<AgenciaBanco> contenidoTabla = FXCollections.observableArrayList();
    private JFXComboBox<AgenciaBanco> cbAgencia;

    public int id_Agencia = -1;

    public ComboAgen(JFXComboBox<AgenciaBanco> cbAgencia) {

        this.cbAgencia = cbAgencia;
        inicializarTabla();

        StringConverter<AgenciaBanco> converter = new StringConverter<AgenciaBanco>() {
            @Override
            public String toString(AgenciaBanco bank) {
                return bank.getNombre_banco()+"-"+bank.getNombre();
            }

            @Override
            public AgenciaBanco fromString(String id) {
                return contenidoTabla.stream()
                        //.filter(item -> item.getNo().equals(id))
                        .filter(item -> item.getId_agencia()== Integer.valueOf(id))
                        //.collect(Collectors.toList()).get(0);
                        .collect(Collectors.toList()).get(0);
            }

        };

        cbAgencia.setConverter(converter);

        // Print the name of the Bank that is selected
        cbAgencia.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {

            Agencia elemento = cbAgencia.getSelectionModel().selectedItemProperty().get();
            System.out.println(elemento.getId_agencia());
            System.out.println(elemento.getNombre());

            this.id_Agencia = elemento.getId_agencia();

        });

    }

    /**
     * Muesta el contenido de Stack en la tabla
     *
     * @param Stack
     */
    
    void mostrarAgencias(AgenciaBancoController b) {
        //limpiando tabla
        contenidoTabla.clear();
        ArrayList<AgenciaBanco> listarAgencias = b.listBancosTest();
        for (int i = 0; i < listarAgencias.size(); i++) {
            AgenciaBanco temp = listarAgencias.get(i);
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
