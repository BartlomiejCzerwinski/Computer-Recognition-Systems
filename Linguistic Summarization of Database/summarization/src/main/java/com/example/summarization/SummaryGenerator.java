package com.example.summarization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private ArrayList<SimpleSummary> summariesWithMeasures = new ArrayList<>();


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
        if (kind == "single subject") {
            switch (type) {
                case 1:
                    generateSummariesSingleKindType1();
                    break;
                case 2:
                    generateSummariesSingleKindType2();
                    break;
            }
        }
        else {
            switch (type) {
                case 1:
                    generateSummariesMultipleKindType1();
                    break;
                case 2:
                    generateSummariesMultipleKindType2();
                    break;
                case 3:
                    generateSummariesMultipleKindType3();
                    break;
                case 4:
                    generateSummariesMultipleKindType4();
                    break;
            }
        }
    }

    public void generateSummariesSingleKindType1() {
        for (Quantifier quantifier : quantifiers) {
            int columnIndex = 0;
            for (LinguisticVariable linguisticVariable : summarizers) {
                for (Label label : linguisticVariable.getLabels()) {
                    double sum = 0.0;
                    for (Credit credit : credits) {
                        double value = getValueByColumnIndex(columnIndex, credit);
                        sum += label.getMembershipFunction().calculateMembershipDegree(value);
                    }
                    double totalMembership = sum / credits.size();
                    for (Label quantifierLabel : quantifier.getLabels()) {
                        double membershipDegree = quantifierLabel.getMembershipFunction().calculateMembershipDegree(totalMembership);
                        String summary = quantifierLabel.getName() + " " + subject1 + " are " + label.getName();
                        System.out.println(summary);

                        //calculateAndPrintQualityMeasures(summary, membershipDegree, totalMembership, 0, 0, linguisticVariable.getLabels().size(), summarizers.size(), quantifierLabel.getMembershipFunction().domainR - quantifierLabel.getMembershipFunction().domainL); // Adjust parameters as needed
                    }
                }
                columnIndex++;
            }
        }
    }

    public void generateSummariesSingleKindType2() {

    }

    public void generateSummariesMultipleKindType1() {

    }

    public void generateSummariesMultipleKindType2() {

    }

    public void generateSummariesMultipleKindType3() {

    }

    public void generateSummariesMultipleKindType4() {

    }

    public double getValueByColumnIndex(int columnIndex, Credit credit) {
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
