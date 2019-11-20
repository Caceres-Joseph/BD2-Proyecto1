/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ChequeTMP2;

/**
 *
 * @author ricar
 */
public class ChequeExterno {
    private int no_cuenta_local;
    private int no_cuenta_externa;
    private int correlativo_cheque;
    private double monto;
    private int id_banco;
    
    public ChequeExterno(int no_cuenta_local, int no_cuenta_externa, int correlativo, double monto, int id_banco)
    {
        this.no_cuenta_local = no_cuenta_local;
        this.no_cuenta_externa = no_cuenta_externa;
        this.correlativo_cheque = correlativo;
        this.monto = monto;
        this.id_banco = id_banco;
        
    }

    public int getNo_cuenta_local() {
        return no_cuenta_local;
    }

    public void setNo_cuenta_local(int no_cuenta_local) {
        this.no_cuenta_local = no_cuenta_local;
    }

    public int getNo_cuenta_externa() {
        return no_cuenta_externa;
    }

    public void setNo_cuenta_externa(int no_cuenta_externa) {
        this.no_cuenta_externa = no_cuenta_externa;
    }

    public int getCorrelativo_cheque() {
        return correlativo_cheque;
    }

    public void setCorrelativo_cheque(int correlativo_cheque) {
        this.correlativo_cheque = correlativo_cheque;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }
    
    
    
    public void imprimir(){
        System.out.println("No cuenta local:");
        System.out.println(no_cuenta_local);
        System.out.println("No cuenta externa: ");
        System.out.println(no_cuenta_externa);
        System.out.println("No correlativo cheuqe:");
        System.out.println(correlativo_cheque);
        System.out.println("Monto: ");
        System.out.println(monto);
        System.out.println("Id banco:");
        System.out.println(id_banco);
        
         
    }
    
}
