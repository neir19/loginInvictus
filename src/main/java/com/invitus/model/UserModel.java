package com.invitus.model;

public class UserModel {

    private static String USER;
    private static String PASSWORD;
    private static String EQUIMPENT;



    public UserModel() {

        //Por favor cada persona modificarlo a sus datos

        USER= "3012553637";
        PASSWORD ="Gana2024.";
        EQUIMPENT="25";

    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getEQUIMPENT() {
        return EQUIMPENT;
    }
}
