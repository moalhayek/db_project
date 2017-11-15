package com.db_connection;

import java.io.*;
import java.util.*;
import java.sql.*;


public class ageEarningsQuery {

    public static ArrayList<String> ageEarnings(){

        ArrayList<String> ret = new ArrayList<String>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT age, avg(salary) FROM drinkers GROUP BY age");

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = result.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
                ret.add(result.toString());
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }

        return ret;
    }

    public static void main(String[] args) {
        ageEarnings();
    }


}
