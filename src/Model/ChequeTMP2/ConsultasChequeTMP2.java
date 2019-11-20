/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.ChequeTMP2;

import Main.B2;
import Model.BD.BDOpciones;
import Model.BD.Conexion;
import Model.LoteTMP2.LoteTMP2;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ricar
 */
public class ConsultasChequeTMP2 extends Conexion {

    public boolean cobra_cheque_externo(ChequeExterno cheque, int id_usuario, int terminal) {
        Connection con = getConexion();
        try {
            cheque.imprimir();
            System.out.println(id_usuario);
            System.out.println(terminal);
            
            String cmd = "{CALL CHEQUE_EXTERNO(?,?,?,?,?,?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, cheque.getNo_cuenta_local());
            call.setInt(2, cheque.getNo_cuenta_externa());
            call.setInt(3, cheque.getCorrelativo_cheque());
            call.setDouble(4, cheque.getMonto());
            call.setInt(5, cheque.getId_banco());
            call.setInt(6, id_usuario);
            call.setInt(7, terminal);
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
                B2.GuiController.mensajeConsola(e.getMessage());
            }
        }
    }

    public ArrayList<LoteTMP2> listLotes() {
        Connection con = getConexion();
        try {
            ArrayList<LoteTMP2> lotes = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT LOTE_TMP_2.ID_LOTE_2, LOTE_TMP_2.TOTAL_DOCUMENTOS, LOTE_TMP_2.TOTAL_MONTO, LOTE_TMP_2.ID_BANCO, LOTE_TMP_2.ESTADO_LOTE, "
                    + "BANCO.NOMBRE AS BANCO "
                    + "FROM BANCO, LOTE_TMP_2 "
                    + "WHERE BANCO.ID_BANCO = LOTE_TMP_2.ID_BANCO "
                    + "ORDER BY LOTE_TMP_2.ID_LOTE_2 DESC ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoteTMP2 l = new LoteTMP2(rs.getInt("total_documentos"), rs.getDouble("total_monto"), rs.getInt("id_banco"), rs.getInt("estado_lote"));
                l.setBancoName(rs.getString("banco"));
                l.setId_lote_2(rs.getInt("id_lote_2"));
                lotes.add(l);
            }
            return lotes;
        } catch (Exception e) {
            System.err.println(e);
            B2.GuiController.mensajeConsola(e.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                B2.GuiController.mensajeConsola(e.getMessage());
                System.err.println(e);
            }
        }
    }

    public ArrayList<ChequeTMP2> listChequesLote(int idlote) {
        Connection con = getConexion();
        try {
            ArrayList<ChequeTMP2> cheques = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM CHEQUE_TMP_2 WHERE LOTE = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idlote);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChequeTMP2 cheque = new ChequeTMP2(rs.getDate("fecha"), rs.getInt("cuenta"), rs.getDouble("valor"), rs.getInt("lote"), rs.getInt("referencia"), rs.getInt("correlativo"));
                cheque.setId_cheque(rs.getInt("id_cheque"));
                cheque.setEstado_cheque(rs.getString("estado_cheque"));
                cheques.add(cheque);
            }
            return cheques;
        } catch (Exception e) {
            B2.GuiController.mensajeConsola(e.getMessage());
            System.err.println(e);
            return new ArrayList<>();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                B2.GuiController.mensajeConsola(e.getMessage());
                System.err.println(e);
            }
        }
    }

    public boolean marcar_lote_exportado(int id_lote, int id_banco) {
        Connection con = getConexion();
        try {
            String cmd = "{CALL MARCAR_LOTE_EXPORTADO(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            call.setInt(1, id_banco);
            call.setInt(2, id_lote);
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            B2.GuiController.mensajeConsola(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                B2.GuiController.mensajeConsola(e.getMessage());
                System.err.println(e);
            }
        }
    }
}
