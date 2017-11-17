package com.DatabaseConn;

import java.util.*;
import java.sql.*;
import com.IDBWebApp.IAgeEarnings;
import com.IDBWebApp.IAgeEarningsResult;


public class ageEarningsQuery {

    public IAgeEarningsResult ageEarnings(){
        IAgeEarningsResult resultClass = new IAgeEarningsResult();
        List<IAgeEarnings> drinkers = new ArrayList<IAgeEarnings>();

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
                //create a new object for each age group (result) and assign it the age and avg earning
                IAgeEarnings ageGroup = new IAgeEarnings();
                ageGroup.averageEarning = result.getString("avg(salary)");
                ageGroup.age = result.getString("age");

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
