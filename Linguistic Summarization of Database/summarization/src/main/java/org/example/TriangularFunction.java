package org.example;

public class TriangularFunction extends MembershipFunction {
    private double a;
    private double b;
    private double c;

    public TriangularFunction(double alfaL, double alfaR, double domainL, double domainR, double a, double b, double c) {
        super(alfaL, alfaR, domainL, domainR);
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
