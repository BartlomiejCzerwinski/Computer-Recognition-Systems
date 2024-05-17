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
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj liczbe k najblizszych sasiadow: ");
        int k = scanner.nextInt();
        System.out.println("Wybierz proporcje podziału na zbior treningowy i testowy");
        System.out.println("1. 10/90");
        System.out.println("2. 30/90");
        System.out.println("3. 50/90");
        System.out.println("4. 90/90");
        System.out.println("5. 95/90");
        int p = scanner.nextInt();
        double proportion = 0.0;
        switch (p) {
            case 1:
                proportion = 0.1;
                break;
            case 2:
                proportion = 0.3;
                break;
            case 3:
                proportion = 0.5;
                break;
            case 4:
                proportion = 0.9;
                break;
            case 5:
                proportion = 0.95;
                break;
        }
        System.out.println("Podaj cechy uwzgledniane przy klasyfikacji (oddzielone spacja)");
        System.out.println("1. Pierwszy wystepujacy ciag slow z wielkiej litery");
        System.out.println("2. Wzgledna liczba slow dluzszych niz 9 znakow");
        System.out.println("3. Wzgledna liczba unikalnych slow");
        System.out.println("4. Najczesciej wystepujace slowo z wielkiej litery w pierwszych 20% tekstu");
        System.out.println("5. Najczesciej wystepujace slowo z wielkiej litery w pierwszych 50% tekstu");
        System.out.println("6. Najczesciej wystepujace slowo z wielkiej litery w calym tekscie");
        System.out.println("7. Wzgledna ilosc liczb");
        System.out.println("8. Najczesciej wystepujacy ciag slow, rozpoczynajacych sie wielka litera");
        System.out.println("9. Najczesciej wystepujaca waluta ze slownika");
        System.out.println("10. Najczesciej wystepujacy kraj ze slownika");
        System.out.println("11. Najczesciej wystepujacy kontynent ze slownika");
        System.out.println("12. Wszystkie cechy");
        scanner.nextLine();
        String input = scanner.nextLine();
        String[] options = input.split("[,\\s]+");

        List<Integer> featuresList = new ArrayList<>();
        for (String option : options) {
            if (Integer.valueOf(option) == 12) {
                featuresList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                break;
            }
            int feature = Integer.parseInt(option) - 1;
            featuresList.add(feature);
        }

        System.out.println("Wybierz Metryke");
        System.out.println("1. Metryka Euklidesowa");
        System.out.println("2. Metryka Uliczna");
        System.out.println("3. Metryka Czybyszewa");
        int m = scanner.nextInt();
        Metric metric = null;
        switch (m) {
            case 1:
                metric = new EuclideanMetric(featuresList);
                break;
            case 2:
                metric = new ManhattanMetric(featuresList);
                break;
            case 3:
                metric = new ChebyshevMetric(featuresList);
                break;
        }

        ArticlesLoader articlesLoader = new ArticlesLoader();
        List<Article> articleList = articlesLoader.loadArticles();
        TextVectorsLoader textVectorsLoader = new TextVectorsLoader();
        List<TextVector> textVectors = textVectorsLoader.loadTextVectors(articleList);
        KNNClassifier knnClassifier = new KNNClassifier(k, proportion, featuresList, metric, textVectors);
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

    }

}