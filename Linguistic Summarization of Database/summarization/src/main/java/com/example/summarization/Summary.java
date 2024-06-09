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
        if (kind == "single subject") {
            switch (type) {
                case 1:
                    return quantifier + " " + subject1 + "s have " + summarizer;
                case 2:
                    return quantifier + " " + subject1 + "s with " + qualifier + " have " + summarizer;
            }
        }
        else {
            switch (type) {
                case 1:
                    return quantifier + " " + subject1 + " credits compared to " + subject2 + " credits have " + summarizer;
                case 2:
                    return quantifier + " " + subject1 + " credits compared to " + subject2 + " credits with " + qualifier + "have " + summarizer;
                case 3:
                    return quantifier + " " + subject1 + " credits with " + qualifier +" compared to " + subject2 + " credits have " + summarizer;
                case 4:
                    return "TODO";
            }
        }
        return null;
    }

    public ArrayList<Double> getMeasures() {
        return measures;
    }
}
