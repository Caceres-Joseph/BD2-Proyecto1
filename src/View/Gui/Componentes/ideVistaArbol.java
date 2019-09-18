/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui.Componentes;


import Main.B2;
import java.io.File; 
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author joseph
 */
public class ideVistaArbol extends ideComponentes{
    
    
    /**<br>+----------------------------------------
     * <br>| Vista de los directorios
     * <br>+----------------------------------------
     */
    @FXML
    protected TreeView<nodoVistaArbol> vistaArbol; 
    
    
    
    public void enventoClickVistaArbol(MouseEvent event) {
        if (event.getClickCount() == 2) {

            //Obteniendo los valores al hacer doble click
//            TreeItem<nodoVistaArbol> selectedItem = vistaArbol.getSelectionModel().getSelectedItem();
//            nodoVistaArbol nodVist = selectedItem.getValue();
// 
            

        
            System.out.println("Doble click");
        }
    }
    
    
    /**
     * Metodo para verificar la extensión del archivo
     * @param extension El nombre de la extensión que se quiere corroborar
     * @param nombre Nombre del archivo
     * @return 
     */
    
    public boolean esExtension(String extension, String nombre){
//        System.out.println("[ideVistaArbol]"+nombre);
        String[] extenArr= nombre.split("\\.");
        if(extenArr.length==2){
            String exten= extenArr[1];
            return exten.toLowerCase().equals(extension);
        }else{
//            System.out.println("[ideVistaArbol]No es igual a 2 : "+String.valueOf(extenArr.length));
            return false;
        }
    }
    
    /**
     * Abrirá un dialogo para seleccionar la ubicación de la carpeta
     */
    public void cargarCarpeta(){ 
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home")));
        File choice = dc.showDialog(B2.sta);
        if(choice == null || ! choice.isDirectory()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Could not open directory");
            alert.setContentText("The file is invalid.");

            alert.showAndWait();
        } else {
            
            vistaArbol.setRoot(getNodesForDirectory(choice));
        }
    }
    
    
    /**
     * Recorriendo todos los directorios para cargar el elemento
     * @param directory
     * @return 
     */
    public TreeItem<nodoVistaArbol> getNodesForDirectory(File directory) { //Returns a TreeItem representation of the specified directory
        TreeItem<nodoVistaArbol> root = new TreeItem<>(new nodoVistaArbol(directory.getName(),directory.getPath()), new ImageView("Gui/img/ic_folder_black_24dp_1x.png"));
//        root.setExpanded(true);
        
        
        for(File f : directory.listFiles()) {
//            System.out.println("[ideVistaArbol][getNodesForDirectory]Loading " + f.getName());
            //println("[getNodesForDirectory]Loading " + f.getName());
//            println("[getNodesForDirectory]Paht"+f.getPath());
            if(f.isDirectory()) { //Then we call the function recursively
                root.getChildren().add(getNodesForDirectory(f));
            } else {
                
                ImageView ima=new ImageView("Gui/img/baseline_insert_drive_file_black_18dp.png");
                ima.setOpacity(0.5);
                root.getChildren().add(new TreeItem<>(new nodoVistaArbol(f.getName(),f.getPath()),ima ));
            }
        }
        return root;
    }
      
    public void println(String mensaje){
        System.out.println("[ideVistaArbol]"+mensaje);
    } 
}
