/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Agencias.AgenciaBanco;
import java.util.ArrayList;
import Model.Agencias.ConsultasAgencia;
import Model.BD.BDOpciones;
/**
 *
 * @author Notebook
 */
public class AgenciaBancoController extends AgenciasController {
    
    ConsultasAgencia consultas;
    
    
    public  AgenciaBancoController()
    {
        consultas = new ConsultasAgencia();
    }
    
    /**
     * Retorna los items listados del mas reciente al menos TEST
     *
     * @return
     */
    public ArrayList<AgenciaBanco> listBancosTest() {
        try {
            return consultas.listaData(BDOpciones.Orden.DESC);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
