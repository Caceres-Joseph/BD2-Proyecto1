/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelTesting;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import Model.BD.BDOpciones;
import Model.Usuarios.ConsultaUsuarios;
import Model.Usuarios.Usuario;
/**
 *
 * @author ricar
 */
public class TestUsuario {
    ConsultaUsuarios consultas;
    
    public TestUsuario()
    {
        consultas = new ConsultaUsuarios();
    }
    
    @Test
    public void listData()
    {
        ArrayList<Usuario> usuarios = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        assertEquals(3, usuarios.size());
    }
    
    @Test
    public void listDataLimit()
    {
        ArrayList<Usuario> usuarios = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 2);
        assertEquals(2, usuarios.size());
    }
}
