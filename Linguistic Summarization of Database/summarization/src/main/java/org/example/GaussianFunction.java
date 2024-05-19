package org.example;

public class GaussianFunction extends MembershipFunction{
    private double a;

    public GaussianFunction(double alfaL, double alfaR, double domainL, double domainR, double a) {
        super(alfaL, alfaR, domainL, domainR);
        this.a = a;
    }

    @Override
    public double calculateMembershipDegree() {
        return 0;
    }
}
