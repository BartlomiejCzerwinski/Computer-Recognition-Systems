package org.example;

import java.util.List;

public class ClassificationQualityMeasure {
    private List<PredictedRealPair> predictedRealPairs;

    public ClassificationQualityMeasure(List<PredictedRealPair> predictedRealPairs) {
        this.predictedRealPairs = predictedRealPairs;
    }

    public double calculateTotalAccuracy() {
        int correctlyClassified = 0;
        for (PredictedRealPair predictedRealPair : predictedRealPairs) {
            if (predictedRealPair.isCorrectlyClassified())
                    correctlyClassified ++;
        }
        return (double) correctlyClassified / (double) predictedRealPairs.size();
    }

    public double calculateCountryAccuracy(String country) {
        int total = 0;
        int correctlyClassified = 0;
        for (PredictedRealPair predictedRealPair : predictedRealPairs) {
            if (predictedRealPair.getReal().equals(country)) {
                total ++;
                if (predictedRealPair.isCorrectlyClassified())
                    correctlyClassified ++;
            }
        }
        return (double) correctlyClassified / (double) total;
    }
}
