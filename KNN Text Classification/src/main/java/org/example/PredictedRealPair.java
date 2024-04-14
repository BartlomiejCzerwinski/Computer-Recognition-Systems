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

    public boolean isFP (String country) {
        if (!real.equals(country) && predicted.equals(country)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isFN (String country) {
        if (real.equals(country) && !predicted.equals(country)) {
            return true;
        }
        else {
            return false;
        }
    }
}
