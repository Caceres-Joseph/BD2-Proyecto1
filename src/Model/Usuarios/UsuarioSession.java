/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Usuarios;

/**
 *
 * @author ricar
 */
public class UsuarioSession {
    private int id_usuario;
    private String usuario;
    private int id_terminal;
    private int id_agencia;
    private int id_banco;
    private String agencia;
    private String banco;
    
    
    public UsuarioSession(int id_usuario, String usuario, int id_terminal,
                int id_agencia, int id_banco, String agencia, String banco
            )
    {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.id_terminal = id_terminal;
        this.id_agencia = id_agencia;
        this.id_banco = id_banco;
        this.agencia = agencia;
        this.banco = banco;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId_terminal() {
        return id_terminal;
    }

    public void setId_terminal(int id_terminal) {
        this.id_terminal = id_terminal;
    }

    public int getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(int id_agencia) {
        this.id_agencia = id_agencia;
    }

    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    
    
}
