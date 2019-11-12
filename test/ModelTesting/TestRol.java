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
import Model.Roles.ConsultasRoles;
import Model.Roles.Rol;
/**
 *
 * @author ricar
 */
public class TestRol {
    ConsultasRoles consultas;
    
    public TestRol()
    {
        consultas = new ConsultasRoles();
    }
    
    @Test
    public void ListRoles()
    {
        ArrayList<Rol> roles = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        assertEquals(1, roles.size());
    }
    
    @Test
    public void ListRolesLimit()
    {
        ArrayList<Rol> roles = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 0);
        assertEquals(0, roles.size());
    }
}
