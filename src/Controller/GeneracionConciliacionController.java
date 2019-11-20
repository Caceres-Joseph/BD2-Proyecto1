/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.B2;
import Model.ChequeTMP2.*;
import Model.LoteTMP2.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author ricar
 */
public class GeneracionConciliacionController {

    ConsultasChequeTMP2 consultas;

    public GeneracionConciliacionController() {
        this.consultas = new ConsultasChequeTMP2();
    }

    public boolean cobrar_chequeExterno(int no_cuenta_local, int no_cuenta_externa, int correlativo, double monto, int idbanco) {
        try {

            int terminal = B2.usuario.getId_terminal();
            int usuario = B2.usuario.getId_usuario();

            ChequeExterno cheque = new ChequeExterno(no_cuenta_local, no_cuenta_externa, correlativo, monto, idbanco);
            return consultas.cobra_cheque_externo(cheque, usuario, terminal);
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<LoteTMP2> listarLotes() {
        try {
            return consultas.listLotes();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    //Exportación archivo de conciliacion
    public ArrayList<ChequeTMP2> listarChequesLote(int idlote) {
        try {
            return consultas.listChequesLote(idlote);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean marcarExportado(int lote, int idbanco) {
        try {
            return consultas.marcar_lote_exportado(lote, idbanco);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean exportLote(LoteTMP2 lote) {
        try {
            JFileChooser chooser;
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Exporting Lote");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
                ArrayList<ChequeTMP2> cheques = this.listarChequesLote(lote.getId_lote_2());
                LoteExport l = new LoteExport(cheques, chooser.getSelectedFile() + "\\", lote);
                return l.exportConciliacionFile();
            }
            return false;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
