package com.invitus.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDInvictus {
    private static String dbUrl = "jdbc:mysql://invictus-qa-db-instance-1.cirneqqob4fl.us-east-1.rds.amazonaws.com:3306/payments";
    private static String dbUsername = "paymentsDatabaseAdmin";
    private static String dbPassword = "Ma04YabwCBvgCCb8Ci8Tlw,zl04gfF";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
        return null;
    }

    public static void disconnect(Connection conn){
        try {
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
}

