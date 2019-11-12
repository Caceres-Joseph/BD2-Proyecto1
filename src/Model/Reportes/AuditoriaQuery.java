/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Reportes;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class AuditoriaQuery {
    ArrayList<String> fecha;
    ArrayList<String> tipo;
    ArrayList<String> naturaleza;
    ArrayList<Integer> terminal;
    ArrayList<Integer> cuenta;

    public AuditoriaQuery() {
        this.fecha = new ArrayList<>();
        this.tipo = new ArrayList<>();
        this.naturaleza = new ArrayList<>();
        this.terminal = new ArrayList<>();
        this.cuenta = new ArrayList<>();
    }

    public ArrayList<String> getFecha() {
        return fecha;
    }

    public void setFecha(ArrayList<String> fecha) {
        this.fecha = fecha;
    }

    public ArrayList<String> getTipo() {
        return tipo;
    }

    public void setTipo(ArrayList<String> tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(ArrayList<String> naturaleza) {
        this.naturaleza = naturaleza;
    }

    public ArrayList<Integer> getTerminal() {
        return terminal;
    }

    public void setTerminal(ArrayList<Integer> terminal) {
        this.terminal = terminal;
    }

    public ArrayList<Integer> getCuenta() {
        return cuenta;
    }

    public void setCuenta(ArrayList<Integer> cuenta) {
        this.cuenta = cuenta;
    }

}
