package com.example.summarization;

import java.util.ArrayList;

public class Label {
    private String name;
    private MembershipFunction membershipFunction;
    private boolean isNormal;
    private boolean isConvex;

    public Label(String name, MembershipFunction membershipFunction, boolean isNormal, boolean isConvex) {
        this.name = name;
        this.membershipFunction = membershipFunction;
        this.isNormal = isNormal;
        this.isConvex = isConvex;
    }

    public String getName() {
        return name;
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

}
