/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cuenta;

import Controller.CuentasController;
import Main.B2;
import Model.Cuentas.Estado;
import Model.TipoCuentas.TipoCuenta;
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
public class AdministrarCuenta implements Initializable {

    ComboTipoCuenta comboTipo;
    CuentasController con_cuenta = new CuentasController();

    @FXML
    private JFXTextField txtCuenta;

    @FXML
    private JFXComboBox<TipoCuenta> cbEstados;

    @FXML
    void clckAceptar(ActionEvent event) {

        System.out.println(comboTipo.id_TipoCuenta);

        if (!validar()) {
            return;
        }

        try {

            if (con_cuenta.estadoCuenta(Integer.valueOf(txtCuenta.getText()), comboTipo.id_TipoCuenta)) 
            {
                B2.GuiController.mensajeConsola("Se cambió el estado de la cuenta: " + txtCuenta.getText());
            }

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }
    }

    @FXML
    void clckCancelar(ActionEvent event) {

    }

    mascaras mskCuenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //llenando el combo
        comboTipo = new ComboTipoCuenta(cbEstados);
        comboTipo.mostrarEstados();

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

        if (comboTipo.id_TipoCuenta == -1) {
            B2.GuiController.mensajeConsola("Debe seleccionar un estado para la cuenta");
            return false;
        }

        return true;
    }
}
