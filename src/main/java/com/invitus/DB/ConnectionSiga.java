package com.invitus.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSiga {

    public static final String MYSQL_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String HOST_BD = "jdbc:oracle:thin:@matrixdev.cs4nwvxqswzx.us-west-2.rds.amazonaws.com:1521:MATRIDEV";
    public static final String USER_BD = "MATRIX_CALIDAD";
    public static final String PASSWORD_BD = "Us3r_c4l1D4d";

    public Connection Siga(){
        Connection conn = null;
        try {
            Class.forName(MYSQL_DRIVER);
            conn = DriverManager.getConnection(HOST_BD, USER_BD, PASSWORD_BD);
            System.out.println("conexion Siga exitosa");

        } catch (SQLException MySQL){
            System.out.println("Error conexion Siga" + MySQL.getMessage());
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }

    public void disconnect(Connection conn){
        try {
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }

}
