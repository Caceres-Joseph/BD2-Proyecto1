/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelTesting;
import Model.Agencias.Agencia;
import Model.Agencias.ConsultasAgencia;
import Model.BD.BDOpciones;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class TestAgencia {
    ConsultasAgencia consultas;
    
    public TestAgencia()
    {
        consultas = new ConsultasAgencia();
    }
    
    @Test
    public void testList()
    {
        ArrayList<Agencia> agencias = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        assertEquals(3, agencias.size());
    }
    
    @Test
    public void testListLimit()
    {
        ArrayList<Agencia> agencias = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 2);
        assertEquals(2, agencias.size());
    }
}
