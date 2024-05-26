package com.example.summarization;

import java.util.ArrayList;

public class Quantifier {
    private String name;
    private ArrayList<Label> labels;
    private boolean isAbsolute;

    public String getName() {
        return name;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }

    public Quantifier(String name, ArrayList<Label> labels, boolean isAbsolute) {
        this.name = name;
        this.labels = labels;
        this.isAbsolute = isAbsolute;
    }
}
