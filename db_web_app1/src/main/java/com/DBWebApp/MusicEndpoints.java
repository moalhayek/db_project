package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.DatabaseConn.MusicQueries;
import com.IDBWebApp.IMusicEndpoints;

//rest path for music
@Path("/music")
public class MusicEndpoints {
    @Path("/getMusicTrends")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IMusicEndpoints.IMusicResult getMusicTrends(@DefaultValue("21") @QueryParam("lowerAge") String lowerAge, @DefaultValue("60") @QueryParam("upperAge") String upperAge) {
        MusicQueries m = new MusicQueries();
        return m.getMusicTrends(lowerAge, upperAge);
    }
}
