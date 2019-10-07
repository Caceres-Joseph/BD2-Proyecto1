/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cheque;

/**
 *
 * @author ricar
 */
public class Cheque {
    private int id_cheque;
    private int id_chequera;
    private int estado_cheque;
    
    public Cheque(int id_cheque, int id_chequera, int estado_cheque)
    {
        this.id_cheque = id_cheque;
        this.id_chequera = id_chequera;
        this.estado_cheque = estado_cheque;
    }

    public int getId_cheque() {
        return id_cheque;
    }

    public void setId_cheque(int id_cheque) {
        this.id_cheque = id_cheque;
    }

    public int getId_chequera() {
        return id_chequera;
    }

    public void setId_chequera(int id_chequera) {
        this.id_chequera = id_chequera;
    }

    public int getEstado_cheque() {
        return estado_cheque;
    }

    public void setEstado_cheque(int estado_cheque) {
        this.estado_cheque = estado_cheque;
    }
    
    
}
