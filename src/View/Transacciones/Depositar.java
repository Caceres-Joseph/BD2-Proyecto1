/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Transacciones;

import Controller.TransaccionController;
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
public class Depositar implements Initializable {

    TransaccionController con_transferencia = new TransaccionController();

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtCuenta;

    @FXML
    private JFXTextField txtMonto;

    @FXML
    void clckAceptar(ActionEvent event) {

        if (!validar()) {
            return;
        }

        try {
            if (con_transferencia.Acreditar(Integer.valueOf(txtCuenta.getText()), Double.parseDouble(txtMonto.getText())))
                B2.GuiController.mensajeConsola("Se acreditó: "+txtMonto.getText()+ " con éxito de la cuenta "+txtCuenta.getText());

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }
    }
    
    
    @FXML
    void clckDebitar(ActionEvent event) {

        if (!validar()) {
            return;
        }

        try {
            if (con_transferencia.Debitar(Integer.valueOf(txtCuenta.getText()), Double.parseDouble(txtMonto.getText())))
                B2.GuiController.mensajeConsola("Se debitó: "+txtMonto.getText()+ " con éxito de la cuenta "+txtCuenta.getText());

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }
    }

    @FXML
    void clckCancelar(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */

    mascaras mskMonto;
    mascaras mskCuenta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mskMonto = new mascaras(txtMonto, false);
        mskMonto.setMaskDecimalEntero();

        mskCuenta = new mascaras(txtCuenta, false);
        mskCuenta.setMaskNumero();
    }

    /**
     * Validando que se hayan ingrasado todos los valores
     *
     */
    public boolean validar() {

        if (!mskCuenta.estado) {
            B2.GuiController.mensajeConsola("Debe insertar una cuenta válida");
            return false;
        }

        if (!mskMonto.estado) {
            B2.GuiController.mensajeConsola("Debe insertar un Monto válido");
            return false;
        }

        return true;
    }

}
