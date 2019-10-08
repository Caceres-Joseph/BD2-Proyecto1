/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Clientes;

import Controller.ClientesController;
import Main.B2;
import static Main.B2.sta;
import Model.Clientes.Cliente;
import View.Gui.Componentes.mascaras;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Notebook
 */
public class NuevoCliente implements Initializable {

    ClientesController controller = new ClientesController();

    int id_Cliente = -1;

    String pathImageFirma = "";
    String pathImageFoto = "";

    @FXML
    private JFXTextField txtDPI;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtDireccion;

    @FXML
    private JFXTextField txtApellido;

    @FXML
    private JFXTextField txtCorreo;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private JFXDatePicker txtNacimiento;

    @FXML
    private ImageView imgFoto;

    @FXML
    private ImageView imgFirma;

    @FXML
    void clckAceptar(ActionEvent event) {

        if (!validar()) {
            return;
        }

        //Hay que corregir esto
        String dpi = "dpi";
        try {
            Cliente cliente = new Cliente(
                    Integer.valueOf(txtDPI.getText()),
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtDireccion.getText(),
                    txtCorreo.getText(),
                    txtTelefono.getText(),
                    dateNacimiento,
                    1,
                    pathImageFirma,
                    pathImageFoto
            );

            if (this.itemModificar == null) {
                if (insertar(cliente)) {
                    B2.GuiController.mensajeConsola("Cliente insertado exitosamente");
                } else {
                    B2.GuiController.mensajeConsola("Ocurrió un error al insertar el Cliente");
                }
            } else {
                if (editar(cliente)) {
                    B2.GuiController.mensajeConsola("Cliente actualizado exitosamente");
                } else {
                    B2.GuiController.mensajeConsola("Ocurrió un error al actualizar el Cliente");
                }
            }
        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
        }

    }

    @FXML
    void clckFirma(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png")
        );

        File file = fileChooser.showOpenDialog(sta);

        if (file != null) {
            this.cargarFirma(file.getAbsolutePath());
        }
    }

    public void cargarFirma(String url) {
        pathImageFirma = url;
        FileInputStream input = null;
        try {
            input = new FileInputStream(pathImageFirma);
            Image image = new Image(input);
            imgFirma.setImage(image);
        } catch (FileNotFoundException ex) {
            B2.GuiController.mensajeConsola(ex.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                B2.GuiController.mensajeConsola(ex.getMessage());
            }
        }
    }

    @FXML
    void clckFoto(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png")
        );

        File file = fileChooser.showOpenDialog(sta);

        if (file != null) {
            this.cargarFoto(file.getAbsolutePath());
        }
    }

    public void cargarFoto(String url) {
        pathImageFoto = url;
        FileInputStream input = null;
        try {
            input = new FileInputStream(pathImageFoto);
            Image image = new Image(input);
            imgFoto.setImage(image);
        } catch (FileNotFoundException ex) {
            B2.GuiController.mensajeConsola(ex.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                B2.GuiController.mensajeConsola(ex.getMessage());
            }
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    mascaras mskTelefono;
    mascaras mskCorreo;
    mascaras mskDpi;
    java.sql.Date dateNacimiento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mskTelefono = new mascaras(txtTelefono, false);
        mskTelefono.setMaskTelefono();

        mskCorreo = new mascaras(txtCorreo, false);
        mskCorreo.setMaskCorreo();

        mskDpi = new mascaras(txtDPI, false);
        mskDpi.setMaskDpi();
    }

    /**
     * Validando que se hayan ingrasado todos los valores
     *
     */
    public boolean validar() {

        if (!mskDpi.estado) {
            B2.GuiController.mensajeConsola("Debe insertar un DPI válido");
            return false;
        }

        if (txtNombre.getText().length() < 1) {
            B2.GuiController.mensajeConsola("Debe escribir el nombre");
            return false;
        }

        if (txtApellido.getText().length() < 1) {
            B2.GuiController.mensajeConsola("Debe escribir el apellido");
            return false;
        }

        if (txtDireccion.getText().length() < 1) {
            B2.GuiController.mensajeConsola("Debe escribir la dirección");
            return false;
        }

        if (!mskCorreo.estado) {
            B2.GuiController.mensajeConsola("Debe insertar un correo válido");
            return false;
        }

        if (!mskTelefono.estado) {
            B2.GuiController.mensajeConsola("Debe insertar un teléfono válido");
            return false;
        }

        try {

            LocalDate localDate = txtNacimiento.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date fecha1 = Date.from(instant);
            dateNacimiento = new java.sql.Date(fecha1.getTime());

        } catch (RuntimeException e) {
            B2.GuiController.mensajeConsola("Debe ingresar una fecha válida");
            return false;
        }

        return true;
    }

    /*
     +--------------------------------------
     | Metodos de inserción o modificación
     +---------------------------------------
    
     */
    public boolean insertar(Cliente cliente) {

        return controller.createCliente(cliente);
    }

    public boolean editar(Cliente cliente) {
        return controller.updateCliente(cliente);
    }

    Cliente itemModificar;

    public void initData(Cliente item) {
        this.itemModificar = item;
        this.txtNombre.setText(item.getNombre());

        this.txtNombre.setText(item.getNombre());

        this.txtNombre.setText(item.getNombre());
        this.txtDPI.setText(String.valueOf(item.getId_cliente()));
        this.txtApellido.setText(item.getApellido());
        this.txtDireccion.setText(item.getDireccion());
        this.txtCorreo.setText(item.getCorreo());
        this.txtTelefono.setText(item.getTelefono());

        this.cargarFirma(item.getFirma());
        this.cargarFoto(item.getFoto());

        //enviando la fecha
        Date date = item.getFecha_nacimiento();
        DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
        String fecha = dateFormat.format(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(fecha, formatter);
        txtNacimiento.setValue(localDate);

    }

}
