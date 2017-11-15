package com.db_web_app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.db_connection.drinkerQuery;

@Path("/drinkers")
public class getDrinkers {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDrinker(@DefaultValue("1") @QueryParam("id") String id) {
        drinkerQuery d = new drinkerQuery();

        return d.drinker(id);
    }
}
