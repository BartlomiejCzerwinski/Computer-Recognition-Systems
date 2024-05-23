package com.example.summarization;

public class Quantifier {
    private String name;
    private LinguisticLabel qualifier;
    private boolean isAbsolute;

    public String getName() {
        return name;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }

    public Quantifier(String name, LinguisticLabel qualifier, boolean isAbsolute) {
        this.name = name;
        this.qualifier = qualifier;
        this.isAbsolute = isAbsolute;
    }
}
