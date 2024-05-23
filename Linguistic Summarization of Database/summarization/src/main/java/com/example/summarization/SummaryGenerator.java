package com.example.summarization;

import java.util.ArrayList;

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
    private ArrayList<String> summarizers;

    public void generateSummaries() {
        return;
    }

    public void sortSummaries(int measureID) {
        return;
    }

    public SummaryGenerator(String kind, int type, ArrayList<Quantifier> quantifiers, String subject1, String subject2, ArrayList<LinguisticVariable> linguisticVariables, ArrayList<Summary> summaries, ArrayList<Double> measuresWeights, ArrayList<String> qualifiers, ArrayList<String> summarizers) {
        this.kind = kind;
        this.type = type;
        this.quantifiers = quantifiers;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.linguisticVariables = linguisticVariables;
        this.summaries = summaries;
        this.measuresWeights = measuresWeights;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
    }
}
