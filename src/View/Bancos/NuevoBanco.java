/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Bancos;

import Main.B2;
import View.Roles.NuevoRol;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Controller.BancosController;
/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevoBanco extends NuevoRol {

    BancosController b = new BancosController();
    @FXML
    void clckAceptar(ActionEvent event) {

        String txtNombreRol = this.txtNombreRol.getText();
        if (this.id_rol == -1) {

            if (insertarBanco(txtNombreRol)) {

                B2.GuiController.mensajeConsola("Banco insertado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar el Banco");
            }
        } else {

            if (editarBanco(txtNombreRol, id_rol)) {

                B2.GuiController.mensajeConsola("Banco actualizado exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al actualizar el Banco");
            }

        }

    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    /*
    +--------------------------------------
    | Metodos de inserción o modificación
    +---------------------------------------
    
     */
    public boolean insertarBanco(String nombre) {
        return b.createBanco(nombre);
    }

    public boolean editarBanco(String nombre, int id_banco) {
        return b.updateBanco(id_banco, nombre);
    }
    
}
