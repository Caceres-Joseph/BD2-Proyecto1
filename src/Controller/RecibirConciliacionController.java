/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.B2;
import Model.ReConciliacion.ChequeConciliado;
import Model.ReConciliacion.ConsultasConciliacion;
import Model.ReConciliacion.DataArchivo;
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
    public Lote LeerArchivo(String path) {

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
            B2.GuiController.mensajeConsola(ex.getMessage());
            System.out.println(ex);
        }

        //Verificación del lote
        consulta.verificarLote(lote.getId_lote());

        return consulta.findLote(lote.getId_lote());
    }

    /**
     * Retorna un objeto con la información recopilada del archivo del cual se
     * realizó la lectura.
     *
     * @return objeto con los parámetros del archivo
     */
    public DataArchivo getInfoFile() {
        return consulta.getDataArchivo();
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

        String[] nameFile = path.split("\\\\");

        String nombreArchivo = nameFile[nameFile.length - 1];

        String[] infoLote = nombreArchivo.split("_");

        int banco = Integer.parseInt(infoLote[3]);

        int id_lote = Integer.parseInt(infoLote[4]);

        int docs = Integer.parseInt(infoLote[2]);

        double total = Double.parseDouble(infoLote[1]);

        //consulta.setDataArchivo(infoLote[3], infoLote[2], infoLote[4]);
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

        String[] dataCheque = line.split("\\|");

        int referencia = Integer.parseInt(dataCheque[1]);
        int cuenta = Integer.parseInt(dataCheque[2]);
        int id_cheque = Integer.parseInt(dataCheque[3]);
        double monto = Double.parseDouble(dataCheque[4]);

        return new ChequeConciliado(id_cheque, cuenta, monto, lote, "Pendiente", referencia);
    }

    /**
     * Grabación del lote que ya ha sido cuadrado
     *
     * @param lote
     * @param usuario
     * @param terminal
     * @return
     */
    public boolean operarLote(int lote) {

        try {

            int terminal = B2.usuario.getId_terminal();
            int usuario = B2.usuario.getId_usuario();

            return consulta.grabarLote(lote, usuario, terminal);
        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
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
            cheques = consulta.listDataCheques(lote);
            String cadena_archivo = "";

            Lote loteConciliado = consulta.findLote(lote);

            if (loteConciliado != null) {
                int banco = loteConciliado.getId_banco();
                for (ChequeConciliado cheque : cheques) {

                    cadena_archivo += banco + "|" + cheque.getReferencia() + "|" + cheque.getCuenta() + "|" + cheque.getId_cheque() + "|" + cheque.getValor() + "|" + cheque.getEstado() + "\n";

                }

                escribir(cadena_archivo, loteConciliado.getId_banco(), loteConciliado.getId_lote());

                //Se cambia el estado del lote a exportado
                consulta.reportarExportacion(lote);

                return cheques;
            } else {
                return null;
            }

        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
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
            w.close();

            return true;

        } catch (IOException e) {
            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        }
    }

    /**
     * Lectura del archivo de conciliación para realizar la liberación de la
     * reserva.
     *
     * @param path
     * @param usuario
     * @param terminal
     */
    public void LeerConciliados(String path) {

        Lote lote = getDataLote(path);
        consulta.saveLote(lote);

        try {
            Scanner input = new Scanner(new File(path));

            while (input.hasNextLine()) {

                String line = input.nextLine();

                ChequeConciliado cheque = getDataChequeConciliado(line, lote.getId_lote());

                /**
                 * Es posible que se encuentre en otro estado de ser así no se
                 * liberan fondos de reserva y el cheque no se toma en cuenta.
                 */
                int terminal = B2.usuario.getId_terminal();
                int usuario = B2.usuario.getId_usuario();

                if (cheque.getEstado().equalsIgnoreCase("OK")) {
                    consulta.liberarFondos(cheque, usuario, terminal, 1);
                } else {
                    consulta.liberarFondos(cheque, usuario, terminal, 0);
                }

            }
            input.close();

        } catch (FileNotFoundException ex) {
            B2.GuiController.mensajeConsola(ex.getMessage());
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
        String estado = dataCheque[5];

        return new ChequeConciliado(id_cheque, cuenta, monto, lote, estado, referencia);
    }

    /**
     * Retorna los items listados del mas reciente al menos...
     *
     * @param idLote
     * @return
     */
    public ArrayList<ChequeConciliado> listCheques(int idLote) {
        try {
            ArrayList<ChequeConciliado> cheques = consulta.listDataCheques(idLote);
            return cheques;
        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Retorna los lotes de la tabla temporal lote.
     *
     * @return
     */
    public ArrayList<Lote> listLotes() {
        try {
            ArrayList<Lote> lotes = consulta.listlotes();
            return lotes;
        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
            return new ArrayList<>();
        }
    }

}
