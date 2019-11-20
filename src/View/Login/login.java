/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Login;

import static Main.B2.GuiController;
import static Main.B2.sta;
import View.Gui.Gui;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;
import Controller.UsuariosController;
import Main.B2;

/**
 * FXML Controller class
 *
 * @author joseph
 */
public class login implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnAceptar(ActionEvent event) throws IOException{
        UsuariosController usuario = new UsuariosController();
        String tUsuario= this.txtUsuario.getText();
        String tPassword = this.txtPassword.getText();
        

        
        if (usuario.UserLogin(tUsuario, tPassword)) {
            this.success_login();
            B2.usuario = usuario.getUser(tUsuario);
        }
        else
        {
            // SII NO PERMITE EL LOGUEO
            System.err.println("no logueo");
//            this.success_login();
        }
    }
    
    
    public void success_login() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Gui/Gui.fxml"));
            Parent root = loader.load();

            GuiController = (Gui) loader.getController();

            sta.setTitle("Bases 2");
            Scene scene = new Scene(root);
            sta.setScene(scene);

            sta.setMaximized(true);
            sta.show();
            sta.setOnCloseRequest((WindowEvent we) -> {
                System.exit(0);
            });
    }

}
