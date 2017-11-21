package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.DatabaseConn.DrinkerQueries;
import com.IDBWebApp.IDrinkerEndpoints;

@Path("/drinkers")
public class DrinkerEndpoints {
    @Path("/getDrinker")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IDrinkerEndpoints.IDrinkerResult getDrinker(@DefaultValue("1") @QueryParam("barId") int barId) {
        DrinkerQueries d = new DrinkerQueries();
        return d.drinker(barId);
    }
    @Path("/getAgeEarning")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IDrinkerEndpoints.IAgeEarningsResult getAgeEarning() {
        DrinkerQueries earn = new DrinkerQueries();
        return earn.ageEarnings();
    }
}
