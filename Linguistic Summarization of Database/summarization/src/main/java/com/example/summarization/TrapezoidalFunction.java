package com.example.summarization;

import java.util.ArrayList;

public class TrapezoidalFunction extends MembershipFunction {
    private double startGrowth;
    private double stopGrowth;
    private double startDecrease;
    private double stopDecrease;

    public TrapezoidalFunction(double domainL, double domainR, UniverseOfDiscourse universeOfDiscourse, double startGrowth, double stopGrowth, double startDecrease, double stopDecrease) {
        super(domainL, domainR, universeOfDiscourse);
        this.startGrowth = startGrowth;
        this.stopGrowth = stopGrowth;
        this.startDecrease = startDecrease;
        this.stopDecrease = stopDecrease;
    }

    @Override
    public double calculateMembershipDegree(double value) {
        if (value < startGrowth)
            return 0.0;
        else if ( startGrowth <= value && value < stopGrowth)
            return (value - startGrowth) / (stopGrowth - startGrowth);
        else if (stopGrowth <= value && value <= startDecrease)
            return 1.0;
        else if (startDecrease < value && value <= stopDecrease)
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

    @Override
    public double getCardinality(ArrayList<Credit> credits, int columnIndex) {
        if (universeOfDiscourse == UniverseOfDiscourse.CONTINUOUS) {
            return (double) ((stopDecrease - startGrowth) + (startDecrease - stopGrowth)) / 2.0;
        }
        else {
            double sum = 0.0;
            for (Credit credit : credits) {
                sum += calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex));
            }
            return sum;
        }
    }
}
