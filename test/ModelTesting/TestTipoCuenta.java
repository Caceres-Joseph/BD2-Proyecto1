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
import Model.TipoCuentas.*;
/**
 *
 * @author ricar
 */
public class TestTipoCuenta {
    ConsultasTipoCuenta consultas;
    
    public TestTipoCuenta()
    {
        consultas = new ConsultasTipoCuenta();
    }
    
    @Test
    public void listData()
    {
        ArrayList<TipoCuenta> tipocuentas = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        assertEquals(1, tipocuentas.size());
    }
    
    @Test
    public void listDataLimit()
    {
        ArrayList<TipoCuenta> tipocuentas = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 0);
        assertEquals(0, tipocuentas.size());
    }
}
