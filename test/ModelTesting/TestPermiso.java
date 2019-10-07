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
import Model.Permisos.ConsultasPermiso;
import Model.Permisos.Permiso;
/**
 *
 * @author ricar
 */
public class TestPermiso {
    ConsultasPermiso consultas;
    
    public  TestPermiso()
    {
        consultas = new ConsultasPermiso();
    }
    
    @Test
    public void listPermiso()
    {
        ArrayList<Permiso> permisos = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        assertEquals(1, permisos.size());
    }
    
    @Test
    public void listPermisoLimit()
    {
        ArrayList<Permiso> permisos = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 1);
        assertEquals(1, permisos.size());
    }
}
