/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Agencias.AgenciaBanco;
import java.util.ArrayList;

/**
 *
 * @author Notebook
 */
public class AgenciaBancoController extends AgenciasController {

    /**
     * Retorna los items listados del mas reciente al menos TEST
     *
     * @return
     */
    public ArrayList<AgenciaBanco> listBancosTest() {
        try {
            ArrayList<AgenciaBanco> bancos = new ArrayList<>();
            AgenciaBanco bank = new AgenciaBanco("Agencia 1", "Ciudad de Gutemala", 1, "Banco de Guatemala");
            AgenciaBanco bank2 = new AgenciaBanco("Agencia 2", "Tecpán", 2, "Banco de Chimaltenango");

            bancos.add(bank);
            bancos.add(bank2);

            return bancos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
