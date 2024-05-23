package com.example.summarization;

public abstract class MembershipFunction {
    private double alfaL;
    private double alfaR;
    private double domainL;
    private double domainR;

    public abstract double calculateMembershipDegree();

    public abstract double calculateHeight();

    public abstract double calculateSupport();

    public abstract double calculateAlphaCut();

    public MembershipFunction(double alfaL, double alfaR, double domainL, double domainR) {
        this.alfaL = alfaL;
        this.alfaR = alfaR;
        this.domainL = domainL;
        this.domainR = domainR;
    }
}
