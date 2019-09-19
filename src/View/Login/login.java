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
    void btnAceptar(ActionEvent event) throws IOException {
         
        
        
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
