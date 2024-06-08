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
    private ArrayList<Summary> summaries = new ArrayList<>();
    private ArrayList<Credit> credits;
    // DATA SECTION *********************************

    private QualityMeasuresCalculator qualityMeasuresCalculator;


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
        this.qualityMeasuresCalculator = new QualityMeasuresCalculator(credits);
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
            for (LinguisticVariable summarizer : summarizers) {
                for (Label summarizerLabel : summarizer.getLabels()) {

                    double degreeOfTruth = qualityMeasuresCalculator.degreeOfTruth(quantifier, summarizerLabel, null, columnIndex);
                    double degreeOfImprecision = qualityMeasuresCalculator.degreeOfImprecision(summarizer, columnIndex);
                    double degreeOfCovering = qualityMeasuresCalculator.degreeOfCovering(null, summarizerLabel, credits, columnIndex);
                    double degreeOfAppropriateness = qualityMeasuresCalculator.degreeOfAppropriateness(summarizer, credits, null, columnIndex);
                    double lengthOfSummary = qualityMeasuresCalculator.lengthOfSummary(new ArrayList<>(Arrays.asList(summarizerLabel)));
                    double degreeOfQuantifierImprecision = qualityMeasuresCalculator.degreeOfQuantifierImprecision(quantifier, credits, columnIndex, quantifier.isAbsolute());
                    double degreeOfQuantifierCardinality = qualityMeasuresCalculator.degreeOfQuantifierCardinality(quantifier.getLabel(), credits, columnIndex, quantifier.isAbsolute());
                    double degreeOfSummarizerCardinality = qualityMeasuresCalculator.degreeOfSummarizerCardinality(summarizers.get(0), credits, columnIndex);
                    double degreeOfQualifierImprecision = qualityMeasuresCalculator.degreeOfQualifierImprecision(null, null, 0);
                    double degreeOfQualifierCardinality = qualityMeasuresCalculator.degreeOfQualifierCardinality(null, null, 0);
                    double lengthOfQualifier = qualityMeasuresCalculator.lengthOfQualifier(null);

                    double T = (
                            (degreeOfTruth * measuresWeights.get(0))
                                + (degreeOfImprecision * measuresWeights.get(1))
                                + (degreeOfCovering * measuresWeights.get(2))
                                + (degreeOfAppropriateness * measuresWeights.get(3))
                                + (lengthOfSummary * measuresWeights.get(4))
                                + (degreeOfQuantifierImprecision * measuresWeights.get(5))
                                + (degreeOfQuantifierCardinality * measuresWeights.get(6))
                                + (degreeOfSummarizerCardinality * measuresWeights.get(7))
                                + (degreeOfQualifierImprecision * measuresWeights.get(8))
                                + (degreeOfQualifierCardinality * measuresWeights.get(9))
                                + (lengthOfQualifier * measuresWeights.get(10))
                            );
                    T = Math.round(T * 100.0) / 100.0;


                    ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(
                            degreeOfTruth,
                            degreeOfImprecision,
                            degreeOfCovering,
                            degreeOfAppropriateness,
                            lengthOfSummary,
                            degreeOfQuantifierImprecision,
                            degreeOfQuantifierCardinality,
                            degreeOfSummarizerCardinality,
                            degreeOfQualifierImprecision,
                            degreeOfQualifierCardinality,
                            lengthOfQualifier,
                            T));
                    Summary summary = new Summary(kind, type, subject1, subject2, arr, quantifier.getLabel().getName(), "", summarizerLabel.getName(), "");
                    summaries.add(summary);

                }
                columnIndex++;
            }
        }
    }

    public void generateSummariesSingleKindType2() {
        for (Quantifier quantifier : quantifiers) {
            int columnIndex = 0;
            for (LinguisticVariable qualifier : qualifiers) {
                for (Label qualifierLabel : qualifier.getLabels()) {
                    for (LinguisticVariable summarizer : summarizers) {
                        for (Label labelSummarizer : summarizer.getLabels()) {

                                double degreeOfTruth = qualityMeasuresCalculator.degreeOfTruth(quantifier, labelSummarizer, qualifierLabel, columnIndex);
                                double degreeOfImprecision = qualityMeasuresCalculator.degreeOfImprecision(summarizer, columnIndex);
                                double degreeOfCovering = qualityMeasuresCalculator.degreeOfCovering(qualifierLabel, labelSummarizer, credits, columnIndex);
                                double degreeOfAppropriateness = qualityMeasuresCalculator.degreeOfAppropriateness(summarizer, credits, qualifierLabel, columnIndex);
                                double lengthOfSummary = qualityMeasuresCalculator.lengthOfSummary(new ArrayList<>(Arrays.asList(labelSummarizer)));
                                double degreeOfQuantifierImprecision = qualityMeasuresCalculator.degreeOfQuantifierImprecision(quantifier, credits, columnIndex, quantifier.isAbsolute());
                                double degreeOfQuantifierCardinality = qualityMeasuresCalculator.degreeOfQuantifierCardinality(quantifier.getLabel(), credits, columnIndex, quantifier.isAbsolute());
                                double degreeOfSummarizerCardinality = qualityMeasuresCalculator.degreeOfSummarizerCardinality(summarizers.get(0), credits, columnIndex);
                                double degreeOfQualifierImprecision = qualityMeasuresCalculator.degreeOfQualifierImprecision(qualifierLabel, credits, columnIndex);
                                double degreeOfQualifierCardinality = qualityMeasuresCalculator.degreeOfQualifierCardinality(qualifierLabel, credits, columnIndex);
                                double lengthOfQualifier = qualityMeasuresCalculator.lengthOfQualifier(null);

                                double T = (
                                        (degreeOfTruth * measuresWeights.get(0))
                                                + (degreeOfImprecision * measuresWeights.get(1))
                                                + (degreeOfCovering * measuresWeights.get(2))
                                                + (degreeOfAppropriateness * measuresWeights.get(3))
                                                + (lengthOfSummary * measuresWeights.get(4))
                                                + (degreeOfQuantifierImprecision * measuresWeights.get(5))
                                                + (degreeOfQuantifierCardinality * measuresWeights.get(6))
                                                + (degreeOfSummarizerCardinality * measuresWeights.get(7))
                                                + (degreeOfQualifierImprecision * measuresWeights.get(8))
                                                + (degreeOfQualifierCardinality * measuresWeights.get(9))
                                                + (lengthOfQualifier * measuresWeights.get(10))
                                );
                                T = Math.round(T * 100.0) / 100.0;


                                ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(
                                        degreeOfTruth,
                                        degreeOfImprecision,
                                        degreeOfCovering,
                                        degreeOfAppropriateness,
                                        lengthOfSummary,
                                        degreeOfQuantifierImprecision,
                                        degreeOfQuantifierCardinality,
                                        degreeOfSummarizerCardinality,
                                        degreeOfQualifierImprecision,
                                        degreeOfQualifierCardinality,
                                        lengthOfQualifier,
                                        T));
                                Summary summary = new Summary(kind, type, subject1, subject2, arr, quantifier.getLabel().getName(), qualifierLabel.getName(), labelSummarizer.getName(), "");
                                summaries.add(summary);

                        }
                        columnIndex++;
                    }
                }
            }
        }
    }

    public void generateSummariesMultipleKindType1() {

    }

    public void generateSummariesMultipleKindType2() {

    }

    public void generateSummariesMultipleKindType3() {

    }

    public void generateSummariesMultipleKindType4() {

    }

    public ArrayList<Summary> getSummaries() {
        return summaries;
    }

    public void getCreditsPurposeInfo() {
        int creditCard = 0;
        int car = 0;
        int smallBusiness = 0;
        int other = 0;
        int wedding = 0;
        int debtConsolidation = 0;
        int homeImprovement = 0;
        int majorPurchase = 0;
        int medical = 0;
        int vacation = 0;
        int buyingHouse = 0;

        for (Credit credit : credits) {
            String creditPurpose = credit.getPurpose();
            switch (creditPurpose) {
                case "credit_card":
                    creditCard++;
                    break;
                case "car":
                    car++;
                    break;
                case "small_business":
                    smallBusiness++;
                    break;
                case "other":
                    other++;
                    break;
                case "wedding":
                    wedding++;
                    break;
                case "debt_consolidation":
                    debtConsolidation++;
                    break;
                case "home_improvement":
                    homeImprovement++;
                    break;
                case "major_purchase":
                    majorPurchase++;
                    break;
                case "medical":
                    medical++;
                    break;
                case "vacation":
                    vacation++;
                    break;
                case "house":
                    buyingHouse++;
                    break;
            }
        }
        System.out.println("CREDITS BY PURPOSE");
        System.out.println("Credit Card: " + creditCard);
        System.out.println("Car: " + car);
        System.out.println("Small Business: " + smallBusiness);
        System.out.println("Other: " + other);
        System.out.println("Wedding: " + wedding);
        System.out.println("Debt Consolidation: " + debtConsolidation);
        System.out.println("Home Improvement: " + homeImprovement);
        System.out.println("Major Purchase: " + majorPurchase);
        System.out.println("Medical: " + medical);
        System.out.println("Vacation: " + vacation);
        System.out.println("Buying House: " + buyingHouse);
    }
}
