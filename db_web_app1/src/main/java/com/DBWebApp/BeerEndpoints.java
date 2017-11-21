package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.DatabaseConn.BeerQueries;
import com.IDBWebApp.IBeerEndpoints;

@Path("/beers")
public class BeerEndpoints {
    @Path("/getBeers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IBeerEndpoints.IBeerResult getBeers(@DefaultValue("1") @QueryParam("barId") int barId) {
        BeerQueries b = new BeerQueries();
        return b.getBeers(barId);
    }
}
