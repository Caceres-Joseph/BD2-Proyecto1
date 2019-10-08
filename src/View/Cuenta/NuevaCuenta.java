/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cuenta;

import Controller.BancosController;
import Controller.CuentasController;
import Controller.TipoCuentasController;
import Main.B2;
import Model.Bancos.Banco;
import Model.Cuentas.Cuenta;
import Model.Cuentas.CuentaBancoTipo;
import Model.TipoCuentas.TipoCuenta;
import View.Agencia.ComboAgencia;
import View.Gui.Componentes.mascaras;
import com.jfoenix.controls.JFXComboBox;
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
public class NuevaCuenta implements Initializable {

    CuentasController c = new CuentasController();
    TipoCuentasController t = new TipoCuentasController();
    BancosController b = new BancosController();

    ComboAgencia comboAgencia;
    ComboTipoCuenta comboTipo;

    @FXML
    private JFXTextField txtDpi;

    @FXML
    private JFXComboBox<TipoCuenta> cbTipo;

    @FXML
    private JFXComboBox<Banco> cbBancos;

    @FXML
    void clckAceptar(ActionEvent event) {

        Banco elemento = cbBancos.getSelectionModel().selectedItemProperty().get();
        System.out.println(elemento.getId_banco());
        System.out.println(elemento.getNombre());

        String dpi = this.txtDpi.getText();

        if (comboAgencia.id_Banco == -1) {

            B2.GuiController.mensajeConsola("Debe seleccionar un banco");
            return;
        }

        if (comboTipo.id_TipoCuenta == -1) {

            B2.GuiController.mensajeConsola("Debe seleccionar un tipo cuenta");
            return;
        }

        if (this.itemModificar == null) {

            if (insertar(Integer.valueOf(dpi), comboTipo.id_TipoCuenta, comboAgencia.id_Banco)) {
                CuentaBancoTipo cuenta = c.getLastCuentaFrom(Integer.valueOf(dpi), 1);
                B2.GuiController.mensajeConsola("Cuenta insertada exitosamente, No Cuenta: "+String.valueOf(cuenta.getNo_cuenta()));
            } else {

                B2.GuiController.mensajeConsola("Ocurrió un error al insertar la Agencia");
            }
        } else {

        }
    }
    mascaras mskDpi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboTipo = new ComboTipoCuenta(cbTipo);
        comboTipo.mostrarTipoCuentas(t);

        comboAgencia = new ComboAgencia(cbBancos);
        comboAgencia.mostrarBancos(b);

        mskDpi = new mascaras(txtDpi, false);
        mskDpi.setMaskDpi();

        System.out.println("Iniciando datos");
    }


    /*
    +--------------------------------------
    | Metodos de modificación y carga de datos
    +---------------------------------------
    
     */
    private Cuenta itemModificar;

    /*
    +--------------------------------------
    | Metodos de inserción
    +---------------------------------------
    
     */
    public boolean insertar(int dpi, int id_Tipo, int id_Banco) {
        boolean result = c.createCuenta(0.00, id_Banco, id_Tipo, dpi);
        
        return result;
    }

}
