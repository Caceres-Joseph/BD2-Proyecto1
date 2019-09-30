/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Agencia;

import Main.B2;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Controller.AgenciasController;
import Controller.BancosController;
import Model.Agencias.AgenciaBanco;
import Model.Bancos.Banco;
import java.util.ArrayList;
import javafx.scene.control.SingleSelectionModel;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevaAgencia implements Initializable {

    AgenciasController a = new AgenciasController();
    BancosController b = new BancosController();
    //int id_banco = -1;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtDIreccion;

    ComboAgencia comboAgencia;
    @FXML
    private JFXComboBox<Banco> cbBancos;

    @FXML
    void clckAceptar(ActionEvent event) {

        Banco elemento = cbBancos.getSelectionModel().selectedItemProperty().get();
        System.out.println(elemento.getId_banco());
        System.out.println(elemento.getNombre());

        String txtNombreRol = this.txtNombre.getText();
        String txtDireccion = this.txtDIreccion.getText();

        if (comboAgencia.id_Banco == -1) {

            B2.GuiController.mensajeConsola("Debe seleccionar un banco");
            return;
        }

        if (this.itemModificar == null) {

            if (insertar(txtNombreRol, txtDireccion, comboAgencia.id_Banco)) {

                B2.GuiController.mensajeConsola("Agencia insertada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar la Agencia");
            }
        } else {

            if (editar(txtNombreRol, txtDireccion, comboAgencia.id_Banco)) {

                B2.GuiController.mensajeConsola("Agencia actualizada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar la Agencia");
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        comboAgencia = new ComboAgencia(cbBancos);
        comboAgencia.mostrarBancos(b);
        
        System.out.println("Iniciando datos");
    }

    /*
    +--------------------------------------
    | Metodos de inserción
    +---------------------------------------
    
     */
    public boolean insertar(String nombre, String direccion, int id_Banco) {

        return a.createAgencia(nombre, direccion, id_Banco);
    }

    /*
    +--------------------------------------
    | Metodos de modificación y carga de datos
    +---------------------------------------
    
     */
    private AgenciaBanco itemModificar;

    public void initData(AgenciaBanco item) {
        this.itemModificar = item;
        this.txtNombre.setText(item.getNombre());
        this.txtDIreccion.setText(item.getDireccion());
        
        int seleccionado= this.getIndexParaEditar(item.getId_banco());
//        cbBancos.getSelectionModel().select(seleccionado);
        
        //cbBancos.setValue(seleccionado);
        
        cbBancos.getSelectionModel().select(seleccionado); 

    }

    public int getIndexParaEditar(int idBanco) {

        ArrayList<Banco> listarBancos = b.listBancosTest();
        for (int i = 0; i < listarBancos.size(); i++) {
            Banco temp = listarBancos.get(i);

            if (temp.getId_banco() == idBanco) {
                System.out.println("Se encontró el id del banco: "+String.valueOf(idBanco));
                return i;
            }
        }
        return -1;
    }

    public boolean editar(String nombre, String direccion, int id_Banco) {
        return a.updateAgencia(this.itemModificar.getId_agencia(), nombre, direccion, id_Banco);
    }

}
