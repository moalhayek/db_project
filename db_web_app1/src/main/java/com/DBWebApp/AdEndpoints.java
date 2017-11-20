package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.DatabaseConn.AdQueries;
import com.IDBWebApp.IAdEndpoints;

@Path("/ads")
public class AdEndpoints {
    @Path("/getAdPurchases")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IAdEndpoints.IAdPurchaseResult getAdPurchases(@DefaultValue("1") @QueryParam("barId") int barId) {
        AdQueries a = new AdQueries();
        return a.getAdPurchases(barId);
    }
}
