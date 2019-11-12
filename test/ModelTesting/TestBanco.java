/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelTesting;
import Model.BD.BDOpciones;
import org.junit.Test;
import static org.junit.Assert.*;
import Model.Bancos.Banco;
import Model.Bancos.ConsultasBanco;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class TestBanco {
    ConsultasBanco consultas;
    
    public TestBanco()
    {
        consultas = new ConsultasBanco();
    }
    
    @Test
    public void testList()
    {
        ArrayList<Banco> bancos = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 1);
        assertEquals(1, bancos.size());
    }
    
    @Test
    public void testListLike()
    {
        ArrayList<Banco> bancos = consultas.listDataLike(BDOpciones.Orden.DESC, "aasd");
        assertEquals(0, bancos.size());
    }
    
}
