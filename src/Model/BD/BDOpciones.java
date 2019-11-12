/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BD;

import java.util.ArrayList;

/**
 *
 * @author ricar
 */
public class BDOpciones {
    
    public static enum Orden {
        DESC,
        ASC
    }
    
    public static enum LimitOp {
        NO_LIMIT,
        LIMIT
    }
    
    public static enum OperadoresLogicos
    {
        AND,
        OR,
        NAC
    }
    
    public static enum OperadorAritmeticos
    {
        GREATER,
        GREATER_EQUAL,
        LOWER,
        LOWER_EQUAL,
        EQUAL,
        NAN
    }
    
    public static String getLimit(LimitOp limite, int limit)
    {
        switch(limite)
        {
            case NO_LIMIT:
                return "";
            case LIMIT:
                return "WHERE ROWNUM = "+String.valueOf(limit)+" ";
            default:
                return "";
        }
    }
    
    
    public static String getOrder(Orden OpcOrden)
    {
        switch(OpcOrden)
        {
            case DESC:
                return "DESC";
            case ASC:
                return "ASC";
            default:
                return "DESC";
        }
    }
    
    public static String getByState(String Operador, int Estado, String nombre_estado)
    {
        String cadena = Operador + " "+nombre_estado+"="+String.valueOf(Estado)+ " ";
        return cadena;
    }
    
    public static String getFilters(ArrayList<ColumnaTabla> columnas)
    {
        String cad = "";
        for(int x = 0; x < columnas.size(); x++)
        {
            cad = cad + columnas.get(x).toString()+" ";
        }
        return cad;
    }
    
}
