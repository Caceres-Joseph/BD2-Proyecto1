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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Daniel
 */
public class RecibirConciliacionController {

    ConsultasConciliacion consulta;

    public RecibirConciliacionController() {
        consulta = new ConsultasConciliacion();
    }

    /**
     * Lectura del archivo de conciliación para realizar.
     *
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
     * Función utilizada para crear un nuevo lote que será almacenado en la
     * tabla temporal lote, para luego almacenar los cheques que pertenezcan al
     * lote y realizar las compensaciones si todo cuadra.
     *
     * @param path
     * @return retorna un nuevo lote para la tabla temporal.
     */
    public Lote getDataLote(String path) {

        String[] nameFile = path.replace(".txt", "").split("\\");

        String nombreArchivo = nameFile[nameFile.length-1];

        String[] infoLote = nombreArchivo.split("_");

        int banco = Integer.parseInt(infoLote[1]);

        int id_lote = Integer.parseInt(infoLote[2]);

        int docs = Integer.parseInt(infoLote[3]);

        double total = Double.parseDouble(infoLote[4]);

        return new Lote(id_lote, banco, docs, total, 1);
    }

    /**
     * Método encargado de crear un nuevo objeto cheque que será insertado en la
     * tabla temporal de cheques que se reciben para compensación.
     *
     * @param line
     * @param lote
     * @return retorna un nuevo cheque para guardar en la tabla temporal.
     */
    public ChequeConciliado getDataCheque(String line, int lote) {

        String[] dataCheque = line.split("|");

        int referencia = Integer.parseInt(dataCheque[1]);
        int cuenta = Integer.parseInt(dataCheque[2]);
        int id_cheque = Integer.parseInt(dataCheque[3]);
        double monto = Double.parseDouble(dataCheque[4]);

        return new ChequeConciliado(id_cheque, cuenta, monto, lote, 0, referencia);
    }

    /**
     * Grabación del lote que ya ha sido cuadrado
     *
     * @param lote
     * @param usuario
     * @param terminal
     * @return
     */
    public boolean operarLote(int lote, int usuario, int terminal) {

        try {
            return consulta.grabarLote(lote, usuario, terminal);
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Retorna los items listados del mas reciente al menos...
     *
     * @param lote
     * @return
     */
    public ArrayList<ChequeConciliado> exportarConciliados(int lote) {
        try {
            ArrayList<ChequeConciliado> cheques = new ArrayList<>();
            ResultSet rs = consulta.listChequesGrabados(lote);
            String cadena_archivo = "";

            Lote loteConciliado = consulta.findById(lote);

            if (loteConciliado != null) {
                int banco = loteConciliado.getId_banco();
                while (rs.next()) {

                    ChequeConciliado nuevo = new ChequeConciliado(rs.getInt("correlativo"), rs.getInt("cuenta"), rs.getDouble("valor"), rs.getInt("lote"), rs.getInt("estado"), rs.getInt("referencia"));

                    cadena_archivo += banco + "|" + nuevo.getReferencia() + "|" + nuevo.getCuenta() + "|" + nuevo.getId_cheque() + "|" + nuevo.getValor() + "|" + nuevo.getEstado()+"\n";
                    
                    cheques.add(nuevo);
                }
                
                escribir(cadena_archivo,loteConciliado.getId_banco(),loteConciliado.getId_lote());
                
                return cheques;
            } else {
                return null;
            }

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Escritura del archivo conciliado
     *
     * @param cadena_archivo
     * @param banco
     * @param lote
     * @return
     */
    public boolean escribir(String cadena_archivo, int banco, int lote) {
        File f;
        f = new File("IN_" + banco + "_" + lote + ".txt");
        try {

            FileWriter w = new FileWriter(f);

            BufferedWriter bw = new BufferedWriter(w);

            PrintWriter wr = new PrintWriter(bw);

            wr.write(cadena_archivo);

            wr.close();
            bw.close();

            return true;

        } catch (IOException e) {
            return false;
        }
    }
    
    
    /**
     * Lectura del archivo de conciliación para realizar la liberación 
     * de la reserva.
     *
     * @param path
     * @param usuario
     * @param terminal
     */
    public void LeerConciliados(String path, int usuario, int terminal) {

        Lote lote = getDataLote(path);
        consulta.saveLote(lote);

        try {
            Scanner input = new Scanner(new File(path));

            while (input.hasNextLine()) {

                String line = input.nextLine();

                ChequeConciliado cheque = getDataChequeConciliado(line, lote.getId_lote());
                
                /**
                 * Es posible que se encuentre en otro estado
                 * de ser así no se liberan fondos de reserva
                 * y el cheque no se toma en cuenta.
                 */
                if(cheque.getEstado() == 1){
                    consulta.liberarFondos(cheque,usuario,terminal);
                }
                

            }
            input.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    /**
     * Método encargado de crear un nuevo objeto cheque que será insertado en la
     * tabla temporal de cheques que se reciben para compensación.
     *
     * @param line
     * @param lote
     * @return retorna un nuevo cheque para guardar en la tabla temporal.
     */
    public ChequeConciliado getDataChequeConciliado(String line, int lote) {

        String[] dataCheque = line.split("|");

        int referencia = Integer.parseInt(dataCheque[1]);
        int cuenta = Integer.parseInt(dataCheque[2]);
        int id_cheque = Integer.parseInt(dataCheque[3]);
        double monto = Double.parseDouble(dataCheque[4]);
        int estado = Integer.parseInt(dataCheque[5]);

        return new ChequeConciliado(id_cheque, cuenta, monto, lote, estado, referencia);
    }
    
    
}
