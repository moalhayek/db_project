package com.db_web_app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.db_connection.ageEarningsQuery;
import com.google.gson.Gson;

@Path("/ageEarnings")
public class getAgeEarnings {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAgeEarning() {
        ageEarningsQuery earn = new ageEarningsQuery();
        Gson gson = new Gson();
        String json = gson.toJson(earn.ageEarnings());
        return json;
    }
}
