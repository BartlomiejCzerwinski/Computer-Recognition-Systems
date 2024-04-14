package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TextVectorsLoader {

    public TextVectorsLoader() {
    }

    public List<TextVector> loadTextVectors(List<Article> articles) throws FileNotFoundException {
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
                    featuresExtractor.extractMostCommonCurrencyInText(body),
                    featuresExtractor.extractMostCommonCountryInText(body),
                    featuresExtractor.extractMostCommonContinentInText(body));
            textVectors.add(textVector);
        }
        return textVectors;
    }

}
