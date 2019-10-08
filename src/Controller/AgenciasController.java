/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Agencias.Agencia;
import Model.Agencias.ConsultasAgencia;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class AgenciasController {
    ConsultasAgencia consultas;
    
    public AgenciasController()
    {
        consultas = new ConsultasAgencia();
    }
    
    /**
     * Funcion para crear una nueva agencia
     * @param nombre
     * @param direccion
     * @param id_banco
     * @return 
     */
    public boolean createAgencia(String nombre, String direccion, int id_banco)
    {
        try {
            return consultas.save(new Agencia(nombre, direccion, id_banco));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para actualizar la agencia
     * @param id_agencia
     * @param nombre
     * @param direccion
     * @param id_banco
     * @return 
     */
    public boolean updateAgencia(int id_agencia, String nombre, String direccion, int id_banco)
    {
        try {
            Agencia a = new Agencia(nombre, direccion, id_banco);
            a.setId_agencia(id_agencia);
            return consultas.update(a);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para retornar una agencia especifica
     * @param id
     * @return 
     */
    public Agencia getAgencia(int id)
    {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Lista todas las agencias
     * @return 
     */
    public ArrayList<Agencia> listAgencias()
    {
        try {
            ArrayList<Agencia> agencias = new ArrayList<>();
            ResultSet rs = consultas.listItems();
            while(rs.next())
            {
                Agencia a = new Agencia(rs.getString("nombre"), rs.getString("direccion"), rs.getInt("banco_id_banco"));
                a.setId_agencia(rs.getInt("id_agencia"));
                agencias.add(a);
            }
            return agencias;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Lista todas las agencias  TEST
     * @return 
     */
    public ArrayList<Agencia> listAgenciasTest() {
    
        try {
            ArrayList<Agencia> agencias = new ArrayList<>();
            ResultSet rs = consultas.listItems();
             
            
            Agencia nueva=new Agencia("Guate", "Guate", 1);
            agencias.add(nueva);
            return agencias;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
