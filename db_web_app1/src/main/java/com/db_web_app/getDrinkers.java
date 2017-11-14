package com.db_web_app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.db_connection.sampleQuery;

@Path("/drinkers")
public class getDrinkers {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDrinker(@DefaultValue("1") @QueryParam("id") String id) {
        sampleQuery s = new sampleQuery();

        return s.sample(id);
    }
}
