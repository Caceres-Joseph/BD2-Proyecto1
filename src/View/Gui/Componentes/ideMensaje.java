/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui.Componentes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 *
 * @author Notebook
 */
public class ideMensaje extends ideComponentes {

    @FXML
    public TextArea txtConsola;

    public void mensajeConsola(String mensaje) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        txtConsola.appendText("\n["+dtf.format(now)+"] "+mensaje);
    }
}
