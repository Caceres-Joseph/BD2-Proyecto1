/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Roles_Permiso;

import Model.BD.BDOpciones;
import Model.BD.ColumnaTabla;
import Model.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ricar
 */
public class ConsultasRolPermiso extends Conexion{
    
    public boolean save(RolPermiso rolpermiso)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_ROL(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
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
    
    public boolean update(RolPermiso rolpermiso)
    {
        Connection con = getConexion();
        try {
            String cmd = "{CALL INSERT_ROL(?,?)}"; //USANDO EL PROCEDIMIENTO ALMACENADO
            CallableStatement call = con.prepareCall(cmd);
            
            call.execute();
            call.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
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
    
    public ArrayList<RolPermiso> listData()
    {
        Connection con = getConexion();
        try {
            ArrayList<RolPermiso> rolesPermiso = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement ps = null;
            String sql = "SELECT ROL.NOMBRE AS ROL, PERMISO.NOMBRE AS PERMISO, ROL_PERMISO.ROL_ID_ROL, ROL_PERMISO.PERMISO_ID_PERMISO, ROL_PERMISO.ESTADO_ROL_PERMISO FROM PERMISO, ROL, ROL_PERMISO "+
                         "WHERE ROL.ID_ROL = ROL_PERMISO.ROL_ID_ROL AND ROL.ID_ROL = ?"
                        ;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                RolPermiso r = new RolPermiso(rs.getInt("rol_id_rol"), rs.getInt("permiso_id_permiso"), rs.getInt("estado_rol_permiso"));
                r.setRol(rs.getString("rol"));
                r.setPermiso(rs.getString("permiso"));
                rolesPermiso.add(r);
            }
            return rolesPermiso;
        } catch (Exception e) {
            System.err.println(e);
            return new ArrayList<>();
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
