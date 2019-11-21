/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Reportes;

import Model.BD.BDOpciones;
import Model.BD.Conexion;
import Model.Bancos.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class ReporteGerencia extends Conexion {

    public ArrayList<GerenciaQuery1> saldosAgencia() {
        Connection con = getConexion();
        try {
            ArrayList<GerenciaQuery1> results = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;

            //Consulta gráfica de saldos por agencia (Pie) 
            String sql = "SELECT agencia.id_agencia,agencia.nombre,SUM(SALDO_FINAL - SALDO_INICIAL) AS Saldos FROM TRANSACCION,TERMINAL,AGENCIA WHERE transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia GROUP BY agencia.id_agencia,agencia.nombre";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GerenciaQuery1 reporte1 = new GerenciaQuery1(Integer.parseInt(rs.getString("id_agencia")), rs.getString("nombre"), Math.abs(Double.parseDouble(rs.getString("Saldos"))));
                results.add(reporte1);
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

    public ArrayList<GerenciaQuery2> depositosAgencia() {
        Connection con = getConexion();
        try {
            ArrayList<GerenciaQuery2> results = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;

            //Consulta gráfica de saldos por agencia (Pie) 
            String sql = "--CONSULTA CANTIDAD MÁXIMA DE DEPOSITOS HECHOS POR AGENCIA\n"
                    + "SELECT agencia, nombre, cantidad FROM(\n"
                    + "    --DETERMINAR EL MÁXIMO DE DEPOSITOS REALIZADOS EN CADA AGENCIA\n"
                    + "    SELECT agencia, MAX(CONTEO) AS CANTIDAD FROM(\n"
                    + "        SELECT transaccion.cuenta_no_cuenta AS no_cuenta,transaccion.naturaleza AS naturaleza,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,agencia.nombre AS agencia, COUNT(*) AS CONTEO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA\n"
                    + "        WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.naturaleza = 'credito' \n"
                    + "        AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia\n"
                    + "        GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre\n"
                    + "    )\n"
                    + "    GROUP BY naturaleza,agencia),\n"
                    + "    --DETERMINAR EL CONTEO DE DEPOSITOS POR TODOS LOS CLIENTES\n"
                    + "    (SELECT transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre NOMBRE_AGENCIA, COUNT(*) AS CONTEO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA\n"
                    + "    WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.naturaleza = 'credito' \n"
                    + "    AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia\n"
                    + "    GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre) \n"
                    + "    --BUSCAR EL CLIENTE QUE HIZO CADA DEPOSITO MÁXIMO\n"
                    + "WHERE agencia = NOMBRE_AGENCIA AND cantidad = conteo";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GerenciaQuery2 reporte2 = new GerenciaQuery2(rs.getString("agencia"), rs.getString("nombre"), Integer.parseInt(rs.getString("cantidad")));
                results.add(reporte2);
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

    public ArrayList<GerenciaQuery3> pagoCheques() {
        Connection con = getConexion();
        try {
            ArrayList<GerenciaQuery3> results = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;

            //Consulta gráfica de saldos por agencia (Pie) 
            String sql = "SELECT agencia,nombre, maximo FROM(\n"
                    + "    SELECT agencia, MAX(PAGADO) AS MAXIMO FROM(\n"
                    + "        SELECT transaccion.cuenta_no_cuenta AS no_cuenta,transaccion.naturaleza AS naturaleza,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,agencia.nombre AS agencia, SUM(ABS(SALDO_FINAL - SALDO_INICIAL)) AS PAGADO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA\n"
                    + "        WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.tipo = 'cheque' \n"
                    + "        AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia\n"
                    + "        GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre\n"
                    + "    )    \n"
                    + "    GROUP BY agencia),\n"
                    + "    \n"
                    + "    (SELECT transaccion.cuenta_no_cuenta AS no_cuenta,transaccion.naturaleza AS naturaleza,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,agencia.nombre AS NOMBRE_AGENCIA, SUM(ABS(SALDO_FINAL - SALDO_INICIAL)) AS PAGADO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA\n"
                    + "    WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.tipo = 'cheque' \n"
                    + "    AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia\n"
                    + "    GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre)\n"
                    + "WHERE agencia = NOMBRE_AGENCIA AND maximo = pagado";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GerenciaQuery3 reporte3 = new GerenciaQuery3(rs.getString("agencia"), rs.getString("nombre"), Double.parseDouble(rs.getString("maximo")));
                results.add(reporte3);
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

    public ArrayList<GerenciaQuery4> nuncaDepositan(String agencia) {
        Connection con = getConexion();
        try {
            ArrayList<GerenciaQuery4> results = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;

            //Consulta gráfica de saldos por agencia (Pie) 
            String sql = "SELECT dpi_cliente,nombre AS dpi FROM cliente\n"
                    + "MINUS\n"
                    + "    SELECT dpi,nombre FROM(\n"
                    + "    SELECT transaccion.cuenta_no_cuenta AS no_cuenta,cliente.dpi_cliente AS dpi,cliente.nombre AS nombre,COUNT(*) AS CONTEO FROM TRANSACCION,CUENTA,TERMINAL,AGENCIA,CLIENTE,MANCOMUNADA\n"
                    + "    WHERE transaccion.cuenta_no_cuenta = cuenta.no_cuenta AND transaccion.naturaleza = 'credito' AND agencia.id_agencia = "+agencia+" \n"
                    + "    AND mancomunada.cuenta_no_cuenta = cuenta.no_cuenta AND mancomunada.cliente_dpi_cliente = cliente.dpi_cliente AND transaccion.terminal_id_terminal = terminal.id_terminal AND terminal.agencia_id_agencia = agencia.id_agencia\n"
                    + "    GROUP BY transaccion.cuenta_no_cuenta,transaccion.naturaleza,cliente.dpi_cliente,cliente.nombre,agencia.nombre\n"
                    + "    )";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GerenciaQuery4 reporte4 = new GerenciaQuery4(Integer.parseInt(rs.getString("dpi_cliente")), rs.getString("dpi"));
                results.add(reporte4);
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
    
    public ArrayList<GerenciaQuery5> saldosGenerales() {
        Connection con = getConexion();
        try {
            ArrayList<GerenciaQuery5> results = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;

            //Consulta gráfica de saldos por agencia (Pie) 
            String sql = "SELECT cuenta.no_cuenta,cuenta.saldo,cliente.nombre FROM CUENTA,CLIENTE, MANCOMUNADA\n" +
                         "WHERE cuenta.no_cuenta = mancomunada.cuenta_no_cuenta AND cliente.dpi_cliente = mancomunada.cliente_dpi_cliente";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GerenciaQuery5 reporte5 = new GerenciaQuery5(Integer.parseInt(rs.getString("NO_CUENTA")), rs.getString("nombre"), Double.parseDouble(rs.getString("saldo")));
                results.add(reporte5);
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
