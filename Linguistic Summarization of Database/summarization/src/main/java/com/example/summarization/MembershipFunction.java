package com.example.summarization;

public abstract class MembershipFunction {
    private double alfaL;
    private double alfaR;
    private double domainL;
    private double domainR;

    public abstract double calculateMembershipDegree(double value);

    public abstract double calculateHeight();

    public abstract double calculateSupport();

    public abstract double calculateAlphaCut();

    public MembershipFunction(double domainL, double domainR) {
        this.domainL = domainL;
        this.domainR = domainR;
    }
}
