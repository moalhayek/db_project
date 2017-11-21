package com.DatabaseConn;

import com.IDBWebApp.IAdEndpoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdQueries {
    public IAdEndpoints.IAdPurchaseResult getAdPurchases(int barId){
        IAdEndpoints.IAdPurchaseResult resultClass = new IAdEndpoints().new IAdPurchaseResult();
        List<IAdEndpoints.IAdPurchase> adPurchases = new ArrayList<IAdEndpoints.IAdPurchase>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT p.bar_id, a.platform, p.start_date, p.end_date,(DATEDIFF(p.end_date, p.start_date)*a.cost_per_day) AS total_cost, p.total_clicks, (DATEDIFF(p.end_date, p.start_date)*a.cost_per_day)/p.total_clicks AS CPC FROM ad_purchases p INNER JOIN ad_platforms a ON (p.platform_id = a.id) WHERE p.bar_id = %d", barId);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                IAdEndpoints.IAdPurchase adPurchase = new IAdEndpoints().new IAdPurchase();
                //music age range
                adPurchase.barId = barId;
                adPurchase.platformName = result.getString("platform");
                adPurchase.startDate = result.getString("start_date");
                adPurchase.endDate = result.getString("end_date");
                adPurchase.totalCost = result.getInt("total_cost");
                adPurchase.totalClicks = result.getInt("total_clicks");
                adPurchase.costPerClick = result.getDouble("CPC");

                //add the new age group to the list of ageEarningResult age groups
                adPurchases.add(adPurchase);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.adPurchases = adPurchases;

        return resultClass;
    }
}
