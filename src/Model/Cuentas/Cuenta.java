    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cuentas;

/**
 *
 * @author ricar
 */
public class Cuenta {
    private int no_cuenta;
    private double saldo;
    private int banco_id_banco;
    private int tipo_cuenta_id_tipo;
    private int estado_cuenta;
    
    public Cuenta(int no_cuenta, int estado_cuenta){
        this.no_cuenta=no_cuenta;
        this.estado_cuenta= estado_cuenta;
        this.saldo=0;
        this.banco_id_banco=0;
        this.tipo_cuenta_id_tipo=0;
    }
    
    public Cuenta(double saldo, int banco_id_banco, int tipo_cuenta_id_tipo)
    {
        this.saldo = saldo;
        this.banco_id_banco = banco_id_banco;
        this.tipo_cuenta_id_tipo = tipo_cuenta_id_tipo;
        this.estado_cuenta = 1;
    }

    public int getNo_cuenta() {
        return no_cuenta;
    }

    public void setNo_cuenta(int no_cuenta) {
        this.no_cuenta = no_cuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getBanco_id_banco() {
        return banco_id_banco;
    }

    public void setBanco_id_banco(int banco_id_banco) {
        this.banco_id_banco = banco_id_banco;
    }

    public int getTipo_cuenta_id_tipo() {
        return tipo_cuenta_id_tipo;
    }

    public void setTipo_cuenta_id_tipo(int tipo_cuenta_id_tipo) {
        this.tipo_cuenta_id_tipo = tipo_cuenta_id_tipo;
    }

    public int getEstado_cuenta() {
        return estado_cuenta;
    }

    public void setEstado_cuenta(int estado_cuenta) {
        this.estado_cuenta = estado_cuenta;
    }
    
    
    public void imprimir(){
        System.out.println("-- Cuenta---");
        System.out.println("No. "+String.valueOf(no_cuenta));
        System.out.println("Saldo "+String.valueOf(saldo));
        System.out.println("IdBanco. "+String.valueOf(banco_id_banco));
        System.out.println("TipoCuenta. "+String.valueOf(tipo_cuenta_id_tipo));
        System.out.println("Estado. "+String.valueOf(estado_cuenta));
        
         
    
    
    }
    
}
