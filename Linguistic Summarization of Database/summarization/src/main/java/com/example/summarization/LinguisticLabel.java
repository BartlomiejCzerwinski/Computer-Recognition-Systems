package com.example.summarization;

public class LinguisticLabel {
    private String name;
    private MembershipFunction membershipFunction;
    private boolean isNormal;
    private boolean isConvex;

    public LinguisticLabel(String name, MembershipFunction membershipFunction, boolean isNormal, boolean isConvex) {
        this.name = name;
        this.membershipFunction = membershipFunction;
        this.isNormal = isNormal;
        this.isConvex = isConvex;
    }
}
