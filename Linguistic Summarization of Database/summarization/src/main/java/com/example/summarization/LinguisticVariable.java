package com.example.summarization;

import java.util.ArrayList;

public class LinguisticVariable {
    private String name;
    private ArrayList<LinguisticLabel> linguisticLabels;
    private double domainL;
    private double domainR;
    private UniverseOfDiscourse universeOfDiscourse;

    public LinguisticVariable(String name, ArrayList<LinguisticLabel> linguisticLabels, double domainL, double domainR, UniverseOfDiscourse universeOfDiscourse) {
        this.name = name;
        this.linguisticLabels = linguisticLabels;
        this.domainL = domainL;
        this.domainR = domainR;
        this.universeOfDiscourse = universeOfDiscourse;
    }
}
