/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.BD.BDOpciones;
import Model.Terminal.ConsultasTerminal;
import Model.Terminal.Terminal;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class TerminalController {
    ConsultasTerminal consultas;
    
    public TerminalController()
    {
        consultas = new ConsultasTerminal();
    }
    
    public boolean createTerminal(int id_agencia, int id_usuario)
    {
        try {
            return consultas.save(new Terminal(id_agencia, id_usuario,1));
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean updateTerminal(int id_terminal,int id_agencia, int id_usuario, int estado_terminal)
    {
        try {
            Terminal t = new Terminal(id_agencia, id_usuario, estado_terminal);
            t.setId_terminal(id_terminal);
            return consultas.update(t);
        } catch (Exception e) {
            return false;
        }
    }
    
    public ArrayList<Terminal> listTerminal(BDOpciones.Orden orden, BDOpciones.LimitOp Limite, int limite)
    {
        try {
            return consultas.listData(orden, Limite, limite);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
