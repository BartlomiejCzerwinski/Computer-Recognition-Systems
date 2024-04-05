package org.example;

import java.util.ArrayList;
import java.util.List;

public class TextVectorsLoader {

    public TextVectorsLoader() {
    }

    public List<TextVector> loadTextVectors(List<Article> articles) {
        List<TextVector> textVectors = new ArrayList<>();
        FeaturesExtractor featuresExtractor = new FeaturesExtractor();
        for (Article a : articles) {
            String body = a.getBody();
            TextVector textVector = new TextVector(a.getCountry(),
                    featuresExtractor.extractFirstBigLettersSeries(body),
                    featuresExtractor.extractRelativeNumberOfWordsLongerThan9Signs(body),
                    featuresExtractor.extractRelativeNumberOfUniqueWords(body),
                    featuresExtractor.extractMostCommonBigLetterWordInFirstXPercent(body, 0.20),
                    featuresExtractor.extractMostCommonBigLetterWordInFirstXPercent(body, 0.50),
                    featuresExtractor.extractMostCommonBigLetterWordInFirstXPercent(body, 1.00),
                    featuresExtractor.extractRelativeNumberOfNumbers(body),
                    featuresExtractor.extractMostCommonBigLettersSeriesInText(body),
                    "CURRENCY_PLACEHOLDER",
                    "COUNTRY_PLACEHOLDER",
                    "CONTINENT_PLACEHOLDER");
        }
    }

}
