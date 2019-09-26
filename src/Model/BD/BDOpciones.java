/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BD;

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
    
    
}
