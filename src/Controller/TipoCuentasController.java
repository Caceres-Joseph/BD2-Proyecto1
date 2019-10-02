/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TipoCuentas.TipoCuenta;
import Model.TipoCuentas.ConsultasTipoCuenta;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ricar
 */
public class TipoCuentasController {

    ConsultasTipoCuenta consultas;

    public TipoCuentasController() {
        consultas = new ConsultasTipoCuenta();
    }

    /**
     * Funcion para crear un tipo de cuenta
     *
     * @param nombre
     * @return
     */
    public boolean createTipoCuenta(String nombre) {
        try {
            return consultas.save(new TipoCuenta(nombre));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Funcion para poder actualizar la cuenta
     *
     * @param id_tipo
     * @param nombre
     * @return
     */
    public boolean updateTipoCuenta(int id_tipo, String nombre) {
        try {
            TipoCuenta t = new TipoCuenta(nombre);
            t.setId_tipo(id_tipo);
            return consultas.update(t);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Funcion que retorna una tipo de cuenta especifica
     *
     * @param id
     * @return
     */
    public TipoCuenta getTipoCuenta(int id) {
        try {
            return consultas.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Funcion que lista los tipos de cuentas
     *
     * @return
     */
    public ArrayList<TipoCuenta> listTipoCuentas() {
        try {
            ArrayList<TipoCuenta> tipos = new ArrayList<>();
            ResultSet rs = consultas.listItems();
            while (rs.next()) {
                TipoCuenta tc = new TipoCuenta(rs.getString("nombre"));
                tc.setId_tipo(rs.getInt("id_tipo"));
                tipos.add(tc);
            }
            return tipos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Funcion que lista los tipos de cuentas TEST
     *
     * @return
     */
    public ArrayList<TipoCuenta> listTipoCuentasTest() {
        try {
            ArrayList<TipoCuenta> tipos = new ArrayList<>();
            ResultSet rs = consultas.listItems();

            TipoCuenta tc = new TipoCuenta("Mancomunada");
            tc.setId_tipo(1);
            tipos.add(tc);

            return tipos;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
