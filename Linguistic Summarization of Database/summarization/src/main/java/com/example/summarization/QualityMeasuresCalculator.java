package com.example.summarization;

import java.util.ArrayList;
import java.util.Arrays;

public class QualityMeasuresCalculator {
    private ArrayList<Credit> credits;


    // T1
    public double degreeOfTruth(ArrayList<Label> labels, int columIndex) {
        double totalResult = 0.0;
        for (Label label : labels) {
            double sum = 0.0;
            for (Credit credit : credits) {
                sum += label.getMembershipFunction().calculateMembershipDegree(getValueByColumnIndex(columIndex, credit));
            }
            sum /= credits.size();
            totalResult += sum;
        }
        double result = totalResult / labels.size();
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T2 ???
    public double degreeOfImprecision(LinguisticVariable summarizer, int columIndex) {
        double quotient = 1.0;
        for (Label label : summarizer.getLabels()) {
            double degreeOfFuzniess = label.getSupport(credits, columIndex).size() / (double)credits.size();
            quotient *= degreeOfFuzniess;
        }
        double result = 1.0 - Math.pow(quotient, 1.0 / summarizer.getLabels().size());
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T3 !!!
    public double degreeOfCovering() {
        return 0.0;
    }

    // T4 !!!
    public double degreeOfAppropriateness(ArrayList<Credit> credits, LinguisticVariable summarizer, int columIndex) {
        double quotient = 1.0;
        double t3 = degreeOfCovering();
        for (Label label : summarizer.getLabels()) {
            double sum = 0.0;
            for (Credit credit : credits) {
                sum += label.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex));
            }
            quotient *= sum/credits.size();
        }
        return Math.abs(quotient - t3);
    }

    // T5
    public double lengthOfSummary(ArrayList<LinguisticVariable> summarizers) {
        System.out.println("SUMMARIZERS SIZE: " + summarizers.size());
        return 2 * Math.pow(1.0 / 2.0, summarizers.size());
    }

    public QualityMeasuresCalculator(ArrayList<Credit> credits) {
        this.credits = credits;
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
