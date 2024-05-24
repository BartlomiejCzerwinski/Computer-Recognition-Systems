package com.example.summarization;

public class TrapezoidalFunction extends MembershipFunction {
    private double a;
    private double b;
    private double c;
    private double d;

    public TrapezoidalFunction(double alfaL, double alfaR, double domainL, double domainR, double a, double b, double c, double d) {
        super(alfaL, alfaR, domainL, domainR);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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