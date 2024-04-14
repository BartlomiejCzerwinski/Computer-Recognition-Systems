package org.example;

import java.net.InterfaceAddress;
import java.util.*;

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

    public List<PredictedRealPair> classify() {
        List<PredictedRealPair> predictedRealPairs = new ArrayList<>();
        int i = 1;
        for (TextVector testVector : testVectors) {
            List<DistanceToCountry> distancesToCountries = new ArrayList<>();
            for (TextVector trainVector : trainVectors) {
                distancesToCountries.add(new DistanceToCountry( trainVector.getCountry(), metric.calculateDistance(testVector, trainVector)));
            }
            Collections.sort(distancesToCountries);
            predictedRealPairs.add(new PredictedRealPair(predictCountry(distancesToCountries), testVector.getCountry()));
            //System.out.println((((double) i / (double) testVectors.size())*100.0) + "%");     \\percent of load
            i++;
        }
        return predictedRealPairs;
    }

    public String predictCountry(List<DistanceToCountry> distancesToCountries) {
        Map<String, Integer> counters = new HashMap<String, Integer>() {{
            put("west-germany", 0);
            put("usa", 0);
            put("france", 0);
            put("uk", 0);
            put("canada", 0);
            put("japan", 0);
        }};

        for (int i = 0; i < k; i++) {
            int currVal = counters.get(distancesToCountries.get(i).getCountry());
            counters.put(distancesToCountries.get(i).getCountry(), currVal + 1);
        }

        String countryWithMaxCount = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : counters.entrySet()) {
            String country = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                countryWithMaxCount = country;
            }
        }
        return countryWithMaxCount;
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
    }
}
