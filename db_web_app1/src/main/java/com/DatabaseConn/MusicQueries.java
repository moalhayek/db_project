package com.DatabaseConn;

import com.IDBWebApp.IMusicEndpoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MusicQueries {
    public IMusicEndpoints.IMusicResult getMusicTrends(String lowerAge, String upperAge){
        IMusicEndpoints.IMusicResult resultClass = new IMusicEndpoints().new IMusicResult();
        List<IMusicEndpoints.IMusic> musicAgeData = new ArrayList<IMusicEndpoints.IMusic>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT m1.genre, COUNT(l1.drinker_id) FROM listens l1, music m1, drinkers d1 WHERE l1.music_id = m1.id AND l1.drinker_id = d1.id AND (d1.age BETWEEN "+lowerAge+" AND "+upperAge+") GROUP BY m1.genre");

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            //initially, result points to before first row
            result.next();

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                IMusicEndpoints.IMusic music = new IMusicEndpoints().new IMusic();
                music.ageGroup = String.format(lowerAge+"-"+upperAge);
                music.genre = result.getString("m1.genre");
                music.listeners = result.getString("COUNT(l1.drinker_id)");

                //add the new age group to the list of ageEarningResult age groups
                musicAgeData.add(music);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.musicAgeData = musicAgeData;

        return resultClass;
    }
}
