/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui.Elementos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Platform;
import javafx.event.ActionEvent; 
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author joseph
 */
public class elementoMensaje {
    public StackPane stackPadre;
    public elementoMensaje(StackPane stackPadre)
    {
        this.stackPadre=stackPadre;
    }
    
    public void informacion(String titulo, String mensaje) {

        Platform.runLater(() -> {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(titulo));
            content.setBody(new Text(mensaje));

            JFXDialog dialog = new JFXDialog(stackPadre, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("Aceptar");

            button.setStyle("-fx-background-color:  #37474F; -fx-alignment: CENTER;-fx-text-fill: white;");
            button.setOnAction((ActionEvent event) -> {
                dialog.close();
            });
            content.setActions(button);
            dialog.show();
        });
    }
}
