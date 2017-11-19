package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.DatabaseConn.BarQueries;
import com.IDBWebApp.IBarEndpoints;

@Path("/bars")
public class BarEndpoints {
    @Path("/getBarNames")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IBarEndpoints.IBarResult getBarNames() {
        BarQueries b = new BarQueries();
        return b.getBarName();
    }
}
