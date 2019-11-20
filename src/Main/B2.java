/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.GeneracionConciliacionController;
import Model.ChequeTMP2.ConsultasChequeTMP2;
import Model.LoteTMP2.LoteExport;
import Model.LoteTMP2.LoteTMP2;
import Model.Permisos.JsonPermiso;
import Model.Usuarios.UsuarioSession;
import Model.Reportes.Auditoria;
import Model.Reportes.Grafica;
import Model.Reportes.Grafica2;
import Model.Reportes.Grafica3;
import Model.Reportes.Grafica4;
import Model.Reportes.Grafica5;
import Model.Reportes.ReporteGerencia;
import View.Gui.Gui;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    public static UsuarioSession usuario;

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
    public static void main(String[] args)  {

        launch(args); 
    }

}