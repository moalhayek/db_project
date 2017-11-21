package com.DatabaseConn;

import com.IDBWebApp.IDrinkerEndpoints;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DrinkerQueries {

    public IDrinkerEndpoints.IDrinkerResult drinker(int barId){
        IDrinkerEndpoints.IDrinkerResult resultClass = new IDrinkerEndpoints().new IDrinkerResult();
        List<IDrinkerEndpoints.IDrinker> drinkers = new ArrayList<IDrinkerEndpoints.IDrinker>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT d.name, d.spending_per_night, b.name AS beer_name\n" +
                    "FROM drinkers d INNER JOIN frequents f INNER JOIN likes l INNER JOIN beers b INNER JOIN sells s ON (d.id = f.drinker_id AND l.drinker_id = d.id AND l.beer_id = b.id AND s.beer_id = b.id AND s.bar_id = f.bar_id)\n" +
                    "WHERE f.bar_id = %d AND b.name = (SELECT b1.name\n" +
                    "                                   FROM sells s1 INNER JOIN likes l1 INNER JOIN beers b1 ON (l1.beer_id = b1.id AND s1.beer_id = l1.beer_id)\n" +
                    "                                   WHERE s1.beer_id = b1.id AND s1.bar_id = f.bar_id AND l1.drinker_id = f.drinker_id\n" +
                    "                                   ORDER BY s1.price DESC\n" +
                    "                                   LIMIT 1)\n" +
                    "ORDER BY d.spending_per_night desc;", barId);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            while (result.next()) {
                IDrinkerEndpoints.IDrinker drinker = new IDrinkerEndpoints().new IDrinker();
                drinker.name = result.getString("name");
                drinker.spendingPerNight = result.getInt("spending_per_night");
                drinker.expensiveFavBeer = result.getString("beer_name");
                drinkers.add(drinker);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }


        resultClass.drinkers = drinkers;
        return resultClass;
    }

    public int addDrinker(String name, int age, String gender, String street, String city, String state){
        int new_id = -1;

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a insert query
            String str = String.format("INSERT INTO drinkers (name, age, gender, street_address, city, state) " +
                    "VALUES ('%s', %d, '%s', '%s', '%s', '%s')", name, age, gender, street, city, state);

            //Run the query against the database.
            stmt.executeUpdate(str, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()){
                new_id = rs.getInt(1);
            }


            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }

        return new_id;
    }

    public IDrinkerEndpoints.IAgeEarningsResult ageEarnings() {
        IDrinkerEndpoints.IAgeEarningsResult resultClass = new IDrinkerEndpoints().new IAgeEarningsResult();
        List<IDrinkerEndpoints.IAgeEarnings> drinkers = new ArrayList<IDrinkerEndpoints.IAgeEarnings>();

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

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                IDrinkerEndpoints.IAgeEarnings ageGroup = new IDrinkerEndpoints().new IAgeEarnings();
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

//    public static void main(String[] args) {
//        int new_id = addDrinker("mo al", 21, "M", "17 hardy", "new bruns", "NJ");
//        System.out.print(new_id);
//    }


}
