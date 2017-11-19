package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.DatabaseConn.BarQueries;
import com.IDBWebApp.IBarEndpoints;
import com.IDBWebApp.IDeleteBarEndpoint;

@Path("/bars")
public class BarEndpoints {
    @Path("/getBarNames")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IBarEndpoints.IBarResult getBarNames() {
        BarQueries b = new BarQueries();
        return b.getBarName();
    }

    @Path("/deleteBar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteBar(IDeleteBarEndpoint input) {
        BarQueries b = new BarQueries();
        b.deleteBar(input.id);
    }
}
