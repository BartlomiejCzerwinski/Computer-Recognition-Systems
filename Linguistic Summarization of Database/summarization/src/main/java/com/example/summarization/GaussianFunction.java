package com.example.summarization;

import java.util.ArrayList;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.analysis.function.Gaussian;

public class GaussianFunction extends MembershipFunction{
    private double mu; // https://en.wikipedia.org/wiki/Mu_(letter)
    private double omega; // https://en.wikipedia.org/wiki/Omega

    public GaussianFunction(double domainL, double domainR, UniverseOfDiscourse universeOfDiscourse, double mu, double omega) {
        super(domainL, domainR, universeOfDiscourse);
        this.mu = mu;
        this.omega = omega;
    }

    @Override
    public double calculateMembershipDegree(double value) {
         if (value >= domainL && value < domainR)
             return Math.exp(-((Math.pow(value - mu, 2))/(2.0*Math.pow(omega, 2))));
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
        if (universeOfDiscourse == UniverseOfDiscourse.DISCRETE) {
            double sum = 0.0;
            for (Credit credit : credits) {
                sum += calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex));
            }
            return sum;
        }
        else {
            UnivariateIntegrator integrator = new SimpsonIntegrator();
            Gaussian gaussian = new Gaussian(1.0, mu, omega);
            double result = integrator.integrate(
                    BaseAbstractUnivariateIntegrator.DEFAULT_MAX_ITERATIONS_COUNT,
                    gaussian,
                    domainL,
                    domainR
            );
            return result;
        }
    }
}
