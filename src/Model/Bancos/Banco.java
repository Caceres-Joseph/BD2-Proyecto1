/*
 * Clase Encaragada del manejo de los atributos de los bancos
 */
package Model.Bancos;

/**
 *
 * @author Ricardo Antonio Cutz Hernandez
 */
public class Banco {
    private String nombre;
    private int id_banco;
    private int estado_banco;
    
    /**
     * Constructor de la clase
     * @param nombre 
     */
    public Banco(String nombre)
    {
        this.nombre = nombre;
        this.estado_banco = 1;
    }
    
    /**
     * Getter del nombre del banco
     * @return String que representa el nombre del banco
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter del nombre del banco
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Getter del id del banco
     * @return Int que representa el id del banco
     */
    public int getId_banco() {
        return id_banco;
    }
    
    /**
     * Setter del id_Banco
     * @param id_banco 
     */
    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public int getEstado_banco() {
        return estado_banco;
    }

    public void setEstado_banco(int estado_banco) {
        this.estado_banco = estado_banco;
    }
    
    
    
}
