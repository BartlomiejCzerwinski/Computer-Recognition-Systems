package com.example.summarization;

import java.util.ArrayList;

public abstract class MembershipFunction {
    private double alfaL;
    private double alfaR;
    protected double domainL;
    protected double domainR;
    protected UniverseOfDiscourse universeOfDiscourse;

    public abstract double calculateMembershipDegree(double value);

    public abstract double calculateHeight();

    public abstract double calculateSupport();

    public abstract double calculateAlphaCut();

    public abstract double getCardinality(ArrayList<Credit> credits, int columnIndex);
    public abstract double getAlphaCount(ArrayList<Credit> credits, int columnIndex);
    public abstract double getAlphaCountNonFuzzy(ArrayList<Credit> credits, int columnIndex);

    public MembershipFunction(double domainL, double domainR, UniverseOfDiscourse universeOfDiscourse) {
        this.domainL = domainL;
        this.domainR = domainR;
        this.universeOfDiscourse = universeOfDiscourse;
    }
}
