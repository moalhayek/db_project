package com.DatabaseConn;

import com.IDBWebApp.IBarEndpoints;
import com.IDBWebApp.IBartenderEndpoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//public class BartenderQueries {
//    public void addBartender(int bar_id, int drinker_id, String start_date){
//        try {
//            //Get the database connection
//            ApplicationDB db = new ApplicationDB();
//            Connection con = db.getConnection();
//
//            //Create a SQL statement
//            Statement stmt = con.createStatement();
//
//            //Make a insert query
//            String str = String.format("INSERT INTO bartenders (bar_id, drinker_id, start_date) " +
//                    "VALUES (%d, %d, '%s')", bar_id, drinker_id, start_date);
//
//            //Run the query against the database.
//            stmt.executeUpdate(str);
//
//            db.closeConnection(con);
//        } catch (Exception e) {
//            System.out.print(e);
//        }
//    }
//    public IBartenderEndpoints.IBartenderResult getTopBartenders(int barId){
//        IBartenderEndpoints.IBartenderResult resultClass = new IBartenderEndpoints().new IBartenderResult();
//        List<IBartenderEndpoints.IBartender> bartenders = new ArrayList<IBartenderEndpoints.IBartender>();
//
//        try {
//            //Get the database connection
//            ApplicationDB db = new ApplicationDB();
//            Connection con = db.getConnection();
//
//            //Create a SQL statement
//            Statement stmt = con.createStatement();
//
//            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
//            String str = String.format("SELECT bar_id, drinker_id, "+shift+" AS sales FROM bartenders WHERE bar_id="+barId+" ORDER BY "+shift+" desc");
//
//            //Run the query against the database.
//            ResultSet result = stmt.executeQuery(str);
//
//            //initially, result points to before first row
//            result.next();
//
//            //each result represents an age group of drinkers
//            while (result.next()) {
//                //create a new object for each age group (result) and assign it the age and avg earning
//                IBartenderEndpoints.IBartender bartender = new IBartenderEndpoints().new IBartender();
//                bartender.bar_id = result.getInt("bar_id");
//                bartender.bartender_id = result.getInt("drinker_id");
//                bartender.bartender_sales = result.getInt("sales");
//                bartender.shift_name = shift;
//
//                //add the new age group to the list of ageEarningResult age groups
//                bartenders.add(bartender);
//            }
//
//            db.closeConnection(con);
//        } catch (Exception e) {
//            System.out.print(e);
//        }
//        resultClass.topBartenders = bartenders;
//
//        return resultClass;
//    }
//}
