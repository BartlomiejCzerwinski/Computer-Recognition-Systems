package org.example;

import java.util.List;
public class EuclideanMetric extends Metric{
    public EuclideanMetric(List<Integer> featuresList) {
        super(featuresList);
    }

    @Override
    public double calculateDistance(TextVector vector1, TextVector vector2) {
        float sum = 0f;
        for (int i : featuresList) {
            if ( i == 1 || i == 2 || i == 6) {
                sum += Math.pow((double) vector1.get(i) - (double) vector2.get(i), 2.0);
            }
            else {
                sum += Math.pow(calculateTextsDistance((String) vector1.get(i), (String) vector2.get(i)), 2.0);
            }
        }
        return Math.sqrt((double) sum);
    }
}
