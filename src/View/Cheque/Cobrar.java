/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cheque;

import Controller.ChequeController;
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

    ChequeController con_cheque = new ChequeController();
    
    
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
            if (con_cheque.CrearChequera(Integer.valueOf(txtCuenta.getText()), 1)) {
                B2.GuiController.mensajeConsola("Se creó la chequera para la cuenta: " + txtCuenta.getText());
            }else{
                B2.GuiController.mensajeConsola("Ocurrió un error al crear la chequera");
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        mskCuenta = new mascaras(txtCuenta, false);
        mskCuenta.setMaskNumero();
        
        
        mskCheque = new mascaras(txtNoCheque, false);
        mskCheque.setMaskNumero();
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

        return true;
    }
    
}
