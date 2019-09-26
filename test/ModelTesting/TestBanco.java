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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void testSave()
    {
        //assertTrue(consultas.save(new Banco("BancoTest")));
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
