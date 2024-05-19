package org.example;

import java.util.ArrayList;

public class LinguisticVariable {
    private String name;
    private ArrayList<LinguisticLabel> linguisticLabels;
    private double domainL;
    private double domainR;

    public LinguisticVariable(String name, ArrayList<LinguisticLabel> linguisticLabels, double domainL, double domainR) {
        this.name = name;
        this.linguisticLabels = linguisticLabels;
        this.domainL = domainL;
        this.domainR = domainR;
    }
}
