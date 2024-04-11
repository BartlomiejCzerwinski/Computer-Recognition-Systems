package org.example;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class KNNClassifier {
    private int k;
    private double divideProportion; // Proportion of the training data, e.g. 0.9 - 90% train, 10% test
    private List<Integer> featuresList;
    private Metric metric;
    private List<TextVector> trainVectors = new ArrayList<>();
    private List<TextVector> testVectors = new ArrayList<>();

    public KNNClassifier(int k, double divideProportion, List<Integer> featuresList, Metric metric, List<TextVector> textVectors) {
        this.k = k;
        this.divideProportion = divideProportion;
        this.featuresList = featuresList;
        this.metric = metric;
        splitData(textVectors);
    }

    public void splitData(List<TextVector> textVectors) {
        double trainingProportion = divideProportion;
        double testingProportion = 1.0 - divideProportion;

        int trainingSize = (int) (textVectors.size() * trainingProportion);
        int testingSize = textVectors.size() - trainingSize;

        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < textVectors.size(); i++) {
            indexes.add(i);
        }

        Collections.shuffle(indexes);

        for (int i = 0; i < trainingSize; i++) {
            trainVectors.add(textVectors.get(i));
        }
        for (int i = trainingSize; i < textVectors.size(); i++) {
            testVectors.add(textVectors.get(i));
        }
        System.out.println("ALL: " + textVectors.size());
        System.out.println("TRAIN: " + trainVectors.size());
        System.out.println("TEST: " + testVectors.size());
        System.out.println("SUM: " + (testVectors.size() + trainVectors.size()));

    }
}
