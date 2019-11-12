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
import Model.Cheque.Cheque;
import Model.Cheque.ConsultasCheque;
/**
 *
 * @author ricar
 */
public class TestCheque {
    ConsultasCheque consultas;
    
    public TestCheque()
    {
        consultas = new ConsultasCheque();
    }
    
    @Test
    public void listData()
    {
        ArrayList<Cheque> cheques = consultas.listDataByChequeraID(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1, 1);
        assertEquals(1, cheques.size());
    }
    
}
