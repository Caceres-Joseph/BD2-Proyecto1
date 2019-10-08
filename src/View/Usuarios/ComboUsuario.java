/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Usuarios;

import Controller.UsuariosController;
import Model.BD.BDOpciones;
import Model.Usuarios.Usuario;
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
public class ComboUsuario {
    
    private ObservableList<Usuario> contenidoTabla = FXCollections.observableArrayList();
    private JFXComboBox<Usuario> cbAgencia;

    public int id_Usuario = -1;

    public ComboUsuario(JFXComboBox<Usuario> cbAgencia) {

        this.cbAgencia = cbAgencia;
        inicializarTabla();

        StringConverter<Usuario> converter = new StringConverter<Usuario>() {
            @Override
            public String toString(Usuario bank) {
                return bank.getUsuario();
            }

            @Override
            public Usuario fromString(String id) {
                return contenidoTabla.stream()
                        //.filter(item -> item.getNo().equals(id))
                        .filter(item -> item.getId_usuario()== Integer.valueOf(id))
                        //.collect(Collectors.toList()).get(0);
                        .collect(Collectors.toList()).get(0);
            }

        };

        cbAgencia.setConverter(converter);

        // Print the name of the Bank that is selected
        cbAgencia.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {

            Usuario elemento = cbAgencia.getSelectionModel().selectedItemProperty().get();
            System.out.println(elemento.getId_usuario());
            System.out.println(elemento.getUsuario());

            this.id_Usuario = elemento.getId_usuario();

        });

    }

    /**
     * Muesta el contenido de Stack en la tabla
     *
     * @param Stack
     */
    
    public void mostrarUsuarios(UsuariosController b) {
        //limpiando tabla
        contenidoTabla.clear();
        ArrayList<Usuario> listarUsuarios = b.listUsuarios(BDOpciones.LimitOp.NO_LIMIT, BDOpciones.Orden.DESC, -1);
//        ArrayList<Usuario> listarUsuarios = b.listUsuariosTest();
        for (int i = 0; i < listarUsuarios.size(); i++) {
            Usuario temp = listarUsuarios.get(i);
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
