package org.example;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeaturesExtractor {
    public TextVector extract(Article article) {
        return null;
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

    public int getNumberOfWordsInText(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
