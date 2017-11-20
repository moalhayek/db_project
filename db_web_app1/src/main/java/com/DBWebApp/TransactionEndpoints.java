package com.DBWebApp;

import com.DatabaseConn.TransactionQueries;
import com.IDBWebApp.ITransactionsEndpoints;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.joda.time.LocalDate;


@Path("/transactions")
public class TransactionEndpoints {
    @Path("/getMonthlyEarnings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ITransactionsEndpoints.IEarningsResult getMonthlyEarnings(@DefaultValue("1") @QueryParam("barId") int barId, @DefaultValue("2016-01-01") @QueryParam("startDate") String start_date, @DefaultValue("2020-12-31") @QueryParam("endDate") String end_date, @DefaultValue("total") @QueryParam("timeOfDay") String time_of_day  ) {
        //LocalDate sd = LocalDate.parse(start_date);
        //LocalDate ed = LocalDate.parse(end_date);

        TransactionQueries t = new TransactionQueries();
        return t.getMonthlyEarnings(barId, start_date, end_date, time_of_day);
    }
}
