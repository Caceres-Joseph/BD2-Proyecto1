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
public class CuentaBancoTipo{
    
    private int dpi_cliente;
    private String nombre;
    private String apellido;
    private int no_cuenta;
    private double saldo_cuenta;
    private String banco;
    private String tipo;
    private int estado_cuenta;
    
    public CuentaBancoTipo(int dpi_cliente, String nombre, String apellido, int no_cuenta,
            double saldo, String banco, String tipo, int estado_cuenta
        )
    {
        this.dpi_cliente = dpi_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.no_cuenta = no_cuenta;
        this.saldo_cuenta = saldo;
        this.banco = banco;
        this.tipo = tipo;
        this.estado_cuenta =estado_cuenta;
    }

    public int getDpi_cliente() {
        return dpi_cliente;
    }

    public void setDpi_cliente(int dpi_cliente) {
        this.dpi_cliente = dpi_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNo_cuenta() {
        return no_cuenta;
    }

    public void setNo_cuenta(int no_cuenta) {
        this.no_cuenta = no_cuenta;
    }

    public double getSaldo_cuenta() {
        return saldo_cuenta;
    }

    public void setSaldo_cuenta(double saldo_cuenta) {
        this.saldo_cuenta = saldo_cuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado_cuenta() {
        return estado_cuenta;
    }

    public void setEstado_cuenta(int estado_cuenta) {
        this.estado_cuenta = estado_cuenta;
    }
    
    
}