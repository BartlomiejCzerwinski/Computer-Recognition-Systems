package com.example.summarization;

import java.util.ArrayList;

public class Summary {
    private String summary;
    private String subject1;
    private String subject2;
    private int type;
    private String kind;
    private ArrayList<Double> measures;
    private ArrayList<Double> measuresWeights;
    private ArrayList<Label> summarizers;
    private Quantifier quantifier;
    private Label qualifier;
    private double T;

    public void generateSummary() {
        return;
    }

    public void calculateMeasures() {
        return;
    }

    public Summary(String summary, String subject1, String subject2, int type, String kind, ArrayList<Double> measures, ArrayList<Double> measuresWeights, ArrayList<Label> summarizers, Quantifier quantifier, Label qualifier, double t) {
        this.summary = summary;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.type = type;
        this.kind = kind;
        this.measures = measures;
        this.measuresWeights = measuresWeights;
        this.summarizers = summarizers;
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        T = t;
    }
}
