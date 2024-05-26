package com.example.summarization;

import java.util.ArrayList;

public class SummaryGenerator {
    private String kind;
    private int type;
    private ArrayList<Quantifier> quantifiers;
    private String subject1;
    private String subject2;
    private ArrayList<LinguisticVariable> linguisticVariables;
    private ArrayList<Summary> summaries;
    private ArrayList<Double> measuresWeights;
    private ArrayList<String> qualifiers;
    private ArrayList<LinguisticVariable> summarizers;
    private ArrayList<Credit> credits;



    public void sortSummaries(int measureID) {
        return;
    }

    public SummaryGenerator(String kind, int type, ArrayList<Quantifier> quantifiers, String subject1, String subject2, ArrayList<LinguisticVariable> linguisticVariables, ArrayList<Double> measuresWeights, ArrayList<String> qualifiers, ArrayList<LinguisticVariable> summarizers) {
        this.kind = kind;
        this.type = type;
        this.quantifiers = quantifiers;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.linguisticVariables = linguisticVariables;
        this.measuresWeights = measuresWeights;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.credits = databaseConnector.fetchData();
    }

    public void generateSummaries() {
        if (type == 1) {
            for (Quantifier quantifier : quantifiers) {
                int columnIndex = 0;
                for (LinguisticVariable linguisticVariable : summarizers) {
                    for (Label label : linguisticVariable.getLabels()) {
                        double sum = 0.0;
                        for (Credit credit : credits) {
                            double value = getCalueByColumnIndex(columnIndex, credit);
                            sum += label.getMembershipFunction().calculateMembershipDegree(value);
                        }
                        double totalMembership = sum/credits.size();
                        for (Label quantifierLabel : quantifier.getLabels()) {
                            quantifierLabel.getMembershipFunction().calculateMembershipDegree(totalMembership);
                            String summary = quantifierLabel.getName() + " are " + label.getName();
                            System.out.println(summary);
                        }
                    }
                    columnIndex++;
                }
            }
        }
    }

    public double getCalueByColumnIndex(int columnIndex, Credit credit) {
        switch (columnIndex) {
            case 0:
                return credit.getAmount();
            case 1:
                return credit.getIntRate();
            case 2:
                return credit.getAnnualIncome();
            case 3:
                return credit.getNumberOfQuestions();
            case 4:
                return credit.getInstallment();
            case 5:
                return credit.getDti();
            case 6:
                return credit.getRevolBalance();
            case 7:
                return credit.getTotalCollAmount();
            case 8:
                return credit.getCreditLimit();
            case 9:
                return credit.getTotalAccountsBalance();
        }
        return 0.0;
    }

}
