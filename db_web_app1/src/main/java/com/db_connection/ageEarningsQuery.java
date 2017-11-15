package com.db_connection;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.resultClasses.ageEarningsResult;
import com.resultClasses.drinkerAgeGroupEarnings;


public class ageEarningsQuery {

    public drinkerAgeGroupEarnings ageEarnings(){
        drinkerAgeGroupEarnings resultClass = new drinkerAgeGroupEarnings();
        List<ageEarningsResult> drinkers = new ArrayList<ageEarningsResult>();

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

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group and assign it the age and avg earning
                ageEarningsResult ageGroup = new ageEarningsResult();
                // loop through each column in the current row
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) ageGroup.averageEarning = result.getString(i);
                    else ageGroup.age = result.getString(i);
                }
                //add the new age group to the list of ageEarningResult age groups
                drinkers.add(ageGroup);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.drinkersAgeGroups = drinkers;

        return resultClass;
    }

    public static void main(String[] args) {
        //ageEarnings();
    }


}
