package org.example;

public class LinguisticLabel {
    private String name;
    private MembershipFunction membershipFunction;

    public LinguisticLabel(String name, MembershipFunction membershipFunction) {
        this.name = name;
        this.membershipFunction = membershipFunction;
    }

    public SetType getSetType() {
        return SetType.NORMAL;
    }
}
