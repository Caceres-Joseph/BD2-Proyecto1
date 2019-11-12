/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BD.BDOpciones;
import Model.Bancos.Banco;
import Model.Bancos.ConsultasBanco;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class BancosController {
    
    ConsultasBanco consultas;
    public BancosController()
    {
        consultas = new ConsultasBanco();
    }
    
    /**
     * Funcion para almacenar un nuevo Banco en la BD
     * @param nombre
     * @return 
     */
    public boolean createBanco(String nombre)
    {
        try {
            Banco b = new Banco(nombre);
            return consultas.save(b);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para poder actualizar un banco nuevo
     * @param id_banco
     * @param nombre
     * @return 
     */
    public boolean updateBanco(int id_banco, String nombre)
    {
        try {
            Banco b = new Banco(nombre);
            b.setId_banco(id_banco);
            return consultas.update(b);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para buscar por ID
     * @param id
     * @return 
     */
    public Banco getBanco(int id)
    {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Retorna los items listados del mas reciente al menos...
     * @return 
     */
    public ArrayList<Banco> listBancos(BDOpciones.LimitOp l, BDOpciones.Orden orden, int limite)
    {
        try {
            ArrayList<Banco> bancos = consultas.listData(orden, l, limite);
            return bancos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    
    /**
     * Retorna los items listados del mas reciente al menos...
     * Sirve para test
     * @return 
     */
    
    public ArrayList<Banco> listBancosTest()
    {
        try {
            ArrayList<Banco> bancos = new ArrayList<>();
           Banco bank=new Banco("Guatemala");
           bank.setId_banco(1);
           bank.setEstado_banco(1);
           
           bancos.add(bank);
            
            
            return bancos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
