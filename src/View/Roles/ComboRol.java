/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Roles;
 
import Controller.RolesController;
import Model.BD.BDOpciones; 
import Model.Roles.Rol;
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
public class ComboRol {
    
    private ObservableList<Rol> contenidoTabla = FXCollections.observableArrayList();
    private JFXComboBox<Rol> cbAgencia;

    public int id_Rol = -1;

    public ComboRol(JFXComboBox<Rol> cbAgencia) {

        this.cbAgencia = cbAgencia;
        inicializarTabla();

        StringConverter<Rol> converter = new StringConverter<Rol>() {
            @Override
            public String toString(Rol bank) {
                return bank.getNombre();
            }

            @Override
            public Rol fromString(String id) {
                return contenidoTabla.stream()
                        //.filter(item -> item.getNo().equals(id))
                        .filter(item -> item.getId_rol()== Integer.valueOf(id))
                        //.collect(Collectors.toList()).get(0);
                        .collect(Collectors.toList()).get(0);
            }

        };

        cbAgencia.setConverter(converter);

        // Print the name of the Bank that is selected
        cbAgencia.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {

            Rol elemento = cbAgencia.getSelectionModel().selectedItemProperty().get();
 

            this.id_Rol = elemento.getId_rol();

        });

    }

    /**
     * Muesta el contenido de Stack en la tabla
     *
     * @param Stack
     */
    
    public void mostrarRols(RolesController b) {
        //limpiando tabla
        contenidoTabla.clear();
        ArrayList<Rol> listarRols = b.listRoles(BDOpciones.LimitOp.NO_LIMIT, BDOpciones.Orden.DESC, -1);
//        ArrayList<Rol> listarRols = b.listRolesTest();
        for (int i = 0; i < listarRols.size(); i++) {
            Rol temp = listarRols.get(i);
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
