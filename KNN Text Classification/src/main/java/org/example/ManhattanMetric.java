package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManhattanMetric extends Metric{
    public ManhattanMetric(List<Integer> featuresList) {
        super(featuresList);
    }


    @Override
    public double calculateDistance(TextVector vector1, TextVector vector2) {
        double sum = 0.0;
        for (int i : featuresList) {
            if ( i == 1 || i == 2 || i == 6) {
                sum += Math.abs((double) vector1.get(i) - (double) vector2.get(i));
            }
            else {
                sum += (double) Math.abs(calculateTextsDistance((String) vector1.get(i), (String) vector2.get(i)));
            }
        }
        return sum;
    }
}
