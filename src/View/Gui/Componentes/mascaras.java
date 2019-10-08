/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui.Componentes;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Notebook
 */
public class mascaras {

    JFXTextField txtField;
    public boolean estado;

    public mascaras(JFXTextField txtField, boolean estado) {
        this.txtField = txtField;
        this.estado = estado;

    }

    public mascaras(JFXTextField txtField) {
        this.txtField = txtField;
        this.estado = true;

    }

    public void setMaskCorreo() {

        this.txtField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!mskCorreo(newValue)) {
                this.esIncorrecto();
            } else {
                this.esCorrecto();
            }
        });
    }

    public void setMaskTelefono() {

        this.txtField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!mskTelefono(newValue)) {
                this.esIncorrecto();
            } else {
                this.esCorrecto();
            }
        });
    }
    
    public void setMaskDpi() {

        this.txtField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!mskDpi(newValue)) {
                this.esIncorrecto();
            } else {
                this.esCorrecto();
            }
        });
    }
    

    private void esCorrecto() {
        this.txtField.setStyle("-fx-background-color:white");
        this.estado = true;
    }

    private void esIncorrecto() {
        this.txtField.setStyle("-fx-background-color:#F6CED8");
        this.estado = false; 
    }

    /*
    +--------------------------------------
    | Metodos de inserción o modificación
    +---------------------------------------
     */
    public boolean mskTelefono(String telefono) {
        if (telefono.matches("[(]\\d{1,7}[)]\\d{1,10}")) {
            return true;
        }
        return false;
    }

    public boolean mskCorreo(String telefono) {
        if (telefono.matches("\\S+@\\S+\\.\\S+")) {
            return true;
        }
        return false;
    }
      
    public boolean mskDpi(String dpi) {
        if (dpi.matches("\\d{1,10}")) {
            return true;
        }
        return false;
    }
     
}
