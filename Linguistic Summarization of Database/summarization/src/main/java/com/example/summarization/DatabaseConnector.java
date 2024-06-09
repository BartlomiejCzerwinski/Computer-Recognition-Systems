package com.example.summarization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:sqlite:database.db";
    private int LIMIT_OF_DATA_POINTS = 5000;//800000;
    private int NUMBER_OF_ALL_CREDITS = 800000;
    private ArrayList<Credit> credits;
    private int NUMBER_OF_RECORDS = 0;

    public DatabaseConnector() {
        credits = fetchData();
    }

    public ArrayList<Credit> fetchData() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Credit> credits = new ArrayList<>();
        int test = 0;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
            System.out.println("SUCCESSFULLY CONNECTED WITH DATABASE.");

            stmt = conn.createStatement();

            String sql = "SELECT * FROM loan";
            rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next() && i < NUMBER_OF_ALL_CREDITS) {
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
                String creditPurpose = rs.getString("purpose");
                if (amount < 10000)
                    test++;

                Credit credit = new Credit(amount, intRate, annualIncome, numberOfQuestions,
                        installment, dti, revolBalance, totalCollAmount, creditLimit, totalAccountsBalance, creditPurpose);
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
        System.out.println("LOADED " + credits.size() + " CREDITS FROM DATABASE.");
        return credits;
    }

    private ArrayList<Credit> getCreditsByCategory(String category) {
        int numOfCredits = 0;
        ArrayList<Credit> result = new ArrayList<>();
        for (Credit credit : credits) {
            if (credit.getPurpose().equals(category)) {
                result.add(credit);
                numOfCredits ++;
            }
            if (numOfCredits >= LIMIT_OF_DATA_POINTS)
                break;
        }
        return result;
    }

    public HashMap<CreditsType, ArrayList<Credit>> getAllCredits() {
        HashMap<CreditsType, ArrayList<Credit>> result = new HashMap<>();
        result.put(CreditsType.CREDIT_CARD, getCreditsByCategory("credit_card"));
        result.put(CreditsType.CAR, getCreditsByCategory("car"));
        result.put(CreditsType.SMALL_BUSINESS, getCreditsByCategory("small_business"));
        result.put(CreditsType.DEBT_CONSOLIDATION, getCreditsByCategory("debt_consolidation"));
        result.put(CreditsType.HOME_IMPROVEMENT, getCreditsByCategory("home_improvement"));
        result.put(CreditsType.MAJOR_PURCHASE, getCreditsByCategory("major_purchase"));
        result.put(CreditsType.MEDICAL, getCreditsByCategory("medical"));
        ArrayList<Credit> all = getCreditsByCategory("credit_card");
        all.addAll(getCreditsByCategory("car"));
        all.addAll(getCreditsByCategory("small_business"));
        all.addAll(getCreditsByCategory("debt_consolidation"));
        all.addAll(getCreditsByCategory("home_improvement"));
        all.addAll(getCreditsByCategory("major_purchase"));
        all.addAll(getCreditsByCategory("medical"));
        result.put(CreditsType.ALL, all);
        return result;
    }


}
