package com.db_web_app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.db_connection.drinkerQuery;
import com.google.gson.Gson;

@Path("/drinkers")
public class getDrinkers {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDrinker(@DefaultValue("1") @QueryParam("id") String id) {
        drinkerQuery d = new drinkerQuery();
        Gson gson = new Gson();
        String json = gson.toJson(d.drinker(id));

        return json;
    }
}
