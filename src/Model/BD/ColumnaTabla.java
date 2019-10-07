/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BD;

import Model.BD.BDOpciones;

/**
 *
 * @author ricar
 */
public class ColumnaTabla {
    
    private BDOpciones.OperadoresLogicos oplog;
    private String columName;
    private String value;
    private BDOpciones.OperadorAritmeticos opart;
    
    public ColumnaTabla(BDOpciones.OperadoresLogicos op, String columna, String value
        , BDOpciones.OperadorAritmeticos opart
            )
    {
        this.oplog = op;
        this.columName = columna;
        this.value = value;
        this.opart = opart;
    }

    public BDOpciones.OperadoresLogicos getOplog() {
        return oplog;
    }

    public void setOplog(BDOpciones.OperadoresLogicos oplog) {
        this.oplog = oplog;
    }

    public String getColumName() {
        return columName;
    }

    public void setColumName(String columName) {
        this.columName = columName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BDOpciones.OperadorAritmeticos getOpart() {
        return opart;
    }

    public void setOpart(BDOpciones.OperadorAritmeticos opart) {
        this.opart = opart;
    }
    
    @Override
    public String toString()
    {
        return OperadorLog()+" "+this.columName+""+OperadorAritmetico()+""+this.value;
    }
    
    private String OperadorLog()
    {
        if(this.oplog == BDOpciones.OperadoresLogicos.AND)
        {
            return " AND ";
        }
        if(this.oplog == BDOpciones.OperadoresLogicos.OR)
        {
            return " OR ";
        }
        if(this.oplog == BDOpciones.OperadoresLogicos.NAC)
        {
            return "";
        }
        return "";
    }
    
    private String OperadorAritmetico()
    {
        if(this.opart == BDOpciones.OperadorAritmeticos.EQUAL)
        {
            return "=";
        }
        if(this.opart == BDOpciones.OperadorAritmeticos.GREATER)
        {
            return ">";
        }
        if(this.opart == BDOpciones.OperadorAritmeticos.GREATER_EQUAL)
        {
            return ">=";
        }
        if(this.opart == BDOpciones.OperadorAritmeticos.LOWER)
        {
            return "<";
        }
        if(this.opart == BDOpciones.OperadorAritmeticos.LOWER_EQUAL)
        {
            return "<=";
        }
        if(this.opart == BDOpciones.OperadorAritmeticos.NAN)
        {
            return "";
        }
        return "";
    }
}
