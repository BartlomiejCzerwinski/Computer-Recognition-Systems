package com.example.summarization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QualityMeasuresCalculator {
    private ArrayList<Credit> credits;


    // T1
    public double degreeOfTruth(Quantifier quantifier, Label summarizer, Label qualifier, int columIndex) {
        double rUp = 0.0;
        double rDown = 0.0;
        for (Credit credit : credits) {
            if (qualifier == null) {
                rUp += summarizer.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex));
            }
            else {
                rUp += Math.min(summarizer.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex)),
                        qualifier.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex)));
                rDown += qualifier.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex));
            }
        }

        double result = 0.0;
        if (qualifier == null)
            result = rUp;
        else
            result = rUp/rDown;
        if (quantifier.isAbsolute())
            result = quantifier.getLabel().getMembershipFunction().calculateMembershipDegree(result);
        else
            result = quantifier.getLabel().getMembershipFunction().calculateMembershipDegree(result/(double)credits.size());

        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T2
    public double degreeOfImprecision(LinguisticVariable summarizer, int columIndex) {
        double quotient = 1.0;
        for (Label label : summarizer.getLabels()) {
            double degreeOfFuzziness = label.getSupport(credits, columIndex) / (double) credits.size();
            quotient *= degreeOfFuzziness;
        }
        double result = 1.0 - Math.pow(quotient, 1.0 / summarizer.getLabels().size());
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T3
    public double degreeOfCovering(Label qualifier, Label summarizer, ArrayList<Credit> credits, int columnIndex) {
        double t = 0.0;
        double h = 0.0;
        if (qualifier == null) {
            for (Credit credit : credits) {
                if (summarizer.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) > 0) {
                    t++;
                }
                h++;
            }
            double result = t / h;
            result = Math.round(result * 100.0) / 100.0;
            return result;
        }

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

    // T4 //TODO
    public double degreeOfAppropriateness(LinguisticVariable summarizer, ArrayList<Credit> credits, Label qualifier, int columIndex) {
        double quotient = 1.0;
        for (Label label : summarizer.getLabels()) {
            double sum = label.getSupport(credits, columIndex) / (double) credits.size();
            double t3 = degreeOfCovering(qualifier, label, credits, columIndex);
            quotient *= (sum - t3);
        }
        double result = Math.abs(quotient);
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T5
    public double lengthOfSummary(ArrayList<Label> summarizers) {
        return 2 * Math.pow(1.0 / 2.0, summarizers.size());
    }

    //T6
    public double degreeOfQuantifierImprecision(Quantifier quantifier, ArrayList<Credit> credits, int columnIndex, boolean isAbsolute) {
        double result = quantifier.getLabel().getMembershipFunction().domainR - quantifier.getLabel().getMembershipFunction().domainL;
        if (quantifier.isAbsolute())
            result /= (double) credits.size();
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
    public double degreeOfQualifierImprecision(Label qualifier, ArrayList<Credit> credits, int columnIndex) {
        if (qualifier == null) {
            return 0.0;
        }
        double degreeOfFuzziness = qualifier.getSupport(credits, columnIndex) / (double)credits.size();
        double result = 1.0 - degreeOfFuzziness;
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    //T10 !!! - forma 2
    public double degreeOfQualifierCardinality(Label qualifier, ArrayList<Credit> credits, int columnIndex) {
        if (qualifier == null) {
            return 0.0;
        }
        double result = 1.0 - (qualifier.getCardinality(credits, columnIndex) / credits.size());
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    //T11 !!!
    public double lengthOfQualifier(LinguisticVariable qualifier) {
        return 1.0;
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
