package com.example.summarization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:sqlite:database.db";
    private int LIMIT_OF_DATA_POINTS = 800000;
    private int NUMBER_OF_RECORDS = 0;
    public ArrayList<Credit> fetchData() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Credit> credits = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
            System.out.println("SUCCESSFULLY CONNECTED WITH DATABASE.");

            stmt = conn.createStatement();

            String sql = "SELECT * FROM loan";
            rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next() && i < LIMIT_OF_DATA_POINTS) {
                NUMBER_OF_RECORDS ++;
                float amount = rs.getFloat("loan_amnt");
                float intRate = rs.getFloat("int_rate");
                float annualIncome = rs.getFloat("annual_inc");
                int numberOfQuestions = rs.getInt("inq_last_6mths");
                float installment = rs.getFloat("installment");
                int dti = rs.getInt("dti");
                float revolBalance = rs.getFloat("revol_bal");
                float totalCollAmount = rs.getFloat("tot_coll_amt");
                float creditLimit = rs.getFloat("total_rev_hi_lim");
                float totalAccountsBalance = rs.getFloat("tot_cur_bal");

                Credit credit = new Credit(amount, intRate, annualIncome, numberOfQuestions,
                        installment, dti, revolBalance, totalCollAmount, creditLimit, totalAccountsBalance);
                credits.add(credit);
                i++;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("LOADED " + credits.size() + " CREDITS.");
        return credits;
    }

}
