/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ricar
 */
public class TransaccionController {
    
    
    public boolean Acreditar(int no_cuenta, double monto)
    {
        try {
            return false;
        } catch (Exception e) {
        }
        return false;
    }
    
    
    public boolean Debitar(int no_cuenta, double monto)
    {
        try {
            return true;
        } catch (Exception e) {
            
        }
        return true;
    }
    
    public boolean Transferencia(int no_cuenta_origen, int no_cuenta_destino, double monto)
    {
        try {
            return false;
        } catch (Exception e) {
        }
        return true;
    }
}
