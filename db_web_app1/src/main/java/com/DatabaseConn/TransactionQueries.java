package com.DatabaseConn;

import com.IDBWebApp.ITransactionsEndpoints;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionQueries {
    public ITransactionsEndpoints.IEarningsResult getMonthlyEarnings(int bar_id, String start_date, String end_date) {
        ITransactionsEndpoints.IEarningsResult resultClass = new ITransactionsEndpoints().new IEarningsResult();
        List<ITransactionsEndpoints.IEarning> monthlyEarnings = new ArrayList<ITransactionsEndpoints.IEarning>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();


            //Create a SQL statement
            Statement stmt = con.createStatement();

            String query = "Select t1.year, t1.month, t1.monthly_earning as early_earnings, t2.monthly_earning as late_earnings\n" +
                    "From (SELECT YEAR(date_of_sale) as year, MONTH(date_of_sale) as month, SUM(sale_price) as monthly_earning\n" +
                    "      FROM transactions\n" +
                    "      WHERE bar_id = %d\n" +
                    "            AND date_of_sale >= '%s'\n" +
                    "            AND date_of_sale <= '%s'\n" +
                    "            AND shift_type = 'early'\n" +
                    "      GROUP BY year, month) as t1\n" +
                    "      INNER JOIN\n" +
                    "      (SELECT YEAR(date_of_sale) as year, MONTH(date_of_sale) as month, SUM(sale_price) as monthly_earning\n" +
                    "       FROM transactions\n" +
                    "       WHERE bar_id = %d\n" +
                    "             AND date_of_sale >= '%s'\n" +
                    "             AND date_of_sale <= '%s'\n" +
                    "             AND shift_type = 'late'\n" +
                    "       GROUP BY year, month) as t2\n" +
                    "      ON t1.year = t2.year AND t1.month = t2.month";

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format(query, bar_id, start_date, end_date, bar_id, start_date, end_date);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                ITransactionsEndpoints.IEarning monthlyEarning = new ITransactionsEndpoints().new IEarning();
                monthlyEarning.month = result.getString("month");
                monthlyEarning.year = result.getString("year");
                monthlyEarning.early_earnings = result.getInt("early_earnings");
                monthlyEarning.late_earnings = result.getInt("late_earnings");

                monthlyEarning.total_earnings = monthlyEarning.early_earnings + monthlyEarning.late_earnings;
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

    public ITransactionsEndpoints.IDailyAveragesResult getDailyAverages(int bar_id, String start_date, String end_date) {
        ITransactionsEndpoints.IDailyAveragesResult resultClass = new ITransactionsEndpoints().new IDailyAveragesResult();
        List<ITransactionsEndpoints.IDailyAverages> dailyAverages = new ArrayList<ITransactionsEndpoints.IDailyAverages>();

        try {
            //Get the database connection
            ApplicationDB db = new ApplicationDB();
            Connection con = db.getConnection();


            //Create a SQL statement
            Statement stmt = con.createStatement();

            String query = "SELECT t1.day_of_week, AVG(t1.total_revenue) as early_daily_average, AVG(t2.total_revenue) as late_daily_average\n" +
                    "From (SELECT DAYOFWEEK(date_of_sale) as day_of_week, MONTH(date_of_sale) as month, YEAR(date_of_sale) as year,  SUM(sale_price) as total_revenue\n" +
                    "      FROM transactions\n" +
                    "      WHERE bar_id = %d\n" +
                    "            AND date_of_sale >= '%s'\n" +
                    "            AND date_of_sale <= '%s'\n" +
                    "            AND shift_type = 'early'\n" +
                    "      GROUP BY day_of_week, month, year) as t1\n" +
                    "      INNER JOIN\n" +
                    "      (SELECT DAYOFWEEK(date_of_sale) as day_of_week, MONTH(date_of_sale) as month, YEAR(date_of_sale) as year,  SUM(sale_price) as total_revenue\n" +
                    "       FROM transactions\n" +
                    "       WHERE bar_id = %d\n" +
                    "             AND date_of_sale >= '%s'\n" +
                    "             AND date_of_sale <= '%s'\n" +
                    "             AND shift_type = 'late'\n" +
                    "       GROUP BY day_of_week, month, year) as t2\n" +
                    "      ON t1.day_of_week = t2.day_of_week\n" +
                    "Group by t1.day_of_week;";

            //Make a SELECT query from the table specified by the 'command' parameter at the index.jsp
            String str = String.format(query, bar_id, start_date, end_date, bar_id, start_date, end_date);

            //Run the query against the database.
            ResultSet result = stmt.executeQuery(str);

            while (result.next()) {
                //create a new object for each age group (result) and assign it the age and avg earning
                ITransactionsEndpoints.IDailyAverages dailyAverage = new ITransactionsEndpoints().new IDailyAverages();
                dailyAverage.dayOfWeek = result.getInt("day_of_week");
                dailyAverage.avg_early_earnings = result.getInt("early_daily_average");
                dailyAverage.avg_late_earnings = result.getInt("late_daily_average");

                dailyAverage.avg_total_earnings = dailyAverage.avg_early_earnings + dailyAverage.avg_late_earnings;
                //add the new age group to the list of ageEarningResult age groups
                dailyAverages.add(dailyAverage);
            }

            db.closeConnection(con);
        } catch (Exception e) {
            System.out.print(e);
        }
        resultClass.dailyAverages = dailyAverages;

        return resultClass;
    }


}
