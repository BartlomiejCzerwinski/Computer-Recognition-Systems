package org.example;

public abstract class MembershipFunction {
    private double alfaL;
    private double alfaR;
    private double domainL;
    private double domainR;

    public abstract double calculateMembershipDegree();

    public MembershipFunction(double alfaL, double alfaR, double domainL, double domainR) {
        this.alfaL = alfaL;
        this.alfaR = alfaR;
        this.domainL = domainL;
        this.domainR = domainR;
    }
}
