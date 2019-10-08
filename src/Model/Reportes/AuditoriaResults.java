/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Reportes;

/**
 *
 * @author Daniel
 */
public class AuditoriaResults {

    private int id;
    private String fecha;
    private String tipo;
    private String naturaleza;
    private Double monto;
    private int codigo_autorizacion;
    private String rechazo;
    private String razon_rechazo;
    private int id_usuario;
    private int id_terminal;
    private int no_cuenta;

    public AuditoriaResults() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getCodigo_autorizacion() {
        return codigo_autorizacion;
    }

    public void setCodigo_autorizacion(int codigo_autorizacion) {
        this.codigo_autorizacion = codigo_autorizacion;
    }

    public String getRechazo() {
        return rechazo;
    }

    public void setRechazo(String rechazo) {
        this.rechazo = rechazo;
    }

    public String getRazon_rechazo() {
        return razon_rechazo;
    }

    public void setRazon_rechazo(String razon_rechazo) {
        this.razon_rechazo = razon_rechazo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_terminal() {
        return id_terminal;
    }

    public void setId_terminal(int id_terminal) {
        this.id_terminal = id_terminal;
    }

    public int getNo_cuenta() {
        return no_cuenta;
    }

    public void setNo_cuenta(int no_cuenta) {
        this.no_cuenta = no_cuenta;
    }

}
