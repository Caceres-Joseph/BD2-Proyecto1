/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Permisos;

import Main.B2;
import View.Gui.Componentes.nodoVistaArbol; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Notebook
 */
public class JsonPermiso {

    private modulos modulos = new modulos();

    private String permisos = "{\n"
            + "	\"roles\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"bancos\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"usuarios\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"clientes\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"agencias\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "	}\n"
            + "	\n"
            + "}";

    public JsonPermiso(String usuario, String password) {

        JSONObject obj = new JSONObject(permisos);
        Iterator<String> keys = obj.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(key);
            if (obj.get(key) instanceof JSONObject) {

                modulo nuevoModulo = new modulo(key);

                JSONObject value = (JSONObject) obj.get(key);
                JSONArray array = value.getJSONArray("permisos");
                for (int i = 0; i < array.length(); i++) {
                    String permiso = array.getString(i);
                    nuevoModulo.insertarPermiso(permiso);
                }

                this.modulos.insertarModulo(nuevoModulo);
            }
        }
//
//        List<String> list = new ArrayList<>();
//        JSONArray array = obj.getJSONArray("interests");
//        for (int i = 0; i < array.length(); i++) {
//            list.add(array.getJSONObject(i).getString("interestKey"));
//        }
//
//        obj.getJSONObject(permisos)
//        
//        for (int i = 0; i < array.length(); i++) {
//            array.getJSONObject(i).getString("interestKey");
//        }

    }

    public modulos getModulos() {
        return this.modulos;
    }

    public class modulos {

        TreeItem<nodoVistaArbol> root;
        ArrayList<modulo> listaModulos = new ArrayList();

        public modulos() {
            nodoVistaArbol nodo = new nodoVistaArbol("Módulos", "/");
            ImageView image = new ImageView("View/Gui/img/ic_folder_black_24dp_1x.png");
            this.root = new TreeItem<>(nodo, image);
        }

        public void insertarModulo(modulo nuevo) {
            this.listaModulos.add(nuevo);

            root.getChildren().add(nuevo.module);
        }

        public TreeItem<nodoVistaArbol> getRoot() {
            return root;
        }

    }

    public class modulo {

        public TreeItem<nodoVistaArbol> module;
        String nombre;
        permisos permisos = new permisos();

        modulo(String nombre) {
            this.nombre = nombre;
            this.module = new TreeItem<>(new nodoVistaArbol(nombre, "/"), new ImageView("View/Gui/img/ic_folder_black_24dp_1x.png"));
        }

        public void insertarPermiso(String nombre) {
            this.permisos.insertar(nombre, module, this.nombre);
        }
    }

    public class permisos {

        ArrayList<String> permisos = new ArrayList<>();

        public permisos() {

        }

        public void insertar(String permiso, TreeItem<nodoVistaArbol> root, String nombreModulo) {
            this.permisos.add(permiso);
            ImageView ima = new ImageView("View/Gui/img/baseline_insert_drive_file_black_18dp.png");
            ima.setOpacity(0.5);

            String rutaControlador = "";
            if (nombreModulo.equals("roles")) {

                if (permiso.equals("crear")) {
                    rutaControlador = "/View/Roles/NuevoRol.fxml";
                } else if (permiso.equals("listar")) {
                    rutaControlador = "/View/Roles/ListarRol.fxml";

                } else if (permiso.equals("modificar")) {
                    rutaControlador = "/View/Roles/EditarRol.fxml";

                } else if (permiso.equals("eliminar")) {
                    
                    //rutaControlador = "/View/Roles/EliminarRol.fxml";

                } else {

                    B2.GuiController.mensajeConsola("No se reconoció el permiso: " + permiso + " del módulo: " + nombreModulo);
                }
            } else if (nombreModulo.toLowerCase().equals("bancos")) {

                if (permiso.equals("crear")) {
                    rutaControlador = "/View/Bancos/NuevoBanco.fxml";
                } else if (permiso.equals("listar")) {
                    rutaControlador = "/View/Bancos/ListarBanco.fxml";

                } else if (permiso.equals("modificar")) {
                    rutaControlador = "/View/Bancos/EditarBanco.fxml";

                } else if (permiso.toLowerCase().equals("eliminar")) {
                    
                    //rutaControlador = "/View/Roles/EliminarRol.fxml";

                } else {

                    B2.GuiController.mensajeConsola("No se reconoció el permiso: " + permiso + " del módulo: " + nombreModulo);
                }
            }else if (nombreModulo.toLowerCase().equals("agencias")) {

                if (permiso.equals("crear")) {
                    rutaControlador = "/View/Agencia/NuevaAgencia.fxml";
                } else if (permiso.equals("listar")) {
                    rutaControlador = "/View/Agencia/ListarAgencia.fxml";

                } else if (permiso.equals("modificar")) {
                    rutaControlador = "/View/Agencia/EditarAgencia.fxml";

                } else if (permiso.equals("eliminar")) {
                    
                    //rutaControlador = "/View/Roles/EliminarRol.fxml";

                } else {

                    B2.GuiController.mensajeConsola("No se reconoció el permiso: " + permiso + " del módulo: " + nombreModulo);
                }
            }else if (nombreModulo.toLowerCase().equals("usuarios")) {

                if (permiso.equals("crear")) {
                    rutaControlador = "/View/Usuarios/NuevoUsuario.fxml";
                } else if (permiso.equals("listar")) {
                    rutaControlador = "/View/Bancos/ListarBanco.fxml";

                } else if (permiso.equals("modificar")) {
                    rutaControlador = "/View/Bancos/EditarBanco.fxml";

                } else if (permiso.equals("eliminar")) {
                    
                    //rutaControlador = "/View/Roles/EliminarRol.fxml";

                } else {

                    B2.GuiController.mensajeConsola("No se reconoció el permiso: " + permiso + " del módulo: " + nombreModulo);
                }
            }else if (nombreModulo.toLowerCase().equals("clientes")) {

                if (permiso.equals("crear")) {
                    rutaControlador = "/View/Clientes/NuevoCliente.fxml";
                } else if (permiso.equals("listar")) {
                    rutaControlador = "/View/Clientes/ListarCliente.fxml";

                } else if (permiso.equals("modificar")) {
                    rutaControlador = "/View/Bancos/EditarBanco.fxml";

                } else if (permiso.equals("eliminar")) {
                    
                    //rutaControlador = "/View/Roles/EliminarRol.fxml";

                } else {

                    B2.GuiController.mensajeConsola("No se reconoció el permiso: " + permiso + " del módulo: " + nombreModulo);
                }
            }

            root.getChildren().add(new TreeItem<>(new nodoVistaArbol(permiso, rutaControlador), ima));
        }
    }

}
