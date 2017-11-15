package com.db_connection;

import java.io.*;
import java.util.*;
import java.sql.*;


public class drinkerQuery {

    public static String drinker(String id){

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

        return ret;
    }

    public static void main(String[] args) {
        drinker("1");
    }


}
