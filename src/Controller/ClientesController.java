/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BD.BDOpciones;
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
     * @param cliente
     * @return
     */
    public boolean createCliente(Cliente cliente) {
        try {
            return consultas.save(cliente);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Funcion para actualizar un cliente
     *
     * @param cliente
     * @return
     */
    public boolean updateCliente(Cliente cliente) {
        try {
            
            return consultas.update(cliente);
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

            /*Date date = new Date();
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
            clientes.add(cliente);*/

            return consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
