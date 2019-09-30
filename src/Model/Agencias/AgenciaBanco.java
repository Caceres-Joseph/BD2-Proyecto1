/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Agencias;

/**
 *
 * @author Notebook
 */
public class AgenciaBanco extends Agencia{
    
    private String nombre_banco;
    
    
    /**
     * Constructor de la clase
     * @param nombre
     * @param direccion
     * @param id_banco 
     * @param nombre_banco 
     */
    public AgenciaBanco(String nombre, String direccion, int id_banco, String nombre_banco) {
        super(nombre, direccion, id_banco);
        this.nombre_banco = nombre_banco;
    }
    
    
    /**
     * Getter de Nombre
     * @return String del nombre del Banco
     */
    public String getNombre_banco() {
        return nombre_banco;
    }
    
    /**
     * Setter del Nombre
     * @param nombre 
     */
    public void setNombre_banco(String nombre) {
        this.nombre_banco = nombre;
    }
    
    
}
