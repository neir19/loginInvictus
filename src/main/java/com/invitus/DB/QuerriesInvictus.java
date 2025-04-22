package com.invitus.DB;



import com.invitus.model.UserModel;
import net.serenitybdd.screenplay.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

public class QuerriesInvictus {


    public static List<List<String>> querrylastinsert(String product) throws SQLException {

        Connection con = null;
        Statement stmt = null;

        ResultSet rset = null;
        String id_user = null;
        String id_product = null;
        String id_chance = null;
        String number_chance = null;
        String id_lotery = null;
        String lotery = null;
        List<List<String>> lista = new ArrayList<>();
        List<String> id_loterias = new ArrayList<>();
        Date fechaSorteo = null;

        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }

        try {

            rset = stmt.executeQuery("SELECT id_user FROM hierarchies.USERS where phone=" + UserModel.getUSER());
            if (rset.next()) {
                System.out.println("SI TIENE elementos");
                id_user = rset.getString("id_user");
                System.out.println("el usuario es " + id_user);
            } else {
                System.out.println("NO tiene elementos");
            }
        } catch (Exception e) {
            System.out.println("Ocurio un error");
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
        try {
            System.out.println("entre al segundo try");
            rset = stmt.executeQuery("SELECT id_product FROM products.PRODUCTS WHERE name= " + "'" + product + "'");
            if (rset.next()) {
                id_product = "" + rset.getString("id_product");
                System.out.println("el producto es " + id_product);
            } else {
                System.out.println("NO tiene elementos");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            System.out.println("entre al tercer try");
            rset = stmt.executeQuery("SELECT id_chance,drawing_date  FROM lotteriesgames.CHANCES WHERE id_product=" + id_product + " and seller_id =" + "'" + id_user + "'" + " and status= 1 order by id_chance  DESC limit 1;");
            if (rset.next()) {
                id_chance = rset.getString("id_chance");
                fechaSorteo = rset.getDate("drawing_date");
                System.out.println("el id chance " + id_chance + " y la fehca " + fechaSorteo + "fecha de hoy " + now());
            } else {
                System.out.println("NO tiene elementos");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

         /*
            try{
                System.out.println("SELECT LC.id_chance FROM  hierarchies.USERS UC  INNER JOIN lotteriesgames.CHANCES LC on LC.seller_id=UC.id_user INNER JOIN products.PRODUCTS PP on LC.id_product= PP.id_product WHERE UC.phone='"+login.getUSER()+"' and  PP.name='"+product+"'  and LC.status =1 ORDER BY LC.id_chance DESC LIMIT  1;");
                rset=stmt.executeQuery("SELECT LC.id_chance FROM  hierarchies.USERS UC  INNER JOIN lotteriesgames.CHANCES LC on LC.seller_id=UC.id_user INNER JOIN products.PRODUCTS PP on LC.id_product= PP.id_product WHERE UC.phone='"+login.getUSER()+"' and  PP.name='"+product+"'  and LC.status =1 ORDER BY LC.id_chance DESC LIMIT  1;");
                if(rset.next()) {
                    id_chance=rset.getString(id_chance);
                    System.out.println("el id chanc es"+id_chance);
                }
            }catch (Exception e){
                System.out.println("error"+e);
           }


         */


        try {
            System.out.println("entre al cuarto try");
            rset = stmt.executeQuery("SELECT id_lottery FROM lotteriesgames.CHANCES_LOTTERIES WHERE id_chance=" + id_chance);
            while (rset.next()) {
                id_lotery = rset.getString("id_lottery");
                id_loterias.add(id_lotery);

                System.out.println("el id lotery es " + id_lotery);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            ArrayList<String> loterias = new ArrayList<>();
            Date fechahoy = Date.valueOf(now());
            if (fechaSorteo.after(fechahoy) || fechaSorteo.equals(fechahoy)) {
                for (String idLot : id_loterias) {
                    rset = stmt.executeQuery("SELECT name  FROM lotteriesgames.LOTTERIES WHERE id_lottery in(" + idLot + ")");
                    if (rset.next()) {
                        lotery = rset.getString("name");
                        System.out.println("el producto es " + lotery);
                        loterias.add(lotery);
                    }
                }
                lista.add(loterias);
            } else {
                System.out.println("No entré a las loterias");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            System.out.println("entre al sexto try");
            rset = stmt.executeQuery("SELECT AES_DECRYPT(number,'N3X0MYSQL2021*') FROM lotteriesgames.CHANCES_DETAIL WHERE id_chance=" + id_chance);
            ArrayList<String> numeros = new ArrayList<>();
            while (rset.next()) {
                number_chance = rset.getString(1);
                number_chance = number_chance.replaceAll("[*]", "");
                System.out.println("el numero es " + number_chance);
                numeros.add(number_chance);
            }
            lista.add(numeros);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        BDInvictus.disconnect(con);
        return lista;


    }

    public static int range() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;


        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }
        try {
            System.out.println("entre al try");
            stmt.executeUpdate("UPDATE payments.PRODUCT_TOPS  SET multiple = 0 WHERE id_product =963481");
            rset = stmt.executeQuery("SELECT * FROM payments.PRODUCT_TOPS WHERE id_product= 963481");
            if (rset.next()) {
                System.out.println("SI TIENE elementos");


                int rangoMenor = (rset.getInt("minimum_transaction_value"));
                int rangoMayor = (rset.getInt("maximum_transaction_value"));


                int numero = (int) (Math.random() * (rangoMayor - rangoMenor + 1) + rangoMenor);
                System.out.println("el numero random es" + numero);
                return numero;

            } else {
                System.out.println("NO tiene elementos");
                return 0;
            }

        } catch (Exception e) {
            System.out.println("Ocurio un error");
            System.out.println(e.toString());
            throw new RuntimeException(e);

        }


    }

    public static void insertMultiplo(String multiplo) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;


        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }
        try {
            System.out.println("entre al try");
            stmt.executeUpdate("UPDATE payments.PRODUCT_TOPS  SET multiple =" + multiplo + " WHERE id_product =963481");
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void updateTopInf(int topeInf) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;


        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }
        try {
            System.out.println("entre al try");
            stmt.executeUpdate("UPDATE payments.PRODUCT_TOPS  SET minimum_transaction_value =" + topeInf + " WHERE id_product =963481");
            stmt.close();




        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void updateTopMay(int topeMay) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;


        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }
        try {
            System.out.println("entre al try");
            stmt.executeUpdate("UPDATE payments.PRODUCT_TOPS  SET maximum_transaction_value =" + topeMay + " WHERE id_product =963481");
            stmt.close();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static List<Integer>getTopes() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;
        List<Integer> topes= new ArrayList<>();


        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }
        try {
            System.out.println("entre al try");

            rset = stmt.executeQuery("SELECT * FROM payments.PRODUCT_TOPS WHERE id_product =963481 ");
            if (rset.next()) {
                topes.add(rset.getInt("minimum_transaction_value"));
                topes.add(rset.getInt("maximum_transaction_value"));
                System.out.println("SI TIENE elementos");


            } else {
                System.out.println("NO tiene elementos");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topes;
    }

    public static void eliminarFingerPrint(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;


        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }
        try {
            System.out.println("entre al try");
            stmt.executeUpdate("UPDATE hierarchies.EQUIPMENTS  SET fingerprint= NULL  where name ='16c7ce22-6faa-41ce-8579-e12e70beac27'");
            stmt.close();
        } catch (SQLException e) {
            BDInvictus.disconnect(con);
            System.out.println( "error update "+ e);
        }

    }

    public static Actor.ErrorHandlingMode updateUID(String uid){
        Connection con = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            con = BDInvictus.getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            BDInvictus.disconnect(con);
            System.out.println("error" + e);
        }
        try {
            System.out.println("entre al try");
            System.out.println("el querry es: UPDATE hierarchies.EQUIPMENTS SET name = '" + uid + "', fingerprint = NULL WHERE id_equipment ="+ UserModel.getEQUIMPENT() + ";");
            stmt.executeUpdate("UPDATE hierarchies.EQUIPMENTS SET name = '" + uid + "', fingerprint = NULL WHERE id_equipment ="+ UserModel.getEQUIMPENT() + ";");
            stmt.close();


        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }




}

