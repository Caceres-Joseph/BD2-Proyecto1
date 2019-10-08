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
import Model.Cuentas.ConsultasCuenta;
import Model.Cuentas.Cuenta;
import Model.Cuentas.CuentaBancoTipo;
/**
 *
 * @author ricar
 */
public class TestCuenta {
    
    ConsultasCuenta consultas;
    
    public TestCuenta()
    {
        consultas = new ConsultasCuenta();
    }
    
    @Test
    public void listData()
    {
        ArrayList<Cuenta> cuentas = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.NO_LIMIT, -1);
        assertEquals(3, cuentas.size());
    }
    
    @Test
    public void listDataLimit()
    {
        ArrayList<Cuenta> cuentas = consultas.listData(BDOpciones.Orden.DESC, BDOpciones.LimitOp.LIMIT, 2);
        assertEquals(2, cuentas.size());
    }
    
    @Test
    public void listDataCuentaBanco()
    {
        ArrayList<CuentaBancoTipo> cuentas = consultas.listCuentaTipo(1, 1);
        assertEquals(1, cuentas.size());
    }
}
