package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChebyshevMetric extends Metric{
    public ChebyshevMetric(List<Integer> featuresList) {
        super(featuresList);
    }

    @Override
    public double calculateDistance(TextVector vector1, TextVector vector2) {
        List<Double> distances = new ArrayList<>();
        for (int i : featuresList) {
            if ( i == 1 || i == 2 || i == 6) {
                distances.add(Math.abs((double) vector1.get(i) - (double) vector2.get(i)));
            }
            else {
                distances.add((double) Math.abs(calculateTextsDistance((String) vector1.get(i), (String) vector2.get(i))));
            }
        }
        distances.sort(Collections.reverseOrder());

        return distances.get(0);
    }
}
