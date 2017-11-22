package com.DatabaseConn;

import com.IDBWebApp.IBarEndpoints;
import com.IDBWebApp.IBartenderEndpoints;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

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
    public IBartenderEndpoints.IBartenderResult getBartenders(int barId){
        IBartenderEndpoints.IBartenderResult resultClass = new IBartenderEndpoints().new IBartenderResult();
        List<IBartenderEndpoints.IBartender> bartenders = new ArrayList<IBartenderEndpoints.IBartender>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            String query = "Select t2.name, AVG(t2.total_sales) as avg_sales, t2.shift_type, t2.day_of_week\n" +
                            "From (Select d.name, SUM(t.sale_price) as total_sales, t.shift_type, DAYOFWEEK(t.date_of_sale) as day_of_week\n" +
                            "      From transactions t INNER JOIN drinkers d ON t.employee_id = d.id\n" +
                            "      Where t.bar_id = %d\n" +
                            "      Group by date_of_sale, shift_type) as t2\n" +
                            "Group by t2.name, t2.shift_type, t2.day_of_week";

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format(query, barId);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            System.out.println(str);

            HashMap<String, early_late_pair> hmap = new HashMap<String, early_late_pair>();

            //each result represents an age group of drinkers
            while (result.next()) {
                // add to the hashmap's early/late totals for the drinker
                int dow = result.getInt("day_of_week");
                String bartender = result.getString("name");
                String shift_type = result.getString("shift_type");


                early_late_pair e = hmap.get(bartender);

                // e can either be null or a previously stored array, regardless we are updating a different dow index
                if (e == null) {
                    e = new early_late_pair();
                }

                if (shift_type.equals("early")){
                    e.early_avgs[dow-1] = result.getInt("avg_sales");
                }
                else {
                    e.late_avgs[dow-1] = result.getInt("avg_sales");
                }


                hmap.put(bartender,e);
            }

            for (String bartender : hmap.keySet()){
                IBartenderEndpoints.IBartender b = new IBartenderEndpoints().new IBartender();
                b.early_avgs = hmap.get(bartender).early_avgs;
                b.late_avgs = hmap.get(bartender).late_avgs;
                b.name = bartender;

                for (int i = 0; i < 7; i++){
                    b.total_avgs[i] = b.early_avgs[i] + b.late_avgs[i];
                }

                bartenders.add(b);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.bartenders = bartenders;

        return resultClass;
    }

    private class early_late_pair{
        public int[] early_avgs = new int[] {0,0,0,0,0,0,0};    // every index corresponds to an avg for that day of the week
        public int[] late_avgs = new int[] {0,0,0,0,0,0,0};
    }

}
