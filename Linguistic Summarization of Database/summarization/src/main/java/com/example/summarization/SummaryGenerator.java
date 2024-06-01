package com.example.summarization;

import java.util.ArrayList;
import java.util.Arrays;

public class SummaryGenerator {


    // GENERATOR SECTION ****************************
    private String kind;
    private int type;
    private ArrayList<Quantifier> quantifiers;
    private ArrayList<LinguisticVariable> qualifiers;
    private ArrayList<LinguisticVariable> summarizers;
    private String subject1;
    private String subject2;
    private ArrayList<Double> measuresWeights;
    // GENERATOR SECTION ****************************


    // WHAT TO RETURN SECTION ***********************
    private String quantifiersToReturn;
    private String qualifiersToReturn;
    private String summarizersToReturn;
    // WHAT TO RETURN SECTION ***********************


    // DATA SECTION *********************************
    private ArrayList<Summary> summaries;
    private ArrayList<Credit> credits;
    // DATA SECTION *********************************


    public SummaryGenerator(String kind, int type, ArrayList<Quantifier> quantifiers, ArrayList<LinguisticVariable> qualifiers, ArrayList<LinguisticVariable> summarizers, String subject1, String subject2, ArrayList<Double> measuresWeights, String quantifiersToReturn, String qualifiersToReturn, String summarizersToReturn) {
        this.kind = kind;
        this.type = type;
        this.quantifiers = quantifiers;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.measuresWeights = measuresWeights;
        this.quantifiersToReturn = quantifiersToReturn;
        this.qualifiersToReturn = qualifiersToReturn;
        this.summarizersToReturn = summarizersToReturn;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.credits = databaseConnector.fetchData();
    }

    public void generateSummaries() {
        if (kind == "single subject") {
            switch (type) {
                case 1:
                    System.out.println("11111");
                    generateSummariesSingleKindType1();
                    break;
                case 2:
                    System.out.println("222222");
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
                        //String summary = quantifierLabel.getName() + " " + subject1 + " are " + label.getName();
                        //System.out.println(summary);
                        ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0));
                        Summary summary = new Summary(kind, type, subject1, subject2, arr, quantifierLabel.getName(), "", label.getName(), "");
                        summaries.add(summary);
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

    public ArrayList<Summary> getSummaries() {
        return summaries;
    }
}
