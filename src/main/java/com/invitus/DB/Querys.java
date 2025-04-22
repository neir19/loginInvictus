package com.invitus.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Querys {

    static String  numberDocument = "1127578047";
    static ConnectionSiga conn = new ConnectionSiga();

    public static int queryIntentosFallidosLogin() throws SQLException {
        int resultName = 0;
        Connection con = conn.Siga();
        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery("select num_intentos_fallidos from GANA_SEGURIDAD.usuario WHERE login = '1127578047'");
        while (rset.next()) {
            resultName = Integer.parseInt(rset.getNString(1));
            System.out.println(resultName + "=intentos Fallidos");
        }
        stmt.close();

        return resultName; 
    }

    public static void updateIntentosFallidosLogin() throws SQLException {

        Connection con = conn.Siga();
        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery("UPDATE GANA_SEGURIDAD.usuario SET num_intentos_fallidos = '0' WHERE login ="  + numberDocument);
        stmt.close();
    }




}
