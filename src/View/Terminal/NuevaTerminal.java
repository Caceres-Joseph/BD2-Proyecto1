/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Terminal;

import Controller.AgenciasController;
import Controller.TerminalController;
import Controller.UsuariosController;
import Main.B2;
import Model.Agencias.Agencia;
import Model.Terminal.Terminal;
import Model.Usuarios.Usuario;
import View.Agencia.ComboAgencia;
import View.Usuarios.ComboUsuario;
import com.jfoenix.controls.JFXComboBox;
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
public class NuevaTerminal implements Initializable {

    UsuariosController u = new UsuariosController();
    AgenciasController a = new AgenciasController();
    TerminalController t = new TerminalController();

    ComboAgen comboAgencia;
    ComboUsuario comboUsuario;

    @FXML
    private JFXComboBox<Usuario> cbUsuario;

    @FXML
    private JFXComboBox<Agencia> cbAgencia;

    @FXML
    void clckAceptar(ActionEvent event) {

        Usuario selectUsuario = cbUsuario.getSelectionModel().selectedItemProperty().get();
        Agencia selectAgencia = cbAgencia.getSelectionModel().selectedItemProperty().get();

        if (comboAgencia.id_Agencia == -1) {

            B2.GuiController.mensajeConsola("Debe seleccionar una agencia");
            return;
        }

        if (comboUsuario.id_Usuario == -1) {

            B2.GuiController.mensajeConsola("Debe seleccionar un usuario");
            return;
        }

        if (this.itemModificar == null) {

            if (insertar(comboAgencia.id_Agencia, comboUsuario.id_Usuario)) {

                B2.GuiController.mensajeConsola("Terminal insertada exitosamente ");
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar la Agencia");
            }
        } else {

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboAgencia = new ComboAgen(cbAgencia);
        comboAgencia.mostrarAgencias(a);

        comboUsuario = new ComboUsuario(cbUsuario);
        comboUsuario.mostrarUsuarios(u);

    }

    /*
    +--------------------------------------
    | Metodos de modificación y carga de datos
    +---------------------------------------
    
     */
    private Terminal itemModificar;

    /*
    +--------------------------------------
    | Metodos de inserción
    +---------------------------------------
    
     */
    public boolean insertar(int idAgencia, int idUsuario) {
        return t.createTerminal(idAgencia, idUsuario);
    }

}
