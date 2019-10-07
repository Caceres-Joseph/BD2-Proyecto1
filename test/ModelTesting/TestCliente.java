/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelTesting;
import Model.BD.BDOpciones;
import Model.Clientes.Cliente;
import Model.Clientes.ConsultasClientes;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class TestCliente {
    ConsultasClientes consultas;
    
    public TestCliente()
    {
        consultas = new ConsultasClientes();
    }
    
    @Test
    public void listData()
    {
        ArrayList<Cliente> clientes = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        assertEquals(3, clientes.size());
    }
    
    @Test
    public void listDataLimit()
    {
        ArrayList<Cliente> clientes = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 2);
        assertEquals(2, clientes.size());
    }
}
