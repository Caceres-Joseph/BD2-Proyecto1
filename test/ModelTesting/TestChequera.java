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
import Model.Chequera.Chequera;
import Model.Chequera.ConsultasChequera;
/**
 *
 * @author ricar
 */
public class TestChequera {
    ConsultasChequera consultas;
    
    public TestChequera()
    {
        consultas = new ConsultasChequera();
    }
    
    @Test
    public void ListData()
    {
        ArrayList<Chequera> chequeras = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1, 123);
        assertEquals(1, chequeras.size());
    }
}
