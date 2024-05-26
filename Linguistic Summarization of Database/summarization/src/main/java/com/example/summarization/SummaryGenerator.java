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
        if (type == 1) {
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

                            calculateAndPrintQualityMeasures(summary, membershipDegree, totalMembership, 0, 0, linguisticVariable.getLabels().size(), summarizers.size(), quantifierLabel.getMembershipFunction().domainR - quantifierLabel.getMembershipFunction().domainL); // Adjust parameters as needed
                        }
                    }
                    columnIndex++;
                }
            }
        } else if (type == 2) {
            for (Quantifier quantifier : quantifiers) {
                int columnIndexSummarizer = 0;
                for (LinguisticVariable summarizer : summarizers) {
                    for (Label summarizerLabel : summarizer.getLabels()) {
                        double sumSummarizer = 0.0;
                        for (Credit credit : credits) {
                            double valueSummarizer = getValueByColumnIndex(columnIndexSummarizer, credit);
                            sumSummarizer += summarizerLabel.getMembershipFunction().calculateMembershipDegree(valueSummarizer);
                        }
                        double totalMembershipSummarizer = sumSummarizer / credits.size();
                        for (LinguisticVariable qualifier : summarizers) {
                            int columnIndexQualifier = 0;
                            for (Label qualifierLabel : qualifier.getLabels()) {
                                double sumQualifier = 0.0;
                                for (Credit credit : credits) {
                                    double valueQualifier = getValueByColumnIndex(columnIndexQualifier, credit);
                                    sumQualifier += qualifierLabel.getMembershipFunction().calculateMembershipDegree(valueQualifier);
                                }
                                double totalMembershipQualifier = sumQualifier / credits.size();
                                for (Label quantifierLabel : quantifier.getLabels()) {
                                    double membershipDegree = quantifierLabel.getMembershipFunction().calculateMembershipDegree(totalMembershipSummarizer);
                                    String summary = quantifierLabel.getName() + " " + qualifierLabel.getName() + " are " + summarizerLabel.getName();

                                    calculateAndPrintQualityMeasures(summary, membershipDegree, totalMembershipSummarizer, totalMembershipQualifier, credits.size(), summarizer.getLabels().size(), summarizers.size(), quantifierLabel.getMembershipFunction().domainR - quantifierLabel.getMembershipFunction().domainL);
                                }
                                columnIndexQualifier++;
                            }
                        }
                        columnIndexSummarizer++;
                    }
                }
            }
        }

        printFirstXSummaries(300);
    }

    public void printFirstXSummaries(int x) {
        Collections.sort(summariesWithMeasures);
        for (int i = 0; i < x; i ++) {
            System.out.println(summariesWithMeasures.get(summariesWithMeasures.size() - i - 1));
        }
    }

    private void calculateAndPrintQualityMeasures(String sentence, double membershipDegreeQuantifier, double totalMembershipSummarizer, double totalMembershipQualifier, int totalCredits, int numberOfLabels, int numberOfSummarizers, double quantifierRange) {
        double T1 = calculateQualityMeasureT1(membershipDegreeQuantifier, totalCredits);
        double T2 = calculateQualityMeasureT2(totalMembershipSummarizer);
        double T3 = calculateQualityMeasureT3(totalMembershipSummarizer);
        double T4 = calculateQualityMeasureT4(membershipDegreeQuantifier, totalMembershipSummarizer);
        double T5 = calculateQualityMeasureT5(totalMembershipSummarizer, totalMembershipQualifier);
        double T6 = calculateQualityMeasureT6(numberOfLabels);
        double T7 = calculateQualityMeasureT7(numberOfSummarizers);
        double T8 = calculateQualityMeasureT8(quantifierRange);
        double T9 = calculateQualityMeasureT9(numberOfSummarizers);
        double T10 = calculateQualityMeasureT10(quantifierRange);
        double T11 = calculateQualityMeasureT11(totalMembershipSummarizer);
        summariesWithMeasures.add(new SimpleSummary(sentence, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11));
    }


    private double calculateQualityMeasureT1(double membershipDegree, int totalCredits) {
        return Math.min(membershipDegree, 1.0);
    }

    private double calculateQualityMeasureT2(double totalMembershipSummarizer) {
        return Math.min(totalMembershipSummarizer, 1.0);
    }

    private double calculateQualityMeasureT3(double totalMembershipSummarizer) {
        return Math.min(totalMembershipSummarizer, 1.0);
    }

    private double calculateQualityMeasureT4(double membershipDegreeQuantifier, double totalMembershipSummarizer) {
        if (totalMembershipSummarizer <= 0.01) {
            return 0.0;
        }
        return Math.min(membershipDegreeQuantifier / totalMembershipSummarizer, 1.0);
    }

    private double calculateQualityMeasureT5(double totalMembershipSummarizer, double totalMembershipQualifier) {
        if (totalMembershipQualifier == 0) {
            return 0.0;
        }
        return Math.min(totalMembershipSummarizer / totalMembershipQualifier, 1.0);
    }

    private double calculateQualityMeasureT6(int numberOfLabels) {
        return Math.min(1.0 / numberOfLabels, 1.0);
    }

    private double calculateQualityMeasureT7(int numberOfSummarizers) {
        return Math.min(1.0 / numberOfSummarizers, 1.0);
    }

    private double calculateQualityMeasureT8(double quantifierRange) {
        return Math.min(1.0 / quantifierRange, 1.0);
    }

    private double calculateQualityMeasureT9(int numberOfQualifiers) {
        return Math.min(1.0 / numberOfQualifiers, 1.0);
    }

    private double calculateQualityMeasureT10(double quantifierRange) {
        return Math.min(1.0 / quantifierRange, 1.0);
    }

    private double calculateQualityMeasureT11(double totalMembershipSummarizer) {
        return Math.min(totalMembershipSummarizer, 1.0);
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
