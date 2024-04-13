package org.example;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

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
        ArticlesLoader articlesLoader = new ArticlesLoader();
        List<Article> articleList = articlesLoader.loadArticles();
        TextVectorsLoader textVectorsLoader = new TextVectorsLoader();
        List<TextVector> textVectors = textVectorsLoader.loadTextVectors(articleList);
        textVectors = textVectors.subList(0, 5000); //only part of the dataset!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<Integer> featuresList = new ArrayList<>();
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Metric metric = new ManhattanMetric(list);
        KNNClassifier knnClassifier = new KNNClassifier(300, 0.5, featuresList, metric, textVectors);
        List<PredictedRealPair> predictedRealPairs = knnClassifier.classify();
        ClassificationQualityMeasure classificationQualityMeasure = new ClassificationQualityMeasure(predictedRealPairs);
        System.out.println("Total");
        System.out.println("ACC: " + classificationQualityMeasure.calculateTotalAccuracy());
        System.out.println("west-germany:");
        System.out.println("ACC: " + classificationQualityMeasure.calculateCountryAccuracy("west-germany"));
        System.out.println("usa:");
        System.out.println("ACC: " + classificationQualityMeasure.calculateCountryAccuracy("usa"));
        System.out.println("france:");
        System.out.println("ACC: " + classificationQualityMeasure.calculateCountryAccuracy("france"));
        System.out.println("uk:");
        System.out.println("ACC: " + classificationQualityMeasure.calculateCountryAccuracy("uk"));
        System.out.println("japan:");
        System.out.println("ACC: " + classificationQualityMeasure.calculateCountryAccuracy("japan"));
    }

}