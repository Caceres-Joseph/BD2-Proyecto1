/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cheque;

import Controller.ChequeController;
import Controller.PagoChequeController;
import Main.B2;
import Model.TipoCuentas.TipoCuenta;
import View.Cuenta.ComboTipoCuenta;
import View.Gui.Componentes.mascaras;
import com.jfoenix.controls.JFXComboBox;
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
public class Reportar implements Initializable {

    ChequeController con_cheque = new ChequeController();

    @FXML
    private JFXComboBox<TipoCuenta> cbEstados;

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

            if (con_cheque.EstadoCheque(Integer.valueOf(txtNoCheque.getText()), Integer.valueOf(txtCuenta.getText()), comboTipo.id_TipoCuenta)) {
                B2.GuiController.mensajeConsola("Se cambió el estado del : " + txtNoCheque.getText());
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
    ComboTipoCuenta comboTipo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //llenando el combo
        comboTipo = new ComboTipoCuenta(cbEstados);
        comboTipo.mostrarEstadosCheque();

        mskCuenta = new mascaras(txtCuenta, false);
        mskCuenta.setMaskNumero();

        mskCheque = new mascaras(txtNoCheque, false);
        mskCheque.setMaskNumero();

    }

    /**
     * Validando que se hayan ingrasado todos los valores
     *
     * @return
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

        if (comboTipo.id_TipoCuenta == -1) {
            B2.GuiController.mensajeConsola("Debe seleccionar un estado para el cheque");
            return false;
        }

        return true;
    }

}
