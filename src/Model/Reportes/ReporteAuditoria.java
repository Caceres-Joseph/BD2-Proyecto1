/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Reportes;

import Model.BD.Conexion;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class ReporteAuditoria extends Conexion {

    public AuditoriaQuery datosFiltros() {
        Connection con = getConexion();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");


        try {
            AuditoriaQuery filters = new AuditoriaQuery();
            PreparedStatement ps = null;
            ResultSet rs = null;

            //Consulta gráfica de saldos por agencia (Pie) 
            String sql = "select DISTINCT FECHA  from transaccion";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String fecha = rs.getString("FECHA").split(" ")[0];
                Date date = formatter.parse(fecha);
                filters.getFecha().add(formateador.format(date));
            }

            rs = null;
            sql = "select DISTINCT TIPO  from transaccion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                filters.getTipo().add(rs.getString("TIPO"));
            }

            rs = null;
            sql = "select DISTINCT NATURALEZA  from transaccion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                filters.getNaturaleza().add(rs.getString("NATURALEZA"));
            }

            sql = "select DISTINCT TERMINAL_ID_TERMINAL  from transaccion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                filters.getTerminal().add(Integer.parseInt(rs.getString("TERMINAL_ID_TERMINAL")));
            }

            rs = null;
            sql = "select DISTINCT CUENTA_NO_CUENTA  from transaccion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                filters.getCuenta().add(Integer.parseInt(rs.getString("CUENTA_NO_CUENTA")));
            }

            return filters;
        } catch (Exception e) {
            System.err.println(e);
            return new AuditoriaQuery();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public ArrayList<AuditoriaResults> consultaAuditoria(String tipo, String fecha, String naturaleza, String cuenta, String terminal) {
        Connection con = getConexion();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            ArrayList<AuditoriaResults> results = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            tipo = tipo.equals("Cualquiera")?" ":" tipo = '" + tipo + "' AND ";
            fecha = fecha.equals("Cualquiera")?" ":" fecha = '" + fecha + "' AND";
            naturaleza = naturaleza.equals("Cualquiera")?" ":" naturaleza = '" + naturaleza + "' AND";
            terminal = terminal.equals("Cualquiera")?" ":" terminal_id_terminal = " + terminal + " AND";
            cuenta = cuenta.equals("Cualquiera")?" ":" CUENTA_NO_CUENTA = " + cuenta + " AND";

            //Consulta gráfica de saldos por agencia (Pie) 
            String sql = "SELECT ID_TRANSACCION,FECHA,TIPO,NATURALEZA,CODIGO_AUTORIZACION,USUARIO_ID_USUARIO,TERMINAL_ID_TERMINAL,CUENTA_NO_CUENTA,RECHAZADO,RAZON_RECHAZO,SALDO_FINAL-SALDO_INICIAL AS MONTO FROM transaccion WHERE \n"
                    + tipo +  fecha +  naturaleza + terminal + cuenta + " ESTADO_TRANSACCION = 1";
                    
            System.out.println("LA CONSULTA ES: "+sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AuditoriaResults resultsAuditor = new AuditoriaResults();
                resultsAuditor.setId(Integer.parseInt(rs.getString("ID_TRANSACCION")));
                resultsAuditor.setFecha(rs.getString("fecha"));
                resultsAuditor.setTipo(rs.getString("tipo"));
                resultsAuditor.setNaturaleza(rs.getString("NATURALEZA"));
                resultsAuditor.setCodigo_autorizacion(Integer.parseInt(rs.getString("CODIGO_AUTORIZACION")));
                resultsAuditor.setId_usuario(Integer.parseInt(rs.getString("USUARIO_ID_USUARIO")));
                resultsAuditor.setId_terminal(Integer.parseInt(rs.getString("TERMINAL_ID_TERMINAL")));
                resultsAuditor.setNo_cuenta(Integer.parseInt(rs.getString("CUENTA_NO_CUENTA")));
                resultsAuditor.setRechazo(rs.getString("RECHAZADO"));
                resultsAuditor.setRazon_rechazo(rs.getString("RAZON_RECHAZO"));
                resultsAuditor.setMonto(Math.abs(Double.parseDouble(rs.getString("MONTO"))));
                results.add(resultsAuditor);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e);
            return new ArrayList<>();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
