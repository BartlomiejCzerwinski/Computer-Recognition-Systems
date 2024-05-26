package com.example.summarization;

import java.util.ArrayList;

public class LinguisticVariable {
    private String name;
    private ArrayList<Label> labels;
    private double domainL;
    private double domainR;
    private UniverseOfDiscourse universeOfDiscourse;

    public LinguisticVariable(String name, ArrayList<Label> labels, double domainL, double domainR, UniverseOfDiscourse universeOfDiscourse) {
        this.name = name;
        this.labels = labels;
        this.domainL = domainL;
        this.domainR = domainR;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public ArrayList<Label> getLabels() {g
        return labels;
    }
}
