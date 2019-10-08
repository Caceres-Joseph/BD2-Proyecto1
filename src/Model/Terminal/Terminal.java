/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Terminal;

/**
 *
 * @author ricar
 */
public class Terminal {
    private int id_terminal;
    private int agencia_id_agencia;
    private int usuario_id_usuario;
    private int estado_terminal;


    public Terminal(int agencia_id_agencia, int usuario_id_usuario, int estado_terminal) {
        this.agencia_id_agencia = agencia_id_agencia;
        this.usuario_id_usuario = usuario_id_usuario;
        this.estado_terminal = estado_terminal;
    }

    public int getId_terminal() {
        return id_terminal;
    }

    public void setId_terminal(int id_terminal) {
        this.id_terminal = id_terminal;
    }

    public int getAgencia_id_agencia() {
        return agencia_id_agencia;
    }

    public void setAgencia_id_agencia(int agencia_id_agencia) {
        this.agencia_id_agencia = agencia_id_agencia;
    }

    public int getUsuario_id_usuario() {
        return usuario_id_usuario;
    }

    public void setUsuario_id_usuario(int usuario_id_usuario) {
        this.usuario_id_usuario = usuario_id_usuario;
    }

    public int getEstado_terminal() {
        return estado_terminal;
    }

    public void setEstado_terminal(int estado_terminal) {
        this.estado_terminal = estado_terminal;
    }

    
    
    
}
