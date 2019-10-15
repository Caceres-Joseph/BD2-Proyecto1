/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.Permisos.JsonPermiso;
import Model.Reportes.Auditoria;
import Model.Reportes.Grafica;
import Model.Reportes.Grafica2;
import Model.Reportes.Grafica3;
import Model.Reportes.Grafica4;
import Model.Reportes.Grafica5;
import Model.Reportes.ReporteGerencia;
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
        //Auditoria auditoria = new Auditoria();
        //auditoria.show();
//        Grafica grafica = new Grafica("Grafica1");
//        grafica.show();
//        Grafica2 grafica2 = new Grafica2("Grafica2");
//        grafica2.show();
//        Grafica3 grafica3 = new Grafica3("Grafica3");
//        grafica3.show();
//        Grafica4 grafica4 = new Grafica4();
//        grafica4.show();
//        Grafica5 grafica5 = new Grafica5("Grafica5");
//        grafica5.show();
    }

}
