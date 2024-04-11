package org.example;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;

public class FeaturesExtractor {
    private static String CURRENCIES_DIC = "currencies.json";
    Map<String, String> currencies;

    public FeaturesExtractor() throws FileNotFoundException {
        this.currencies = loadDictionary(CURRENCIES_DIC);
    }

    public TextVector extract(Article article) {
        return null;
    }

    public String extractMostCommonCurrencyInText(String text) throws FileNotFoundException {
        StringBuilder regexBuilder = new StringBuilder();
        for (String currency : currencies.keySet()) {
            if (regexBuilder.length() > 0) {
                regexBuilder.append("|");
            }
            regexBuilder.append(Pattern.quote(currency));
        }
        Pattern pattern = Pattern.compile(regexBuilder.toString(), Pattern.CASE_INSENSITIVE);

        Map<String, Integer> currencyCounts = new HashMap<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String currency = matcher.group().toLowerCase();
            currencyCounts.put(currency, currencyCounts.getOrDefault(currency, 0) + 1);
        }
        Map<String, Integer> currencyCountsTotal = new HashMap<>();
        for (Map.Entry<String, Integer> entry : currencyCounts.entrySet()) {
            currencyCountsTotal.put(currencies.get(entry.getKey()), currencyCountsTotal.getOrDefault(currencies.get(entry.getKey()), 0) + entry.getValue());
        }
        String mostCommonCurrency = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : currencyCountsTotal.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonCurrency = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        if (mostCommonCurrency != null && currencies.containsKey(mostCommonCurrency)) {
            return currencies.get(mostCommonCurrency);
        } else {
            return "";
        }
    }

    public HashMap<String, String> loadDictionary(String filePath) throws FileNotFoundException {
        Gson gson = new Gson();
        HashMap<String, String> hashMap = gson.fromJson(new FileReader(filePath), HashMap.class);

        return hashMap;
    }

    public String extractMostCommonBigLettersSeriesInText(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();

        Pattern pattern = Pattern.compile("\\b[A-Z][a-zA-Z]*(?:\\s+[A-Z][a-zA-Z]*)+\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String words = matcher.group();
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
            if ( word.length() > 0 && Character.isUpperCase(word.charAt(0))) {
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
