/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Permisos;

/**
 *
 * @author Notebook
 */
public class JsonRoles {
    
    
    public String rolAdministrador(){
        return  "{\n"
            + "	\"roles\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"bancos\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"usuarios\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"compensacion\" : {\n"  
            + "		\"permisos\":[\n"
            + "            \"cobrar_cheque\",\n"
            + "            \"archivo_de_conciliacion\",\n"
            + "            \"lectura_del_archivo_grabador\",\n"
            + "            \"lectura_del_archivo_operador\",\n"
            + "            \"exportar_conciliacion\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"clientes\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"terminal\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"tipo_cuenta\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"cheques\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"chquera\",\n"
            + "            \"cobrar\",\n"
            + "            \"reportar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"reportes\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"reporte1\",\n"
            + "            \"reporte2\",\n"
            + "            \"reporte3\",\n"
            + "            \"reporte4\",\n"
            + "            \"reporte5\",\n"
            + "            \"auditoria\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"transacciones\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"acreditar\",\n"
            + "            \"debitar\",\n"
            + "            \"transferencia\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"cuentas\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"administrar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"agencias\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "	}\n"
            + "	\n"
            + "}";
    }
    
    
    public String rolCajero(){
        
        
        return  "{\n"
            + "	\"clientes\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"compensacion\" : {\n"  
            + "		\"permisos\":[\n"
            + "            \"cobrar_cheque\"\n" 
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"cheques\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"chquera\",\n"
            + "            \"cobrar\",\n"
            + "            \"reportar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"transacciones\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"acreditar\",\n"
            + "            \"debitar\",\n"
            + "            \"transferencia\"\n"
            + "        ]\n"
            + "    },\n"
            + "    \n"
            + "	\"cuentas\" : {\n"
            + "		\"permisos\":[\n"
            + "            \"crear\",\n"
            + "            \"modificar\",\n"
            + "            \"administrar\",\n"
            + "            \"listar\"\n"
            + "        ]\n"
            + "     }\n"
            + "     \n"
            + "}";
    }
    
    
    
    public String rolOperador(){
        
        return  "{\n" 
            + "	\"compensacion\" : {\n"  
            + "		\"permisos\":[\n"
            + "            \"archivo_de_conciliacion\",\n"
            + "            \"lectura_del_archivo_operador\",\n"
            + "            \"exportar_conciliacion\"\n"
            + "        ]\n"
            + "	}\n"
            + "	\n"
            + "}";
    }
    
    
    public String rolGrabador(){
        return  "{\n" 
            + "	\"compensacion\" : {\n"  
            + "		\"permisos\":[\n"
            + "            \"archivo_de_conciliacion\",\n"
            + "            \"lectura_del_archivo_grabador\",\n"
            + "            \"exportar_conciliacion\"\n"
            + "        ]\n"
            + "	}\n"
            + "	\n"
            + "}";
    }
    
    
    public String rolGerencia(){
        return  "{\n" 
            + "	\"reportes\" : {\n"   
            + "		\"permisos\":[\n"
            + "            \"reporte1\",\n"
            + "            \"reporte2\",\n"
            + "            \"reporte3\",\n"
            + "            \"reporte4\",\n"
            + "            \"reporte5\",\n"
            + "            \"auditoria\"\n"
            + "        ]\n"
            + "	}\n"
            + "	\n"
            + "}";
    }
    
}
