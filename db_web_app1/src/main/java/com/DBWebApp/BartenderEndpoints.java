package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.DatabaseConn.BartenderQueries;
import com.DatabaseConn.DrinkerQueries;
import com.IDBWebApp.IBartenderEndpoints;
import com.IDBWebApp.IBartenderXMLEndpoints;

// posting a bartender using their name

@Path("/bartenders")
public class BartenderEndpoints {
    @POST
    @Path("/postBartender")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postBartender(IBartenderXMLEndpoints input) {
        //add the drinker to the drinkers table
        DrinkerQueries d = new DrinkerQueries();
        int new_drinker_id = d.addDrinker(input.name, input.age, input.gender, input.street_address, input.city, input.state);
        //add the bartender to the bartenders table
        BartenderQueries b = new BartenderQueries();
        b.addBartender(input.barId, new_drinker_id, input.startDate);
    }
    @Path("/getTopBartenders")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IBartenderEndpoints.IBartenderResult getTopBartenders(@DefaultValue("1") @QueryParam("barId") String barId, @DefaultValue("sat_late_avg_sales") @QueryParam("shift") String shift) {
        BartenderQueries bt = new BartenderQueries();
        return bt.getTopBartenders(barId, shift);
    }
}
