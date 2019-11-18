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
public class Transferir implements Initializable {

    TransaccionController con_transferencia = new TransaccionController();

     

    @FXML
    private JFXTextField txtMonto;

    @FXML
    private JFXTextField txtOrigen;

    @FXML
    private JFXTextField txtDestino;

    @FXML
    void clckCancelar(ActionEvent event) {

    }

    @FXML
    void clckTransferir(ActionEvent event) {

        if (!validar()) {
            return;
        }

        try {
            if (con_transferencia.Transferencia(Integer.valueOf(txtOrigen.getText()),Integer.valueOf(txtDestino.getText()), Double.parseDouble(txtMonto.getText())))
                B2.GuiController.mensajeConsola("Transeferencia con monto: "+txtMonto.getText()+ " de la cuenta "+txtOrigen.getText()+ " a la cuenta "+txtDestino.getText());

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }
    }
    
    /**
     * Initializes the controller class.
     */
    
    mascaras mskMonto;
    mascaras mskOrigen;
    mascaras mskDestino;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
     
        mskMonto = new mascaras(txtMonto, false);
        mskMonto.setMaskDecimalEntero();

        mskOrigen = new mascaras(txtOrigen, false);
        mskOrigen.setMaskNumero();
        
        mskDestino = new mascaras(txtDestino, false);
        mskDestino.setMaskNumero();
        
    }    
    
    
    /**
     * Validando que se hayan ingrasado todos los valores
     *
     */
    public boolean validar() {

        if (!mskOrigen.estado) {
            B2.GuiController.mensajeConsola("Debe insertar una cuenta válida");
            return false;
        }
        
        if (!mskDestino.estado) {
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
