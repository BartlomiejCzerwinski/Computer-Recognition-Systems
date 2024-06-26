package com.example.summarization;

import java.util.ArrayList;

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
        public double degreeOfImprecision(Label summarizer, int columIndex, Label qualifier) {
        double result = 0;
        if (qualifier == null) {
            result = summarizer.getSupport(credits, columIndex) / (double) credits.size();
            result = Math.round(result * 100.0) / 100.0;
            return result;
        } else {
            double supp = 0;
            double qualifierSetSize = 0;
            for(Credit credit: credits) {
                if (qualifier.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex)) > 0) {
                    qualifierSetSize ++;
                    if (summarizer.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columIndex)) > 0) {
                        supp++;
                    }
                }
            }
            result = 1 - ((double) supp / (double) qualifierSetSize);
            result = Math.round(result * 100.0) / 100.0;
            return result;
        }
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

    // T6
    public double degreeOfQuantifierImprecision(Quantifier quantifier, ArrayList<Credit> credits) {
        double result = quantifier.getLabel().getMembershipFunction().domainR - quantifier.getLabel().getMembershipFunction().domainL;
        if (quantifier.isAbsolute())
            result /= (double) credits.size();
        result = 1 - result;
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    // T7
    public double degreeOfQuantifierCardinality(Quantifier quantifier, ArrayList<Credit> credits, int columnIndex) {
        double result = quantifier.getLabel().getMembershipFunction().getCardinality(credits, columnIndex);
        if (quantifier.isAbsolute())
            result =  result / (double) credits.size();
        result = 1 - result;
        result = Math.round(result * 100.0) / 100.0;
        return result;
    }

    //T8 //TODO
    public double degreeOfSummarizerCardinality(Label summarizerLabel, int columnIndex) {
        double cardinality = summarizerLabel.getMembershipFunction().getCardinality(credits, columnIndex);
        double normalizedCardinality = cardinality / credits.size();

        double result = 1 - normalizedCardinality;

        result = Math.round(result * 100.0) / 100.0;

        return result < 0 ? 0 : result;
    }

    //T9
    public double degreeOfQualifierImprecision(Label qualifier, int columnIndex) {
        if(qualifier == null)
            return 0.0;
        double result = qualifier.getSupport(credits, columnIndex) / (double) credits.size();
        result = 1 - (Math.round(result * 100.0) / 100.0);
        return result;
    }

    //T10 !!! - forma 2
    public double degreeOfQualifierCardinality(Label qualifier, int columnIndex) {
        if (qualifier == null)
            return 0.0;
        double cardinality = qualifier.getMembershipFunction().getCardinality(credits, columnIndex);
        double normalizedCardinality = cardinality / credits.size();

        double result = 1 - normalizedCardinality;

        result = Math.round(result * 100.0) / 100.0;

        return result < 0 ? 0 : result;
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
