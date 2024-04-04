package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StopWordsFilter {
    private static Set<String> STOP_WORDS = new HashSet<>();

    public static String filter(String textToFilter) {
        StringBuilder patternBuilder = new StringBuilder();
        for (String word : STOP_WORDS) {
            patternBuilder.append("\\b").append(Pattern.quote(word)).append("\\b|");
        }
        patternBuilder.deleteCharAt(patternBuilder.length() - 1);
        Pattern pattern = Pattern.compile(patternBuilder.toString(), Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(textToFilter);
        String filteredText = matcher.replaceAll("");

        filteredText = filteredText.trim().replaceAll("\\s{2,}", " ");

        return filteredText;
    }

    public StopWordsFilter() {
        String[] words = {
                "the", "be", "to", "of", "and", "a", "in", "that", "have", "I",
                "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
                "this", "but", "his", "by", "from", "they", "we", "say", "her", "she",
                "or", "an", "will", "my", "one", "all", "would", "there", "their", "what",
                "so", "up", "out", "if", "about", "who", "get", "which", "go", "me",
                "when", "make", "can", "like", "time", "no", "just", "him", "know",
                "take", "people", "into", "year", "your", "good", "some", "could", "them",
                "see", "other", "than", "then", "now", "look", "only", "come", "its",
                "over", "think", "also", "back", "after", "use", "two", "how", "our",
                "work", "first", "well", "way", "even", "new", "want", "because", "any",
                "these", "give", "day", "most", "us"
        };

        for (String word : words) {
            STOP_WORDS.add(word);
        }
    }
}
