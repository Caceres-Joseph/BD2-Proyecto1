/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Compesacion;

import Controller.BancosController;
import Controller.GeneracionConciliacionController;
import Main.B2;
import Model.Bancos.Banco;
import View.Agencia.ComboAgencia;
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
public class CobrarCheque implements Initializable {

    BancosController b = new BancosController();
    GeneracionConciliacionController con = new GeneracionConciliacionController();

    @FXML
    private JFXTextField txtCuentaActual;

    @FXML
    private JFXTextField txtMonto;

    @FXML
    private JFXTextField txtCuenta;

    @FXML
    private JFXTextField txtNoCheque;

    @FXML
    private JFXComboBox<Banco> cbBancos;

    @FXML
    void clckAceptar(ActionEvent event) {

        if (!validar()) {
            return;
        }

        try {

            if (con.cobrar_chequeExterno(
                    Integer.valueOf(txtCuentaActual.getText()),
                    Integer.valueOf(txtCuenta.getText()),
                    Integer.valueOf(txtNoCheque.getText()),
                    Double.valueOf(txtMonto.getText()),
                     comboAgencia.id_Banco)) {
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

    mascaras mskCuentaActual;

    mascaras mskCuenta;
    mascaras mskCheque;
    mascaras mskMonto;
    ComboAgencia comboAgencia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mskCuentaActual = new mascaras(txtCuentaActual, false);
        mskCuentaActual.setMaskNumero();

        mskCuenta = new mascaras(txtCuenta, false);
        mskCuenta.setMaskNumero();

        mskCheque = new mascaras(txtNoCheque, false);
        mskCheque.setMaskNumero();

        mskMonto = new mascaras(txtMonto, false);
        mskMonto.setMaskDecimalEntero();

        comboAgencia = new ComboAgencia(cbBancos);
        comboAgencia.mostrarBancos(b);
    }

    /**
     * Validando que se hayan ingrasado todos los valores
     *
     */
    public boolean validar() {

        if (!mskCuentaActual.estado) {
            B2.GuiController.mensajeConsola("Debe insertar una cuenta del banco actual válido");
            return false;
        }

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

        if (comboAgencia.id_Banco == -1) {
            B2.GuiController.mensajeConsola("Debe seleccionar un banco para el cheque");
            return false;
        }

        return true;
    }

}
