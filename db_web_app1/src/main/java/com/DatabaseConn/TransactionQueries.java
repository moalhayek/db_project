package com.DatabaseConn;

import com.IDBWebApp.ITransactionsEndpoints;
import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionQueries {
    public ITransactionsEndpoints.IEarningsResult getMonthlyEarnings(int bar_id, String start_date, String end_date, String time_of_day) {
        ITransactionsEndpoints.IEarningsResult resultClass = new ITransactionsEndpoints().new IEarningsResult();
        List<ITransactionsEndpoints.IEarning> monthlyEarnings = new ArrayList<ITransactionsEndpoints.IEarning>();
        String tod_string = "";
        if (!time_of_day.equals("total")){
            tod_string += "AND shift_type = '" + time_of_day + "'";
        }

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();


            //Create a SQL statement
            Statement stmt = con.createStatement();

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format("SELECT YEAR(date_of_sale) as year, MONTH(date_of_sale) as month, SUM(sale_price) as monthly_earning " +
                                        "FROM transactions " +
                                        "WHERE bar_id = %d " +
                                        "AND date_of_sale >= '%s' " +
                                        "AND date_of_sale <= '%s' " +
                                        "%s " +
                                        "GROUP BY year, month", bar_id, start_date, end_date, tod_string);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);
            // for loop from start to end, incrementing 1 month at a time
//            for (LocalDate i = start_date; i.compareTo(end_date) < 0; i.plusMonths(1)){
//                //Create a SQL statement
//                Statement stmt = con.createStatement();
//
//                //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
//                String str = String.format("SELECT age, avg(salary) FROM drinkers GROUP BY age");
//
//                //Run the query against the database.
//                ResultSet result = stmt.executeQuery(str);
//            }


            //initially, result points to before first row
            //result.next();

            //each result represents a monthly earning
            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                ITransactionsEndpoints.IEarning monthlyEarning = new ITransactionsEndpoints().new IEarning();
                monthlyEarning.month = result.getString("month");
                monthlyEarning.year = result.getString("year");
                monthlyEarning.earnings = result.getInt("monthly_earning");

                //add the new age group to the list of ageEarningResult age groups
                monthlyEarnings.add(monthlyEarning);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.monthly_earnings = monthlyEarnings;

        return resultClass;
    }
}
