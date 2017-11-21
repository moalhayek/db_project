package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.DatabaseConn.DrinkerQueries;
import com.IDBWebApp.IDrinkerEndpoints;
import com.IDBWebApp.IDrinkerXMLEndpoints;

@Path("/drinkers")
public class DrinkerEndpoints {
    @Path("/getDrinkers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IDrinkerEndpoints.IDrinkerResult getDrinkers(@DefaultValue("1") @QueryParam("barId") int barId) {
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

    @Path("/addDrinker")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postDrinker(IDrinkerXMLEndpoints input) {
        //add the drinker to the drinkers table
        DrinkerQueries d = new DrinkerQueries();
        int new_drinker_id = d.addDrinker(input.name, input.age, input.gender, input.street_address, input.city, input.state, input.salary, input.spending_per_night, input.crowding_pref, input.relationship_status);

        if (new_drinker_id == -1){
            return Response.serverError().entity("Cannot add alcoholics or under age drinkers to database").build();
        }
        return Response.ok(new_drinker_id).build();
    }

    @Path("/getDrinkerById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IDrinkerXMLEndpoints getDrinkerById(@DefaultValue("1") @QueryParam("drinkerId") int drinkerId) {
        DrinkerQueries d = new DrinkerQueries();
        return d.getDrinkerById(drinkerId);
    }
}
