/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ReConciliacion;

import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Daniel
 */
public class ConsultasConciliacion extends Conexion {

    
    /**
     * Creación de un nuevo lote en la tabla temporal.
     * @param lote
     * @return 
     */
    public boolean saveLote(Lote lote) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_LOTE_TMP(?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, lote.getId_lote());
            call.setInt(2, lote.getId_banco());
            call.setInt(3, lote.getNo_documentos());
            call.setDouble(4, lote.getValor());
            call.setInt(5, lote.getEstado());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    /**
     * Creación de un nuevo cheque para un lote específico.
     * @param lote
     * @return 
     */
    public boolean saveCheque(ChequeConciliado lote) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_CHEQUE_TMP(?,?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, lote.getId_cheque());
            call.setInt(2, lote.getLote());
            call.setInt(3, lote.getEstado());
            call.setDouble(4, lote.getValor());
            call.setInt(5, lote.getCuenta());
            call.setInt(6, lote.getReferencia());
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
    /**
     * Verificación de lote, si la cantidad de documentos y el total 
     * del monto cuadra.
     * @param lote
     * @return 
     */
    public boolean verificarLote(int lote) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL VERIFICAR_LOTE(?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, lote);
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
    
    
    /**
     * Busca un lote en especifico para determinar su estado
     * @param id
     * @return el banco que encontro
     */
    public Lote findById(int id)
    {        
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM lote_tmp_1 WHERE id_lote=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Lote lote = null;        
            if(rs.next())
            {
                lote = new Lote(rs.getInt("id_lote"),rs.getInt("banco"),rs.getInt("no_documentos"),rs.getInt("valor"),rs.getInt("estado"));
            }
            return lote;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
    /**
     * Grabar el lote que ya fue cuadrado, realizando todas las operaciones
     * que se encuentran en las tablas temporales y actualizando los estados
     * de los cheques y lotes.
     * @param lote
     * @param usuario
     * @param terminal
     * @return 
     */
    public boolean grabarLote(int lote, int usuario, int terminal) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL OPERAR_LOTE(?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, lote);
            call.setInt(1, usuario);
            call.setInt(1, terminal);
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    
        /**
     * List todos los elementos de bancos del mas reciente al menos
     * @return 
     */
    public ResultSet listChequesGrabados(int lote)
    {
        Connection con = getConexion();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM cheque_tmp_1  WHERE lote = ?";
             ps = con.prepareStatement(sql);
            ps.setInt(1, lote);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        finally
        {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    

}
