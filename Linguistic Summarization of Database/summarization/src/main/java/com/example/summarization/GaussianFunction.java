package com.example.summarization;

public class GaussianFunction extends MembershipFunction{
    private double a;

    public GaussianFunction(double domainL, double domainR, double a) {
        super(domainL, domainR);
        this.a = a;
    }

    @Override
    public double calculateMembershipDegree() {
        return 0;
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
