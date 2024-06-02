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
            for (LinguisticVariable linguisticVariable : summarizers) {
                for (Label label : linguisticVariable.getLabels()) {
                    for (Label quantifierLabel : quantifier.getLabels()) {

                        double degreeOfTruth = qualityMeasuresCalculator.degreeOfTruth(new ArrayList<>(Arrays.asList(quantifierLabel, label)), columnIndex);
                        double degreeOfImprecision = qualityMeasuresCalculator.degreeOfImprecision(linguisticVariable, columnIndex);
                        double degreeOfCovering = qualityMeasuresCalculator.degreeOfCovering(null, label, credits, columnIndex);
                        double degreeOfAppropriateness = qualityMeasuresCalculator.degreeOfAppropriateness(credits, linguisticVariable, null, columnIndex);
                        double lengthOfSummary = qualityMeasuresCalculator.lengthOfSummary(new ArrayList<>(Arrays.asList(linguisticVariable)));
                        double degreeOfQuantifierImprecision = qualityMeasuresCalculator.degreeOfQuantifierImprecision(quantifierLabel, credits, columnIndex, quantifier.isAbsolute());
                        double degreeOfQuantifierCardinality = qualityMeasuresCalculator.degreeOfQuantifierCardinality(quantifierLabel, credits, columnIndex, quantifier.isAbsolute());
                        double degreeOfSummarizerCardinality = qualityMeasuresCalculator.degreeOfSummarizerCardinality(summarizers.get(0), credits, columnIndex);
                        double degreeOfQualifierImprecision = qualityMeasuresCalculator.degreeOfQualifierImprecision(null);
                        double degreeOfQualifierCardinality = qualityMeasuresCalculator.degreeOfQualifierCardinality(null);
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
                        Summary summary = new Summary(kind, type, subject1, subject2, arr, quantifierLabel.getName(), "", label.getName(), "");
                        summaries.add(summary);
                    }
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
                    for (LinguisticVariable linguisticVariable : summarizers) {
                        for (Label labelSummarizer : linguisticVariable.getLabels()) {
                            for (Label quantifierLabel : quantifier.getLabels()) {

                                double degreeOfTruth = qualityMeasuresCalculator.degreeOfTruth(new ArrayList<>(Arrays.asList(quantifierLabel, labelSummarizer)), columnIndex);
                                double degreeOfImprecision = qualityMeasuresCalculator.degreeOfImprecision(linguisticVariable, columnIndex);
                                double degreeOfCovering = qualityMeasuresCalculator.degreeOfCovering(qualifierLabel, labelSummarizer, credits, columnIndex);
                                double degreeOfAppropriateness = qualityMeasuresCalculator.degreeOfAppropriateness(credits, linguisticVariable, qualifierLabel, columnIndex);
                                double lengthOfSummary = qualityMeasuresCalculator.lengthOfSummary(new ArrayList<>(Arrays.asList(linguisticVariable)));
                                double degreeOfQuantifierImprecision = qualityMeasuresCalculator.degreeOfQuantifierImprecision(quantifierLabel, credits, columnIndex, quantifier.isAbsolute());
                                double degreeOfQuantifierCardinality = qualityMeasuresCalculator.degreeOfQuantifierCardinality(quantifierLabel, credits, columnIndex, quantifier.isAbsolute());
                                double degreeOfSummarizerCardinality = qualityMeasuresCalculator.degreeOfSummarizerCardinality(summarizers.get(0), credits, columnIndex);
                                double degreeOfQualifierImprecision = qualityMeasuresCalculator.degreeOfQualifierImprecision(null);
                                double degreeOfQualifierCardinality = qualityMeasuresCalculator.degreeOfQualifierCardinality(null);
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
                                Summary summary = new Summary(kind, type, subject1, subject2, arr, quantifierLabel.getName(), qualifierLabel.getName(), labelSummarizer.getName(), "");
                                summaries.add(summary);
                            }
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
}
