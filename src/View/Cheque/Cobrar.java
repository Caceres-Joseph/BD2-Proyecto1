/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cheque;

import Controller.ChequeController;
import Controller.PagoChequeController;
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
public class Cobrar implements Initializable {

    PagoChequeController con_cheque = new PagoChequeController();

    @FXML
    private JFXTextField txtMonto;

    @FXML
    private JFXTextField txtCuenta;

    @FXML
    private JFXTextField txtNoCheque;

    @FXML
    void clckAceptar(ActionEvent event) {
        if (!validar()) {
            return;
        }

        try {
            if (con_cheque.createTransaccionCheque(Integer.valueOf(txtNoCheque.getText()), Integer.valueOf(txtCuenta.getText()), Double.parseDouble(txtMonto.getText()))) {
                B2.GuiController.mensajeConsola("Se cobró el cheque de forma exitosa");
            } else {
                B2.GuiController.mensajeConsola("Ocurrió un error al cobrar el cheque");
            }

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }
    }

    @FXML
    void clckCancelar(ActionEvent event) {

    }

    mascaras mskCuenta;
    mascaras mskCheque;

    mascaras mskMonto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mskCuenta = new mascaras(txtCuenta, false);
        mskCuenta.setMaskNumero();

        mskCheque = new mascaras(txtNoCheque, false);
        mskCheque.setMaskNumero();

        mskMonto = new mascaras(txtMonto, false);
        mskMonto.setMaskDecimalEntero();

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

        if (!mskCheque.estado) {
            B2.GuiController.mensajeConsola("Debe insertar un número de cheque válido");
            return false;
        }

        if (!mskMonto.estado) {
            B2.GuiController.mensajeConsola("Debe insertar un Monto válido");
            return false;
        }

        return true;
    }

}
