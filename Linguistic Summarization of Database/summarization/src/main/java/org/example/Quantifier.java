package org.example;

public class Quantifier {
    private String name;
    private LinguisticLabel qualifier;
    private boolean isAbsolute;

    public Quantifier(String name, LinguisticLabel qualifier, boolean isAbsolute) {
        this.name = name;
        this.qualifier = qualifier;
        this.isAbsolute = isAbsolute;
    }
}
