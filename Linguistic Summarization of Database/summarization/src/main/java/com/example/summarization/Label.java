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

    public ArrayList<Credit> getSupport(ArrayList<Credit> credits, int columnIndex) {
        ArrayList<Credit> result = new ArrayList<Credit>();
        for (Credit credit : credits) {
            if(membershipFunction.calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) > 0.0)
                result.add(credit);
        }
        return result;
    }

}
