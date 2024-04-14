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

    public double calculateCountryPrecision(String country) {
        int TP = 0;
        int FP = 0;
        for (PredictedRealPair predictedRealPair : predictedRealPairs) {
            if (predictedRealPair.real.equals(country) || predictedRealPair.predicted.equals(country)) {
                if (predictedRealPair.isCorrectlyClassified()) {
                    TP++;
                }
                if (predictedRealPair.isFP(country)) {
                    FP++;
                }
            }
        }
        return (double) TP / ((double) TP + (double) FP);
    }

    public double calculateCountryRecall(String country) {
        int TP = 0;
        int FN = 0;
        for (PredictedRealPair predictedRealPair : predictedRealPairs) {
            if (predictedRealPair.predicted.equals(country) || predictedRealPair.real.equals(country)) {
                if (predictedRealPair.isCorrectlyClassified()) {
                    TP++;
                }
                if (predictedRealPair.isFN(country)) {
                    FN++;
                }
            }
        }
        return (double) TP / ((double) TP + (double) FN);
    }
}
