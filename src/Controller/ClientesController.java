/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Clientes.Cliente;
import Model.Clientes.ConsultasClientes;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ricar
 */
public class ClientesController {

    ConsultasClientes consultas;

    public ClientesController() {
        consultas = new ConsultasClientes();
    }

    /**
     * Funcion para crear un cliente
     *
     * @param nombre
     * @param direccion
     * @return
     */
    public boolean createCliente(String nombre, String direccion) {
        try {
            return consultas.save(new Cliente(nombre, direccion));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Funcion para actualizar un cliente
     *
     * @param id_cliente
     * @param nombre
     * @param direccion
     * @return
     */
    public boolean updateCliente(int id_cliente, String nombre, String direccion) {
        try {
            Cliente c = new Cliente(nombre, direccion);
            c.setId_cliente(id_cliente);
            return consultas.update(c);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Funcion para buscar un cliente por ID
     *
     * @param id
     * @return
     */
    public Cliente getCliente(int id) {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Funcion que lista los clientes
     *
     * @return
     */
    public ArrayList<Cliente> listClientes() {
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();
            ResultSet rs = consultas.listItems();
            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("nombre"), rs.getString("direccion"));
                c.setId_cliente(rs.getInt("id_cliente"));
                clientes.add(c);
            }
            return clientes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Funcion que lista los clientes TEst
     *
     * @return
     */
    public ArrayList<Cliente> listClientesTest() {
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();

            Date date = new Date();
            java.sql.Date dateNacimiento = new java.sql.Date(date.getTime());

            Cliente cliente = new Cliente(
                    1,
                    "Ricardo",
                    "Cutz",
                    "Guateamala",
                    "Ricardo@gmail.com",
                    "(342)32434",
                    dateNacimiento,
                    1,
                    "C:\\Users\\Notebook\\Pictures\\234.jpg",
                    "C:\\Users\\Notebook\\Pictures\\234.jpg"
            );
            clientes.add(cliente);

            return clientes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
