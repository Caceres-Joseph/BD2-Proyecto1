/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cuenta;

import Controller.TipoCuentasController;
import Model.BD.BDOpciones;
import Model.TipoCuentas.TipoCuenta;
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
public class ComboTipoCuenta {
    
    private ObservableList<TipoCuenta> contenidoTabla = FXCollections.observableArrayList();
    private JFXComboBox<TipoCuenta> cbAgencia;

    public int id_TipoCuenta = -1;

    public ComboTipoCuenta(JFXComboBox<TipoCuenta> cbAgencia) {

        this.cbAgencia = cbAgencia;
        inicializarTabla();

        StringConverter<TipoCuenta> converter = new StringConverter<TipoCuenta>() {
            @Override
            public String toString(TipoCuenta bank) {
                return bank.getNombre();
            }

            @Override
            public TipoCuenta fromString(String id) {
                return contenidoTabla.stream()
                        //.filter(item -> item.getNo().equals(id))
                        .filter(item -> item.getId_tipo()== Integer.valueOf(id))
                        //.collect(Collectors.toList()).get(0);
                        .collect(Collectors.toList()).get(0);
            }

        };

        cbAgencia.setConverter(converter);

        // Print the name of the Bank that is selected
        cbAgencia.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {

            TipoCuenta elemento = cbAgencia.getSelectionModel().selectedItemProperty().get();
            System.out.println(elemento.getId_tipo());
            System.out.println(elemento.getNombre());

            this.id_TipoCuenta = elemento.getId_tipo();

        });

    }

    /**
     * Muesta el contenido de Stack en la tabla
     *
     * @param Stack
     */
    
    void mostrarTipoCuentas(TipoCuentasController b) {
        //limpiando tabla
        contenidoTabla.clear();
        ArrayList<TipoCuenta> listarTipoCuentas = b.listTipoCuentas(BDOpciones.LimitOp.NO_LIMIT, BDOpciones.Orden.DESC, -1);
        for (int i = 0; i < listarTipoCuentas.size(); i++) {
            TipoCuenta temp = listarTipoCuentas.get(i);
            contenidoTabla.add(temp);
        }
    }
    
    /**
     * Mostrando los estados activo y desactivado
     *
     */
    
    
    public void mostrarEstados() {
        
        TipoCuenta temp = new TipoCuenta("Activar");
        temp.setId_tipo(1);
        contenidoTabla.add(temp);
        
        
        TipoCuenta temp2 = new TipoCuenta("Bloquear");
        temp2.setId_tipo(0);
        contenidoTabla.add(temp2);
        
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
