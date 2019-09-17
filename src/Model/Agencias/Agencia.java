/*
 * Clase encargada del manejo de los atributos de las agencias
 */
package Model.Agencias;

/**
 *
 * @author Ricardo Antonio Cutz Hernandez
 */
public class Agencia {
    private int id_agencia;
    private String nombre;
    private String direccion;
    private int id_banco;
    
    /**
     * Constructor de la clase
     * @param nombre
     * @param direccion
     * @param id_banco 
     */
    public Agencia(String nombre, String direccion, int id_banco)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.id_banco = id_banco;
    }
    
    /**
     * Getter de Nombre
     * @return String del nombre del Banco
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Setter del Nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Getter de la direccion
     * @return String con la direccion del banco
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Setter de la direccion
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Getter del id del banco al que pertenece la agencia
     * @return Int con el ID del banco
     */
    public int getId_banco() {
        return id_banco;
    }
    
    /**
     * Setter del id del Banco
     * @param id_banco 
     */
    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }
    
    /**
     * Getter de el ID de la agencia
     * @return entero que representa el id de agencia
     */
    public int getId_agencia() {
        return id_agencia;
    }
    
    /**
     * Setter del id de la agencia
     * @param id_agencia 
     */
    public void setId_agencia(int id_agencia) {
        this.id_agencia = id_agencia;
    }
    
    
}
