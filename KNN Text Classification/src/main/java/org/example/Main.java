package org.example;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.plaf.IconUIResource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
    /*for (double i = 0.9; i > 0; i = i - 0.2) {
        System.out.println("\n\n\n////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("k: " + i);*/
        ArticlesLoader articlesLoader = new ArticlesLoader();
        List<Article> articleList = articlesLoader.loadArticles();
        TextVectorsLoader textVectorsLoader = new TextVectorsLoader();
        List<TextVector> textVectors = textVectorsLoader.loadTextVectors(articleList);
        textVectors = textVectors.subList(0, 5000); //only part of the dataset!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<Integer> featuresList = new ArrayList<>();
        List<Integer> list = Arrays.asList(3, 4, 5);
        Metric metric = new EuclideanMetric(list);
        KNNClassifier knnClassifier = new KNNClassifier(4, 0.9, featuresList, metric, textVectors);
        List<PredictedRealPair> predictedRealPairs = knnClassifier.classify();
        ClassificationQualityMeasure classificationQualityMeasure = new ClassificationQualityMeasure(predictedRealPairs);
        System.out.println("Total");
        System.out.println("ACC: " + classificationQualityMeasure.calculateTotalAccuracy());

        ArrayList<String> contries = new ArrayList<>();
        contries.add("west-germany");
        contries.add("usa");
        contries.add("france");
        contries.add("uk");
        contries.add("canada");
        contries.add("japan");
        for (String country : contries) {
            double ACC = classificationQualityMeasure.calculateCountryAccuracy(country);
            double Precision = classificationQualityMeasure.calculateCountryPrecision(country);
            double Recall = classificationQualityMeasure.calculateCountryRecall(country);
            double F1 = 2 * (Precision * Recall) / (Precision + Recall);
            System.out.println(country);
            System.out.println("ACC: " + ACC);
            System.out.println("Precision: " + Precision);
            System.out.println("Recall: " + Recall);
            System.out.println("F1: " + F1);
            System.out.println();
        }
    //}



    }

}