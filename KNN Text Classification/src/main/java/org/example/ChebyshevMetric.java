package org.example;

import java.util.List;

public class ChebyshevMetric extends Metric{
    public ChebyshevMetric(List<Integer> featuresList) {
        super(featuresList);
    }

    @Override
    public double calculateDistance(TextVector vector1, TextVector vector2) {
        return 0;
    }
}
