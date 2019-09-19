/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gui.Componentes;


/**
 *
 * @author joseph
 */
public class nodoVistaArbol {

    public String nombre;
    public String ruta;

    public nodoVistaArbol(String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public String getName() {
        return nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}
