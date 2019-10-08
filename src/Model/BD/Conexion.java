/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ricar
 */
public class Conexion {
    
    private final String base = "orcl"; //NOMBRE DE LA BASE DE DATOS
    private final String user = "System";  // USUARIO
    private final String password = "Informatica10"; // PASSWORD
    private final String url = "jdbc:oracle:thin:@localhost:1521";
    private Connection con = null;
    
    public Conexion()
    {
        // CONSTRUCTOR
    }
    
    public Connection getConexion()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.con = (Connection) DriverManager.getConnection(this.url+":"+base, this.user,this.password);
            return this.con;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
    
    
}
