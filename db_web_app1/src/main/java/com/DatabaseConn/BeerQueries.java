package com.DatabaseConn;

import com.IDBWebApp.IBeerEndpoints;

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
            String str = String.format("SELECT b.name, b.manf, s.is_on_tap, b.abv, s.price, b.manf_price, round((s.price - b.manf_price),2) AS profit FROM beers b, sells s WHERE s.bar_id = %d AND s.beer_id = b.id", barId);

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
                beer.profit = result.getDouble("profit");

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
}
