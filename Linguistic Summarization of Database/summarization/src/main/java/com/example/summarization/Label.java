package com.example.summarization;

import java.util.ArrayList;
import java.util.Arrays;

public class Label {
    private String name;
    private MembershipFunction membershipFunction;
    private boolean isNormal;
    private boolean isConvex;
    private int columnIndex;

    public Label(String name, MembershipFunction membershipFunction, boolean isNormal, boolean isConvex, int columnIndex) {
        this.name = name;
        this.membershipFunction = membershipFunction;
        this.isNormal = isNormal;
        this.isConvex = isConvex;
        this.columnIndex = columnIndex;
    }

    public String getName() {
        return name;
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    public double getSupport(ArrayList<Credit> credits, int columnIndex) {
        double supp = 0.0;
        for (Credit credit : credits) {
            if (membershipFunction.calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) > 0)
                supp ++;
        }
        return supp;
    }


    public double getHeight(ArrayList<Credit> credits, int columnIndex) {
        double max = 0.0;
        for (Credit credit : credits) {
            if (membershipFunction.calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) > max) {
                max = membershipFunction.calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex));
            }
        }
        return max;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
