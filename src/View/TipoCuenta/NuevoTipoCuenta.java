/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.TipoCuenta;


import Controller.TipoCuentasController;
import Main.B2;
import Model.TipoCuentas.TipoCuenta;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevoTipoCuenta implements Initializable {

    TipoCuentasController a = new TipoCuentasController();
     

    @FXML
    private JFXTextField txtNombre;

    @FXML
    void clckAceptar(ActionEvent event) {
        
        String txtNombreRol = this.txtNombre.getText(); 
 
        if (this.itemModificar == null) {

            if (insertar(txtNombreRol)) {

                B2.GuiController.mensajeConsola("Tipo cuenta insertada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar la Tipo cuenta");
            }
        } else {

            if (editar(txtNombreRol)) {

                B2.GuiController.mensajeConsola("Tipo cuenta actualizada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar la Tipo cuenta");
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    TipoCuenta itemModificar;
    /*
    +--------------------------------------
    | Metodos de inserción
    +---------------------------------------
    
     */
    public boolean insertar(String nombre) {

        return a.createTipoCuenta(nombre);
    }

    /*
    +--------------------------------------
    | Metodos de modificación y carga de datos
    +---------------------------------------
    
     */
    

    public void initData(TipoCuenta item) {
        this.itemModificar = item;
        this.txtNombre.setText(item.getNombre()); 
          
    }
 

    public boolean editar(String nombre) {
        return a.updateTipoCuenta(this.itemModificar.getId_tipo(), nombre);
    }

}
