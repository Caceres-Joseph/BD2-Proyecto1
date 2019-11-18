/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Chequera;

/**
 *
 * @author ricar
 */
public class Chequera {
    private int id_chequera;
    private int no_cuenta;
    private int estado_chequera;
    
    public Chequera(int id_chequera, int no_cuenta, int estado_chequera)
    {
        this.id_chequera = id_chequera;
        this.no_cuenta = no_cuenta;
        this.estado_chequera = estado_chequera;
    }

    public Chequera(int no_cuenta, int estado_chequera) {
        this.no_cuenta = no_cuenta;
        this.estado_chequera = estado_chequera;
        this.id_chequera=0;
    }
    
    

    public int getId_chequera() {
        return id_chequera;
    }

    public void setId_chequera(int id_chequera) {
        this.id_chequera = id_chequera;
    }

    public int getNo_cuenta() {
        return no_cuenta;
    }

    public void setNo_cuenta(int no_cuenta) {
        this.no_cuenta = no_cuenta;
    }

    public int getEstado_chequera() {
        return estado_chequera;
    }

    public void setEstado_chequera(int estado_chequera) {
        this.estado_chequera = estado_chequera;
    }
    
    
    public void imprimir(){
        System.out.println("-- Id Chequera---");
        System.out.println(id_chequera);
        System.out.println("-- Estado chequera --");
        System.out.println(estado_chequera);
        System.out.println("-- No cuenta --");
        System.out.println(no_cuenta);
    }
    
}
