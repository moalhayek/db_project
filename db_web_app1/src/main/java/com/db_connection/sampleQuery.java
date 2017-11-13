package com.db_connection;

import java.io.*;
import java.util.*;
import java.sql.*;


public class sampleQuery {

    public static void sample(){

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = "SELECT * FROM drinkers";
            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            while (result.next()) {
                System.out.print(result.getString("name"));
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void main(String[] args) {
        sample();
    }


}
