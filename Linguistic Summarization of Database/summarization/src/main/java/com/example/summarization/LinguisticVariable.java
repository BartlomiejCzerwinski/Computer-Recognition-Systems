package com.example.summarization;

import java.util.ArrayList;

public class LinguisticVariable {
    private String name;
    private ArrayList<Label> labels;
    private double domainL;
    private double domainR;

    public LinguisticVariable(String name, ArrayList<Label> labels, double domainL, double domainR) {
        this.name = name;
        this.labels = labels;
        this.domainL = domainL;
        this.domainR = domainR;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public String getName() {
        return name;
    }
}
