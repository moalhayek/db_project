package com.db_connection;

import java.io.*;
import java.util.*;
import java.sql.*;


public class ageEarningsQuery {

    public static List<List<String>> ageEarnings(){

        List<List<String>> ret = new ArrayList<List<String>>();

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

            //initially, result points to before first row
            result.next();

            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                ArrayList<String> temp = new ArrayList<String>(); //temp array to hold the whole row
                // loop through each column in the current row
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = result.getString(i);
                    //add the current column value into temp
                    temp.add(columnValue);
                }
                ret.add(temp);
            }
            System.out.println(ret);

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
