package com.db_web_app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.db_connection.ageEarningsQuery;
import java.util.ArrayList;

@Path("/ageEarnings")
public class getAgeEarnings {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList<String> getAgeEarning() {
        ageEarningsQuery earn = new ageEarningsQuery();

        return earn.ageEarnings();
    }
}
