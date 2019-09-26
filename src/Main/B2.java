/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.Permisos.JsonPermiso;
import View.Gui.Gui; 
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Notebook
 */
public class B2 extends Application {

    public static Stage sta;
    public static Gui GuiController;


    @Override
    public void start(Stage stage) throws IOException {
        
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Gui/Gui.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login/login.fxml"));
        Parent root = loader.load();
    


        stage.setTitle("Bases 2");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((WindowEvent we) -> {
            System.exit(0);
        });

        sta = stage;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
