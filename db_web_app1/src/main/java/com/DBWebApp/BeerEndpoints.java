package com.DBWebApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.DatabaseConn.BeerQueries;
import com.IDBWebApp.IBeerEndpoints;
import com.IDBWebApp.ISellsXMLEndpoint;

@Path("/beers")
public class BeerEndpoints {
    @Path("/getBeers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IBeerEndpoints.IBeerResult getBeers(@DefaultValue("1") @QueryParam("barId") int barId) {
        BeerQueries b = new BeerQueries();
        return b.getBeers(barId);
    }

    @Path("/getAllBeers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IBeerEndpoints.IBeer2Result getBeers() {
        BeerQueries b = new BeerQueries();
        return b.getAllBeers();
    }

    @Path("/addBeer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postBeer(ISellsXMLEndpoint input) {
        //add the drinker to the drinkers table
        BeerQueries b = new BeerQueries();

        int result = b.addBeerToSell(input.bar_id, input.beer_id, input.is_on_tap, input.price, b.alreadySold(input.bar_id, input.beer_id));

        if (result == -1){
            return Response.serverError().entity("Cannot sell a beer for less than you bought it for").build();
        }
        return Response.ok(result).build();
    }

}
