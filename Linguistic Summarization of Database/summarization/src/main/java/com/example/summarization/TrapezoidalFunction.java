package com.example.summarization;

public class TrapezoidalFunction extends MembershipFunction {
    private double a;
    private double b;
    private double c;
    private double d;

    public TrapezoidalFunction(double domainL, double domainR, double a, double b, double c, double d) {
        super(domainL, domainR);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double calculateMembershipDegree(double value) {
        if (value < a)
            return 0.0;
        else if ( a <= value && value < b)
            return (value - a) / (b - a);
        else if (b <= value && value <= c)
            return 1.0;
        else if (c < value && value <= d)
            return 1.0;
        else
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
