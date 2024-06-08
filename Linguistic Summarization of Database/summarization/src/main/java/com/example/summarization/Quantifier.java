package com.example.summarization;

import java.util.ArrayList;

public class Quantifier {
    private String name;
    private Label label;
    private boolean isAbsolute;

    public String getName() {
        return name;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }

    public Quantifier(String name, Label label, boolean isAbsolute) {
        this.name = name;
        this.label = label;
        this.isAbsolute = isAbsolute;
    }

    public Label getLabel() {
        return label;
    }
}
