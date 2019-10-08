/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cuenta;

import Controller.CuentasController;
import Main.B2;
import Model.Cuentas.Cuenta;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class ListarCuenta implements Initializable {

    TablaCuenta tablaCuenta;
    CuentasController c = new CuentasController();

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private TableView<Cuenta> tb;

    @FXML
    private TableColumn tc1;

    @FXML
    void clckEditar(ActionEvent event) {

    }

    @FXML
    void clckEliminar(ActionEvent event) {

        Cuenta selected = tb.getSelectionModel().getSelectedItem();

        //Validación
        if (selected != null) { 
            if (eliminar(selected)) { 
                B2.GuiController.mensajeConsola("Cuenta eliminada exitosamente");
            } else {
                B2.GuiController.mensajeConsola("No ha podido eliminar la cuenta");
            }

            return;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicializando tabla
        tablaCuenta = new TablaCuenta(tb, tc1);
        //Cargando la tabla con datos
        tablaCuenta.mostrar(c);
        //Imprimiendo mensaje en consola
        B2.GuiController.mensajeConsola("Listando agencias");
    }

    /*
    +--------------------------------------
    | Metodos de inserción o modificación
    +---------------------------------------
    
     */
    public boolean eliminar(Cuenta cuenta) {

//        c.updateCuenta(cuenta.getNo_cuenta(), 0.00, cuenta.getBanco_id_banco(), cuenta.getTipo_cuenta_id_tipo());
        return true;
    }

}
