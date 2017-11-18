package com.DatabaseConn;

import com.IDBWebApp.IBarEndpoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BarQueries {
    public IBarEndpoints.IBarResult getBarName(){
        IBarEndpoints.IBarResult resultClass = new IBarEndpoints().new IBarResult();
        List<IBarEndpoints.IBar> bars = new ArrayList<IBarEndpoints.IBar>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT name, id FROM bars");

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            //initially, result points to before first row
            result.next();

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                IBarEndpoints.IBar bar = new IBarEndpoints().new IBar();
                bar.name = result.getString("name");
                bar.id = result.getString("id");

                //add the new age group to the list of ageEarningResult age groups
                bars.add(bar);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.bars = bars;

        return resultClass;
    }
}
