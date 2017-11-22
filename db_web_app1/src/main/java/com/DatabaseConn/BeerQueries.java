package com.DatabaseConn;

import com.IDBWebApp.IBeerEndpoints;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BeerQueries {
    public IBeerEndpoints.IBeerResult getBeers(int barId){
        IBeerEndpoints.IBeerResult resultClass = new IBeerEndpoints().new IBeerResult();
        List<IBeerEndpoints.IBeer> beersSold = new ArrayList<IBeerEndpoints.IBeer>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT b.name, b.manf, s.is_on_tap, b.abv, s.price, b.manf_price,\n" +
                    "ROUND((s.price - b.manf_price),2) AS profitPerBottle,\n" +
                    "COUNT(t.beer_id) AS total_sold,\n" +
                    "ROUND((COUNT(t.beer_id)*(s.price - b.manf_price)),2) AS total_profit\n" +
                    "FROM beers b INNER JOIN sells s INNER JOIN transactions t ON (t.bar_id = s.bar_id AND s.beer_id = b.id AND t.beer_id = s.beer_id)\n" +
                    "WHERE s.bar_id = %d\n" +
                    "GROUP BY b.name;", barId);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                IBeerEndpoints.IBeer beer = new IBeerEndpoints().new IBeer();
                //music age range
                beer.name = result.getString("name");
                beer.manuf = result.getString("manf");
                beer.isOnTap = result.getBoolean("is_on_tap");
                beer.abv = result.getDouble("abv");
                beer.salePrice = result.getInt("price");
                beer.manuf_price = result.getDouble("manf_price");
                beer.profitPerBottle = result.getDouble("profitPerBottle");
                beer.totalSold = result.getInt("total_sold");
                beer.totalProfit = result.getDouble("total_profit");

                //add the new age group to the list of ageEarningResult age groups
                beersSold.add(beer);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.beers = beersSold;

        return resultClass;
    }

    public int addBeerToSell(int bar_id, int beer_id, int is_on_tap, int price, Boolean isUpdate){
        int res;

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a insert/update query
            String query;
            if (isUpdate){
                query = String.format("UPDATE sells SET is_on_tap = %d, price = %d WHERE bar_id = %d AND beer_id = %d", is_on_tap, price, bar_id, beer_id);
                res = 1;
            }
            else{
                query = String.format("INSERT INTO sells (bar_id, beer_id, is_on_tap, price) " +
                        "VALUES (%d, %d, %d, %d)", bar_id, beer_id, is_on_tap, price);
                res = 2;
            }


            //Run the query against the database.
            stmt.executeUpdate(query);

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
            res = -1;
        }

        return res;
    }

    public Boolean alreadySold(int bar_id, int beer_id){

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a insert query
            String str = String.format("SELECT * FROM sells WHERE bar_id = %d AND beer_id = %d", bar_id, beer_id);

            //Run the query against the database.
            ResultSet rs = stmt.executeQuery(str);

            if (rs.absolute(1)){
                return true;
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }

        return false;
    }

    public IBeerEndpoints.IBeer2Result getAllBeers(){
        IBeerEndpoints.IBeer2Result resultClass = new IBeerEndpoints().new IBeer2Result();
        List<IBeerEndpoints.IBeer2> beersSold = new ArrayList<IBeerEndpoints.IBeer2>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT id, name, manf FROM beers");

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                IBeerEndpoints.IBeer2 beer = new IBeerEndpoints().new IBeer2();
                //music age range
                beer.name = result.getString("name");
                beer.manuf = result.getString("manf");
                beer.id = result.getInt("id");

                //add the new age group to the list of ageEarningResult age groups
                beersSold.add(beer);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.beers2 = beersSold;

        return resultClass;
    }
}
