package com.db_web_app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.db_connection.ageEarningsQuery;
import java.util.List;

@Path("/ageEarnings")
public class getAgeEarnings {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAgeEarning() {
        ageEarningsQuery earn = new ageEarningsQuery();

        return earn.ageEarnings().toString();
    }
}
