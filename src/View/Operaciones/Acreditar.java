/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Operaciones;

import Main.B2;
import View.Gui.Componentes.mascaras;
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
public class Acreditar implements Initializable {

    @FXML
    private JFXTextField txtMonto;

    @FXML
    private JFXTextField txtCuenta;

    @FXML
    void clckAceptar(ActionEvent event) {
        try {
            if (this.insertar(Integer.valueOf(txtCuenta.getText()), Double.parseDouble(txtMonto.getText()))) {

                B2.GuiController.mensajeConsola("Acreditación realizada exitosamente");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al debitar");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    mascaras mskSaldo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        mskSaldo = new mascaras(txtMonto, false);
        mskSaldo.setMaskDecimalEntero();

    }

    /*
    +--------------------------------------
    | Metodos de inserción
    +---------------------------------------
    
     */
    public boolean insertar(int no_cuenta, double monto) {

        
        System.out.println(monto);
        return true;
    }

}
