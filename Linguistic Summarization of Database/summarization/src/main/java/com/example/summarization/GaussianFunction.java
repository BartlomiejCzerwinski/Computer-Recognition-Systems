package com.example.summarization;

public class GaussianFunction extends MembershipFunction{
    private double mu; // https://en.wikipedia.org/wiki/Mu_(letter)
    private double omega; // https://en.wikipedia.org/wiki/Omega

    public GaussianFunction(double domainL, double domainR, double mu, double omega) {
        super(domainL, domainR);
        this.mu = mu;
        this.omega = omega;
    }

    @Override
    public double calculateMembershipDegree(double value) {
         if (value >= domainL && value < domainR)
             return Math.exp(-(Math.pow(value - mu, 2))/2.0*Math.pow(omega, 2));
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
