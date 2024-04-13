package org.example;

public class PredictedRealPair {
    String predicted;
    String real;

    public PredictedRealPair(String predicted, String real) {
        this.predicted = predicted;
        this.real = real;
    }

    public String getPredicted() {
        return predicted;
    }

    public String getReal() {
        return real;
    }

    public boolean isCorrectlyClassified() {
        return predicted.equals(real);
    }
}
