package org.example;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeaturesExtractor {
    public TextVector extract(Article article) {
        return null;
    }

    public static String extractMostCommonBigLettersSeriesInText(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();

        Pattern pattern = Pattern.compile("\\b[A-Z][a-zA-Z]*(?:\\s+[A-Z][a-zA-Z]*)+\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String words = matcher.group();
            System.out.println("words: " + words);
            wordCounts.put(words, wordCounts.getOrDefault(words, 0) + 1);
        }

        String mostFrequentWords = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentWords = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentWords;
    }

    public double extractRelativeNumberOfNumbers(String text) {
        Pattern pattern = Pattern.compile("-?\\d+(?:[.,]\\d+)?(?:-\\d+)*");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return (double)count / getNumberOfWordsInText(text);
    }

    public String extractMostCommonBigLetterWordInFirstXPercent(String text, double percentage) {
        String[] words = text.trim().split("\\s+");
        int numberOfWords = getNumberOfWordsInText(text);
        int numberOfWordsToConsider = (int)(numberOfWords * percentage);

        int maxCount = 0;
        String mostFrequentWord = null;

        Map<String, Integer> wordCounts = new HashMap<>();
        for (int i = 0; i < numberOfWordsToConsider; i++) {
            String word = words[i];
            if (Character.isUpperCase(word.charAt(0))) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                if (wordCounts.size() == 1) {
                    maxCount += 1;
                    mostFrequentWord = word;
                }
            }
        }

        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentWord = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentWord;
    }

    public double extractRelativeNumberOfUniqueWords(String text) {
        String[] words = text.trim().split("\\s+");

        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (word.matches("[a-zA-Z]+")) {
                uniqueWords.add(word.toLowerCase());
            }
        }

        return (double) uniqueWords.size() / getNumberOfWordsInText(text);
    }

    public double extractRelativeNumberOfWordsLongerThan9Signs(String text) {
        String[] words = text.trim().split("\\s+");

        int longWordCount = 0;

        for (String word : words) {
            if (word.length() > 9) {
                longWordCount++;
            }
        }

        return (double)longWordCount / getNumberOfWordsInText(text);
    }

    public String extractFirstBigLettersSeries(String text) {
        Pattern pattern = Pattern.compile("\\b[A-ZŻŹĆĄŚĘŁÓ][a-zżźćńółęąś]*\\s[A-ZŻŹĆĄŚĘŁÓ][a-zżźćńółęąś]*\\b");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    public int getNumberOfWordsInText(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
