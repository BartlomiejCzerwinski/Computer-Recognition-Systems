package com.example.summarization;

import java.util.ArrayList;

public class Summary {
    private String subject1;
    private String subject2;
    private ArrayList<Double> measures;
    private String quantifier;
    private String qualifier;
    private String summarizer;
    private String summarizerMany;
    private String kind;
    private int type;


    public Summary(String kind, int type, String subject1, String subject2, ArrayList<Double> measures, String quantifier, String qualifier, String summarizer, String summarizerMany) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.measures = measures;
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.summarizerMany = summarizerMany;
        this.kind = kind;
        this.type = type;
    }

    public String getSentence(){
        String sentence = "";
        if (kind == "single subject") {
            switch (type) {
                case 1:
                    sentence = quantifier + " " + subject1 + "s have\n " + summarizer;
                    break;
                case 2:
                    sentence = quantifier + " " + subject1 + "s with " + qualifier + " have " + summarizer;
                    break;
            }
        }
        else {
            switch (type) {
                case 1:
                    sentence = quantifier + " " + subject1 + " credits compared to " + subject2 + " credits have " + summarizer;
                    break;
                case 2:
                    sentence = quantifier + " " + subject1 + " credits compared to " + subject2 + " credits with " + qualifier + " have " + summarizer;
                    break;
                case 3:
                    sentence = quantifier + " " + subject1 + " credits with " + qualifier + " compared to " + subject2 + " credits have " + summarizer;
                    break;
                case 4:
                    sentence = "more " + subject1 + " credits than " + subject2 + " credits have " + summarizer;
                    break;
            }
        }
        return splitSentence(sentence, 12);
    }

    public static String splitSentence(String sentence, int wordsPerLine) {
        String[] words = sentence.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0 && i % wordsPerLine == 0) {
                result.append("\n");
            }
            result.append(words[i]);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public ArrayList<Double> getMeasures() {
        return measures;
    }

    public String getSubject1() {
        return subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public String getQuantifier() {
        return quantifier;
    }

    public String getQualifier() {
        return qualifier;
    }

    public String getSummarizer() {
        return summarizer;
    }

    public String getSummarizerMany() {
        return summarizerMany;
    }

    public String getKind() {
        return kind;
    }

    public int getType() {
        return type;
    }
}
