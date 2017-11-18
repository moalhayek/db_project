package com.DatabaseConn;

import java.sql.Connection;
import java.sql.Statement;

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
}
