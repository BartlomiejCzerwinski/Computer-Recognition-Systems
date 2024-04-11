package org.example;

import java.net.InterfaceAddress;
import java.util.List;

public class KNNClassifier {
    private int k;
    private double divideProportion; // Proportion of the training data, e.g. 0.9 - 90% train, 10% test
    private List<Integer> featuresList;
    private Metric metric;
    private List<TextVector> trainingVectors;
    private List<TextVector> testVectors;

    public KNNClassifier(int k, double divideProportion, List<Integer> featuresList, Metric metric) {
        this.k = k;
        this.divideProportion = divideProportion;
        this.featuresList = featuresList;
        this.metric = metric;
    }
}
