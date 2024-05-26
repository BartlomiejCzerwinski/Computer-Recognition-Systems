package com.example.summarization;

public class GaussianFunction extends MembershipFunction{
    private double a;

    public GaussianFunction(double domainL, double domainR, double a) {
        super(domainL, domainR);
        this.a = a;
    }

    @Override
    public double calculateMembershipDegree(double value) {
         if (value >= domainL && value < domainR)
             return Math.exp(-(Math.pow(value - a, 2))/1.8*Math.pow(domainL+domainR, 2));
         return 0.0;
    }

    @Override
    public double calculateHeight() {
        return 0;
    }

    @Override
    public double calculateSupport() {
        return 0;
    }

    @Override
    public double calculateAlphaCut() {
        return 0;
    }
}
