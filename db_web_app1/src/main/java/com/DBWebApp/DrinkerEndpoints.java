package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.DatabaseConn.DrinkerQueries;
import com.google.gson.Gson;

@Path("/drinkers")
public class DrinkerEndpoints {
    @Path("/getDrinker")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDrinker(@DefaultValue("1") @QueryParam("id") String id) {
        DrinkerQueries d = new DrinkerQueries();
        Gson gson = new Gson();
        String json = gson.toJson(d.drinker(id));

        return json;
    }
}
