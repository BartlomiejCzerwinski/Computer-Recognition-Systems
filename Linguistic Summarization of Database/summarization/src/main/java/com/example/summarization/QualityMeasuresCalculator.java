package com.example.summarization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public double degreeOfCovering(Label qualifier, Label summarizer, ArrayList<Credit> credits, int columnIndex) {
        double t = 0.0;
        double h = 0.0;
        if (qualifier == null)
            return 0.0;

        for (Credit credit : credits) {
            if (qualifier.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) > 0) {
                h++;
                if (summarizer.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) > 0) {
                    t++;
                }
            }
        }
        double result = t / h;
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T4 !!!
    public double degreeOfAppropriateness(ArrayList<Credit> credits, LinguisticVariable summarizer, Label qualifier, int columIndex) {
        double quotient = 1.0;
        double t3 = 0.0;
        for (Label label : summarizer.getLabels()) {
            double sum = 0.0;
            for (Credit credit : credits) {
                sum += label.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex));
            }
            quotient *= sum/credits.size();
        }
        double result = Math.abs(quotient - t3);
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T5
    public double lengthOfSummary(ArrayList<LinguisticVariable> summarizers) {
        return 2 * Math.pow(1.0 / 2.0, summarizers.size());
    }

    //T6 !!!
    public double degreeOfQuantifierImprecision(Label quantifier, ArrayList<Credit> credits, int columnIndex, boolean isAbsolute) {
        double result = 1.0 - quantifier.getHeight(credits, columnIndex);
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    //T7 !!!
    public double degreeOfQuantifierCardinality(Label quantifier, ArrayList<Credit> credits, int columnIndex, boolean isAbsolute) {
        double result = quantifier.getCardinality(credits, columnIndex);
        result /= (double) credits.size();
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    //T8 !!!
    public double degreeOfSummarizerCardinality(LinguisticVariable summarizer, ArrayList<Credit> credits, int columnIndex) {
        double quotient = 1.0;
        for (Label label : summarizer.getLabels())
            quotient *= (label.getCardinality(credits, columnIndex) / credits.size());
        double result = 1 - Math.pow(quotient, 1.0 / summarizer.getLabels().size());
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    //T9 !!! - forma 2
    public double degreeOfQualifierImprecision(LinguisticVariable qualifier) {
        if (qualifier == null) {
            return 0.0;
        }
        return -1;
    }

    //T10 !!! - forma 2
    public double degreeOfQualifierCardinality(LinguisticVariable qualifier) {
        if (qualifier == null) {
            return 0.0;
        }
        return -1;
    }

    //T11 !!!
    public double lengthOfQualifier(LinguisticVariable qualifier) {
        if (qualifier == null) {
            return 1.0;
        }
        double result = 2 * Math.pow(1.0 / 2.0, qualifier.getLabels().size());
        result = Math.round(result * 100.0) / 100.0;
        return result;
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
