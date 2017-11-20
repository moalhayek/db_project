package com.DBWebApp;

import com.DatabaseConn.TransactionQueries;
import com.IDBWebApp.ITransactionsEndpoints;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/transactions")
public class TransactionEndpoints {
    @Path("/getEarnings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ITransactionsEndpoints.IEarningsResult getMonthlyEarnings(@DefaultValue("1") @QueryParam("barId") int barId, @DefaultValue("2016-01-01") @QueryParam("startDate") String start_date, @DefaultValue("2020-12-31") @QueryParam("endDate") String end_date) {
        TransactionQueries t = new TransactionQueries();
        return t.getMonthlyEarnings(barId, start_date, end_date);
    }

    @Path("/getDailyAverages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ITransactionsEndpoints.IDailyAveragesResult getDailyAverages(@DefaultValue("1") @QueryParam("barId") int barId, @DefaultValue("2016-01-01") @QueryParam("startDate") String start_date, @DefaultValue("2020-12-31") @QueryParam("endDate") String end_date) {
        TransactionQueries t = new TransactionQueries();
        return t.getDailyAverages(barId, start_date, end_date);
    }
}
