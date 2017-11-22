package com.DatabaseConn;

import com.IDBWebApp.IMusicEndpoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MusicQueries {
    public IMusicEndpoints.IMusicResult getMusicTrends(int barId){
        IMusicEndpoints.IMusicResult resultClass = new IMusicEndpoints().new IMusicResult();
        List<IMusicEndpoints.IMusic> musicData = new ArrayList<IMusicEndpoints.IMusic>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();

            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT m1.genre, COUNT(*) as number_of_people\n" +
                    "FROM listens l1 INNER JOIN music m1 INNER JOIN frequents f1\n" +
                    "    ON (l1.music_id = m1.id AND l1.drinker_id = f1.drinker_id)\n" +
                    "WHERE f1.bar_id = %d\n" +
                    "GROUP BY l1.music_id", barId);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            //each result represents an age group of drinkers
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                IMusicEndpoints.IMusic music = new IMusicEndpoints().new IMusic();
                //music age range
                music.genre = result.getString("genre");
                music.listeners = result.getInt("number_of_people");

                //add the new age group to the list of ageEarningResult age groups
                musicData.add(music);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.musicData = musicData;

        return resultClass;
    }
}
