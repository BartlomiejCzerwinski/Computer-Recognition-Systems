package com.example.summarization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    private HashMap<CreditsType, ArrayList<Credit>> creditsByTypes;
    private ArrayList<Credit> credits;
    // DATA SECTION *********************************

    private QualityMeasuresCalculator qualityMeasuresCalculator;

    private ArrayList<CreditsType> creditsTypes = new ArrayList<>(Arrays.asList(
            CreditsType.CREDIT_CARD,
            CreditsType.CAR,
            CreditsType.SMALL_BUSINESS,
            CreditsType.DEBT_CONSOLIDATION,
            CreditsType.HOME_IMPROVEMENT,
            CreditsType.MAJOR_PURCHASE,
            CreditsType.MEDICAL
    ));


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
        this.creditsByTypes = databaseConnector.getAllCredits();
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
        credits = creditsByTypes.get(CreditsType.ALL);
        qualityMeasuresCalculator = new QualityMeasuresCalculator(credits);
        for (Quantifier quantifier : quantifiers) {
            for (LinguisticVariable summarizer : summarizers) {
                for (Label summarizerLabel : summarizer.getLabels()) {

                    double degreeOfTruth = qualityMeasuresCalculator.degreeOfTruth(quantifier, summarizerLabel, null, summarizerLabel.getColumnIndex());
                    double degreeOfImprecision = qualityMeasuresCalculator.degreeOfImprecision(summarizerLabel, summarizerLabel.getColumnIndex());
                    double degreeOfCovering = qualityMeasuresCalculator.degreeOfCovering(null, summarizerLabel, credits, summarizerLabel.getColumnIndex());
                    double degreeOfAppropriateness = qualityMeasuresCalculator.degreeOfAppropriateness(summarizer, credits, null, summarizerLabel.getColumnIndex());
                    double lengthOfSummary = qualityMeasuresCalculator.lengthOfSummary(new ArrayList<>(Arrays.asList(summarizerLabel)));
                    double degreeOfQuantifierImprecision = qualityMeasuresCalculator.degreeOfQuantifierImprecision(quantifier, credits);
                    double degreeOfQuantifierCardinality = qualityMeasuresCalculator.degreeOfQuantifierCardinality(quantifier, credits, summarizerLabel.getColumnIndex());
                    double degreeOfSummarizerCardinality = qualityMeasuresCalculator.degreeOfSummarizerCardinality(summarizerLabel, summarizerLabel.getColumnIndex());
                    double degreeOfQualifierImprecision = qualityMeasuresCalculator.degreeOfQualifierImprecision(null, 0);
                    double degreeOfQualifierCardinality = qualityMeasuresCalculator.degreeOfQualifierCardinality(null, 0);
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
            }
        }
    }

    public void generateSummariesSingleKindType2() {
        credits = creditsByTypes.get(CreditsType.ALL);
        qualityMeasuresCalculator = new QualityMeasuresCalculator(credits);
        for (Quantifier quantifier : quantifiers) {
            for (LinguisticVariable qualifier : qualifiers) {
                for (Label qualifierLabel : qualifier.getLabels()) {
                    for (LinguisticVariable summarizer : summarizers) {
                        for (Label summarizerLabel : summarizer.getLabels()) {

                                double degreeOfTruth = qualityMeasuresCalculator.degreeOfTruth(quantifier, summarizerLabel, qualifierLabel, summarizerLabel.getColumnIndex());
                                double degreeOfImprecision = qualityMeasuresCalculator.degreeOfImprecision(summarizerLabel, summarizerLabel.getColumnIndex());
                                double degreeOfCovering = qualityMeasuresCalculator.degreeOfCovering(qualifierLabel, summarizerLabel, credits, summarizerLabel.getColumnIndex());
                                double degreeOfAppropriateness = qualityMeasuresCalculator.degreeOfAppropriateness(summarizer, credits, qualifierLabel, qualifierLabel.getColumnIndex());
                                double lengthOfSummary = qualityMeasuresCalculator.lengthOfSummary(new ArrayList<>(Arrays.asList(summarizerLabel)));
                                double degreeOfQuantifierImprecision = qualityMeasuresCalculator.degreeOfQuantifierImprecision(quantifier, credits);
                                double degreeOfQuantifierCardinality = qualityMeasuresCalculator.degreeOfQuantifierCardinality(quantifier, credits, summarizerLabel.getColumnIndex());
                                double degreeOfSummarizerCardinality = qualityMeasuresCalculator.degreeOfSummarizerCardinality(summarizerLabel, summarizerLabel.getColumnIndex());
                                double degreeOfQualifierImprecision = qualityMeasuresCalculator.degreeOfQualifierImprecision(qualifierLabel, qualifierLabel.getColumnIndex());
                                double degreeOfQualifierCardinality = qualityMeasuresCalculator.degreeOfQualifierCardinality(qualifierLabel, qualifierLabel.getColumnIndex());
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
                                Summary summary = new Summary(kind, type, subject1, subject2, arr, quantifier.getLabel().getName(), qualifierLabel.getName(), summarizerLabel.getName(), "");
                                summaries.add(summary);

                        }
                    }
                }
            }
        }
    }

    public void generateSummariesMultipleKindType1() {
        for (CreditsType subject1 : creditsTypes) {
            ArrayList<Credit> credits1 = creditsByTypes.get(subject1);
            for (CreditsType subject2 : creditsTypes) {
                ArrayList<Credit> credits2 = creditsByTypes.get(subject2);
                if (subject1 != subject2)
                for (Quantifier quantifier : quantifiers) {
                    for (LinguisticVariable summarizer : summarizers) {
                        for (Label summarizerLabel : summarizer.getLabels()) {

                           double up = (1.0/credits1.size()) * summarizerLabel.getMembershipFunction().getAlphaCountNonFuzzy(credits1, summarizerLabel.getColumnIndex());
                           double down = (up + ((1.0/credits2.size()) * summarizerLabel.getMembershipFunction().getAlphaCountNonFuzzy(credits2, summarizerLabel.getColumnIndex())));
                           double T = quantifier.getLabel().getMembershipFunction().calculateMembershipDegree(up/down);
                           T = Math.round(T * 100.0) / 100.0;
                           ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0,
                                    T));

                            Summary summary = new Summary(kind, type, creditsTypeToString(subject1), creditsTypeToString(subject2), arr, quantifier.getLabel().getName(), null, summarizerLabel.getName(), "");
                            summaries.add(summary);
                        }
                    }

                }
            }
        }
    }

    public void generateSummariesMultipleKindType2() {
        for (CreditsType subject1 : creditsTypes) {
            ArrayList<Credit> credits1 = creditsByTypes.get(subject1);
            for (CreditsType subject2 : creditsTypes) {
                ArrayList<Credit> credits2 = creditsByTypes.get(subject2);
                if (subject1 != subject2)
                    for (Quantifier quantifier : quantifiers) {

                        for (LinguisticVariable qualifierVariable : qualifiers) {
                            for (Label qualifierLabel : qualifierVariable.getLabels()) {
                                for (LinguisticVariable summarizer : summarizers) {
                                    for (Label summarizerLabel : summarizer.getLabels()) {

                                        double up = (1.0/credits1.size()) * summarizerLabel.getMembershipFunction().getAlphaCountNonFuzzy(credits1, summarizerLabel.getColumnIndex());
                                        JoinLabels joinLabels = new JoinLabels(summarizerLabel, qualifierLabel, credits2, summarizerLabel.getColumnIndex());
                                        double down = (up + ((1.0/credits2.size()) * joinLabels.sum()));
                                        double T = quantifier.getLabel().getMembershipFunction().calculateMembershipDegree(up/down);
                                        T = Math.round(T * 100.0) / 100.0;
                                        ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                T));

                                        Summary summary = new Summary(kind, type, creditsTypeToString(subject1), creditsTypeToString(subject2), arr, quantifier.getLabel().getName(), qualifierLabel.getName(), summarizerLabel.getName(), "");
                                        summaries.add(summary);
                                    }
                                }
                            }
                        }

                    }
            }
        }
    }

    public void generateSummariesMultipleKindType3() {
        for (CreditsType subject1 : creditsTypes) {
            ArrayList<Credit> credits1 = creditsByTypes.get(subject1);
            for (CreditsType subject2 : creditsTypes) {
                ArrayList<Credit> credits2 = creditsByTypes.get(subject2);

                if (subject1 != subject2)
                    for (Quantifier quantifier : quantifiers) {

                        for (LinguisticVariable qualifierVariable : qualifiers) {
                            for (Label qualifierLabel : qualifierVariable.getLabels()) {
                                for (LinguisticVariable summarizer : summarizers) {
                                    for (Label summarizerLabel : summarizer.getLabels()) {

                                        double up = ((1.0/credits1.size()) * new JoinLabels(summarizerLabel, qualifierLabel, credits1, summarizerLabel.getColumnIndex()).sum());
                                        double down = (up + ((1.0/credits2.size()) * summarizerLabel.getMembershipFunction().getAlphaCountNonFuzzy(credits2, summarizerLabel.getColumnIndex())));
                                        double T = quantifier.getLabel().getMembershipFunction().calculateMembershipDegree(up/down);
                                        T = Math.round(T * 100.0) / 100.0;
                                        ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                0.0,
                                                T));

                                        Summary summary = new Summary(kind, type, creditsTypeToString(subject1), creditsTypeToString(subject2), arr, quantifier.getLabel().getName(), qualifierLabel.getName(), summarizerLabel.getName(), "");
                                        summaries.add(summary);
                                    }
                                }
                            }
                        }

                    }
            }
        }
    }

    public void generateSummariesMultipleKindType4() {
        for (CreditsType subject1 : creditsTypes) {
            ArrayList<Credit> credits1 = creditsByTypes.get(subject1);
            for (CreditsType subject2 : creditsTypes) {
                ArrayList<Credit> credits2 = creditsByTypes.get(subject2);
                if (subject1 != subject2)
                {
                    for (LinguisticVariable summarizer : summarizers) {
                        for (Label summarizerLabel : summarizer.getLabels()) {
                            double sum = 0.0;
                            for (int i = 0; i < credits1.size(); i++) {
                                double a = summarizerLabel.getMembershipFunction().calculateMembershipDegree(credits2.get(i).getValueByColumnIndex(summarizerLabel.getColumnIndex()));
                                double b = summarizerLabel.getMembershipFunction().calculateMembershipDegree(credits1.get(i).getValueByColumnIndex(summarizerLabel.getColumnIndex()));
                                sum += Math.min(a, b);
                            }
                            sum /= summarizerLabel.getMembershipFunction().getAlphaCount(credits2, summarizerLabel.getColumnIndex());
                            double T = 1 - (sum / summarizerLabel.getMembershipFunction().getAlphaCount(credits2, summarizerLabel.getColumnIndex()));
                            T = Math.round(T * 100.0) / 100.0;
                            ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, T));
                            Summary summary = new Summary(kind, type, creditsTypeToString(subject1), creditsTypeToString(subject2), arr, null, null, summarizerLabel.getName(), "");
                            summaries.add(summary);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Summary> getSummaries() {
        return summaries;
    }

    public void getCreditsPurposeInfo() {

            System.out.println("CREDITS BY PURPOSE");
            System.out.println("Credit Card: " + creditsByTypes.get(CreditsType.CREDIT_CARD).size());
            System.out.println("Car: " + creditsByTypes.get(CreditsType.CAR).size());
            System.out.println("Small Business: " + creditsByTypes.get(CreditsType.SMALL_BUSINESS).size());
            System.out.println("Debt Consolidation: " + creditsByTypes.get(CreditsType.DEBT_CONSOLIDATION).size());
            System.out.println("Home Improvement: " + creditsByTypes.get(CreditsType.HOME_IMPROVEMENT).size());
            System.out.println("Major Purchase: " + creditsByTypes.get(CreditsType.MAJOR_PURCHASE).size());
            System.out.println("Medical: " + creditsByTypes.get(CreditsType.MEDICAL).size());
            System.out.println("All: " + creditsByTypes.get(CreditsType.ALL).size());
    }

    public String creditsTypeToString(CreditsType creditsType) {
        switch (creditsType) {
            case CREDIT_CARD -> {
                return "credit card";
            }
            case CAR -> {
                return "car";
            }
            case SMALL_BUSINESS -> {
                return "small business";
            }
            case DEBT_CONSOLIDATION -> {
                return "debt consolidation";
            }
            case HOME_IMPROVEMENT -> {
                return "home improvement";
            }
            case MAJOR_PURCHASE -> {
                return "major purchase";
            }
            case MEDICAL -> {
                return "medical";
            }
        }
        return null;
    }

}
