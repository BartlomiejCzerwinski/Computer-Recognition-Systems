package com.example.summarization;

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

    public MembershipFunction(double domainL, double domainR, UniverseOfDiscourse universeOfDiscourse) {
        this.domainL = domainL;
        this.domainR = domainR;
        this.universeOfDiscourse = universeOfDiscourse;
    }
}
