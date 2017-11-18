package com.DatabaseConn;

import com.IDBWebApp.IDrinkerResult;
import com.IDBWebApp.IDrinker;
import java.sql.*;


public class DrinkerQueries {

    public IDrinkerResult drinker(String id){
        IDrinkerResult resultClass = new IDrinkerResult();
        String ret = new String();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT * FROM drinkers WHERE id ='%s'", id);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            //initially, result points to before first row
            result.next();

            //now store first row results
            ret = result.getString("name");
            while (result.next()) {
                System.out.print(result.getString("name"));
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        IDrinker drinkerR = new IDrinker();
        drinkerR.name = ret;

        resultClass.drinker = drinkerR;
        return resultClass;
    }

    public int addDrinker(String name, int age, String gender, String street, String city, String state){
        int new_id = -1;

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a insert query
            String str = String.format("INSERT INTO drinkers (name, age, gender, street_address, city, state) " +
                    "VALUES ('%s', %d, '%s', '%s', '%s', '%s')", name, age, gender, street, city, state);

            //Run the query against the database.
            stmt.executeUpdate(str, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()){
                new_id = rs.getInt(1);
            }


            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }

        return new_id;
    }

//    public static void main(String[] args) {
//        int new_id = addDrinker("mo al", 21, "M", "17 hardy", "new bruns", "NJ");
//        System.out.print(new_id);
//    }


}
