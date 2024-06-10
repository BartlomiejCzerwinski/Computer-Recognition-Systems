package com.example.summarization;

import java.util.ArrayList;

public class JoinLabels {
    Label labelA;
    Label labelB;
    ArrayList<Credit> credits;
    int columnIndex;

    public JoinLabels(Label labelA, Label labelB, ArrayList<Credit> credits, int columnIndex) {
        this.labelA = labelA;
        this.labelB = labelB;
        this.credits = credits;
        this.columnIndex = columnIndex;
    }

    public double sum() {
        double sum = 0.0;
        for (Credit credit : credits) {
            if ( labelA.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) == 1 &&
                    labelB.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)) == 1)
            sum += Math.min(labelA.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)),
                    labelB.getMembershipFunction().calculateMembershipDegree(credit.getValueByColumnIndex(columnIndex)));
        }
        return sum;
    }
}
