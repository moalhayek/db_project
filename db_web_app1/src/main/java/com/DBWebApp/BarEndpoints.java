package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.DatabaseConn.BarQueries;
import com.google.gson.Gson;

@Path("/bars")
public class BarEndpoints {
    @Path("/getBarNames")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBarNames() {
        BarQueries b = new BarQueries();
        Gson gson = new Gson();
        String json = gson.toJson(b.getBarName());
        return json;
    }
}
