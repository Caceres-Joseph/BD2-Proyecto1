/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ReConciliacion.ChequeConciliado;
import Model.ReConciliacion.ConsultasConciliacion;
import Model.ReConciliacion.Lote;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class RecibirConciliacionController {

    
    ConsultasConciliacion consulta;
    
    public RecibirConciliacionController(){
        consulta = new ConsultasConciliacion();
    }
    
    /**
     * Lectura del archivo de conciliación para realizar.
     * @param path 
     */
    public void LeerArchivo(String path) {

        Lote lote = getDataLote(path);
        consulta.saveLote(lote);
        
        try {
            Scanner input = new Scanner(new File(path));
            
            while (input.hasNextLine()) {
                
                String line = input.nextLine();
                
                ChequeConciliado cheque = getDataCheque(line, lote.getId_lote());
                
                consulta.saveCheque(cheque);
                
            }
            input.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        //Verificación del lote
        consulta.verificarLote(lote.getId_lote());
    }
    
    
   
    /**
     * Función utilizada para crear un nuevo lote que será almacenado en 
     * la tabla temporal lote, para luego almacenar los cheques que pertenezcan
     * al lote y realizar las compensaciones si todo cuadra.
     *
     * @param path
     * @return retorna un nuevo lote para la tabla temporal.
     */
    public Lote getDataLote(String path) {
        
        String[] nameFile = path.replace(".txt", "").split("/");

        String nombreArchivo = nameFile[nameFile.length];

        String[] infoLote = nombreArchivo.split("_");

        int banco = Integer.parseInt(infoLote[1]);

        int id_lote = Integer.parseInt(infoLote[2]);

        int docs = Integer.parseInt(infoLote[3]);

        double total = Double.parseDouble(infoLote[4]);
        
        return new Lote(id_lote,banco,docs,total,1);
    }
    
    
    /**
     * Método encargado de crear un nuevo objeto cheque que será insertado
     * en la tabla temporal de cheques que se reciben para compensación.
     * @param line
     * @param lote
     * @return retorna un nuevo cheque para guardar en la tabla temporal.
     */
    
    public ChequeConciliado getDataCheque(String line, int lote){

        String[]  dataCheque = line.split("|");
        
        int referencia = Integer.parseInt(dataCheque[1]);
        int cuenta = Integer.parseInt(dataCheque[2]);
        int id_cheque = Integer.parseInt(dataCheque[3]);
        double monto = Double.parseDouble(dataCheque[4]);
        
        return new ChequeConciliado(id_cheque,cuenta,monto,lote,0,referencia);
    }
    

}
