package org.example;

import java.util.List;

public class ManhattanMetric extends Metric{
    public ManhattanMetric(List<Integer> featuresList) {
        super(featuresList);
    }


    @Override
    public double calculateDistance(TextVector vector1, TextVector vector2) {
        return 0;
    }
}
