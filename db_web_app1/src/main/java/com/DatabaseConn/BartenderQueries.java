package com.DatabaseConn;

import com.IDBWebApp.IBarEndpoints;
import com.IDBWebApp.IBartenderEndpoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BartenderQueries {
    public void addBartender(int bar_id, int drinker_id, String start_date){
        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a insert query
            String str = String.format("INSERT INTO bartenders (bar_id, drinker_id, start_date) " +
                    "VALUES (%d, %d, '%s')", bar_id, drinker_id, start_date);

            //Run the query against the database.
            stmt.executeUpdate(str);

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public IBarEndpoints.IBarResult getTopBartenders(int barId, String shift){
        IBartenderEndpoints.IBartenderResult resultClass = new IBartenderEndpoints().new IBartenderResult();
        List<IBartenderEndpoints.IBartender> bartenders = new ArrayList<IBartenderEndpoints.IBartender>();

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
                bar.id = result.getInt("id");

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
