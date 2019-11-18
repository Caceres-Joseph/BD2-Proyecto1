/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.LoteTMP2;
import Model.ChequeTMP2.*;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 *
 * @author ricar
 */
public class LoteExport {
    ArrayList<ChequeTMP2> cheques;
    String export_path;
    LoteTMP2 lote;
    
    public LoteExport(ArrayList<ChequeTMP2> cheques, String export_path, LoteTMP2 lote)
    {
        this.cheques = cheques;
        this.export_path = export_path;
        this.lote = lote;
    }
    
    public boolean exportConciliacionFile()
    {
        try {
            FileWriter fw = new FileWriter(this.export_path+generateFileName());
            String content = generateContent();
            fw.write(content);
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private String generateContent()
    {
        try {
            String content = "";
            content += "BANCO|REFERENCIA|CUENTA|NO_CHEQUE|MONTO\n";
            for(ChequeTMP2 c : this.cheques)
            {
                content += String.valueOf(this.lote.getId_banco())+"|"+String.valueOf(c.getCuenta())+"|"+String.valueOf(c.getReferencia())+"|"+String.valueOf(c.getCorrelativo())+"|"+String.valueOf(c.getValor())+"\n";            
            }
            return content;
        } catch (Exception e) {
            return "-- ERROR -- : "+e.getMessage();
        }
    }
    
    private String generateFileName()
    {
        try {
            String name = "";
            name = String.valueOf(this.lote.getTotal_monto())+"_"+String.valueOf(this.lote.getTotal_documentos())+"_"+this.lote.getBancoName()+"_"+String.valueOf(this.lote.getId_lote_2());
            return name;
        } catch (Exception e) {
            return "error.txt";
        }
    }
}
