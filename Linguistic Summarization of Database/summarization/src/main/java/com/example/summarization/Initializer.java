package com.example.summarization;

import java.util.ArrayList;
import java.util.Arrays;

public class Initializer {
    private int NUMBER_OF_VARIABLES = 10;
    private String[] LINGUISTIC_VARIABLE_NAMES = new String[] {"credit amount",
    "credit interest", "amount of revenue", "number of queries", "amount of installment",
    "DTI", "balance of revolving credits", "total amount of debt collection", "credit limit",
    "balance of all accounts"};
    private Double[] L = new Double[] {300.0, 5.32, 0.0, 0.0, 15.0, 0.0, 0.0, 0.0, 100.0, 0.0};
    private Double[] R = new Double[] {40000.0, 30.0, 950000.0, 35.0, 1500.0, 100.0, 3000000.0, 99950.0, 99999.0, 999999.0};
    private UniverseOfDiscourse[] UNIVERSES_OF_DISCOURSES = new UniverseOfDiscourse[] {
    UniverseOfDiscourse.CONTINUOUS, UniverseOfDiscourse.CONTINUOUS, UniverseOfDiscourse.CONTINUOUS, UniverseOfDiscourse.DISCRETE,
            UniverseOfDiscourse.CONTINUOUS, UniverseOfDiscourse.DISCRETE, UniverseOfDiscourse.CONTINUOUS, UniverseOfDiscourse.CONTINUOUS,
            UniverseOfDiscourse.CONTINUOUS, UniverseOfDiscourse.CONTINUOUS
    };

    private ArrayList<ArrayList<Label>> LINGUISTIC_LABELS = new ArrayList<>();


    public ArrayList<LinguisticVariable> createLinguisticVariables() {
        ArrayList<LinguisticVariable> linguisticVariables = new ArrayList<>();
        initLinguisticLabels();
        for (int i = 0; i < NUMBER_OF_VARIABLES; i++) {
            linguisticVariables.add(new LinguisticVariable(LINGUISTIC_VARIABLE_NAMES[i], LINGUISTIC_LABELS.get(i), L[i], R[i], UNIVERSES_OF_DISCOURSES[i]));
        }
        return linguisticVariables;
    }

    public void initLinguisticLabels() {
        LINGUISTIC_LABELS.add(initLinguisticLabels1());
        LINGUISTIC_LABELS.add(initLinguisticLabels2());
        LINGUISTIC_LABELS.add(initLinguisticLabels3());
        LINGUISTIC_LABELS.add(initLinguisticLabels4());
        LINGUISTIC_LABELS.add(initLinguisticLabels5());
        LINGUISTIC_LABELS.add(initLinguisticLabels6());
        LINGUISTIC_LABELS.add(initLinguisticLabels7());
        LINGUISTIC_LABELS.add(initLinguisticLabels8());
        LINGUISTIC_LABELS.add(initLinguisticLabels9());
        LINGUISTIC_LABELS.add(initLinguisticLabels10());
    }

    public ArrayList<Label> initLinguisticLabels1() {
        Label label1 = new Label("a loan for less than 10000", new TrapezoidalFunction(300, 10000, 300, 300, 10000, 10000), true, true);
        Label label2 = new Label("loan for about 11000", new TrapezoidalFunction(9500, 12500, 9500, 10500, 11500, 1250), true, true);
        Label label3 = new Label("loan from 12000 to 18000", new TrapezoidalFunction(12000, 18000, 12000, 12000, 18000, 18000), true, true);
        Label label4 = new Label("loan for about 20000", new TrapezoidalFunction(17000, 23000, 17000, 19000, 21000, 23000), true, true);
        Label label5 = new Label("loan from 22000 to 30000", new TrapezoidalFunction(22000, 30000, 22000, 22000, 30000, 30000), true, true);
        Label label6 = new Label("a loan for more than 30000", new TrapezoidalFunction(30000, 40000, 30000, 30000, 40000, 40000), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5, label6));
    }
    public ArrayList<Label> initLinguisticLabels2() {
        Label label1 = new Label("interest rate no more than 10%", new TrapezoidalFunction(5.32, 10, 5.32, 5.32, 8, 10), true, true);
        Label label2 = new Label("interest rate from 9% to 20%", new TrapezoidalFunction(9, 20, 9, 9, 20, 29), true, true);
        Label label3 = new Label("interest rate more than 20%", new TrapezoidalFunction(20, 30, 20, 20, 30, 30), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3));
    }
    public ArrayList<Label> initLinguisticLabels3() {
        Label label1 = new Label("income up to 23000", new TrapezoidalFunction(0, 23000, 0, 0, 23000, 23000), true, true);
        Label label2 = new Label("income about 25000", new GaussianFunction(15000, 35000, 25000), true, true);
        Label label3 = new Label("income from 27000 to 68000", new TrapezoidalFunction(27000, 68000, 27000, 27000, 68000, 68000), true, true);
        Label label4 = new Label("income about 70000", new GaussianFunction(60000, 80000, 70000), true, true);
        Label label5 = new Label("income from 72000 to 98000", new TrapezoidalFunction(72000, 98000, 72000, 72000, 98000, 98000), true, true);
        Label label6 = new Label("income about 100000", new GaussianFunction(90000, 110000, 100000), true, true);
        Label label7 = new Label("income more than 102000", new TrapezoidalFunction(102000, 950000, 102000, 102000, 950000, 950000), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5, label6, label7));
    }
    public ArrayList<Label> initLinguisticLabels4() {
        Label label1 = new Label("normal amount of requests", new TrapezoidalFunction(0, 3, 0, 0, 3, 3), true, true);
        Label label2 = new Label("abnormal amount of requests", new TrapezoidalFunction(3, 35, 3, 3, 35, 35), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2));
    }
    public ArrayList<Label> initLinguisticLabels5() {
        Label label1 = new Label("installment less than 100", new TrapezoidalFunction(16, 115, 15, 57.5, 57.5, 115), true, true);
        Label label2 = new Label("installment from 100 to 400", new TrapezoidalFunction(57.5, 400, 57.5, 115, 275, 400), true, true);
        Label label3 = new Label("installment from 300 to 700", new TrapezoidalFunction(275, 1000, 275, 400, 700, 1000), true, true);
        Label label4 = new Label("installment about 1000", new TrapezoidalFunction(700, 1500, 700, 1000, 1200, 1500), true, true);
        Label label5 = new Label("installment more than 1200", new TrapezoidalFunction(1200, 1500, 1200, 1500, 1500, 1500), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5));
    }
    public ArrayList<Label> initLinguisticLabels6() {
        Label label1 = new Label("very low DTI", new TrapezoidalFunction(0, 20, 0, 0, 10, 20), true, true);
        Label label2 = new Label("low DTI", new TrapezoidalFunction(10, 60, 10, 20, 40, 60), true, true);
        Label label3 = new Label("medium DTI", new TrapezoidalFunction(40, 100, 40, 60, 80, 100), true, true);
        Label label4 = new Label("high DTI", new TrapezoidalFunction(70, 110, 70, 80, 90, 110), true, true);
        Label label5 = new Label("very high DTI", new TrapezoidalFunction(80, 100, 80, 100, 100, 100), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5));
    }
    public ArrayList<Label> initLinguisticLabels7() {
        Label label1 = new Label("loan balance less than 1500", new TrapezoidalFunction(0, 1500, 0, 0, 1500, 1500), true, true);
        Label label2 = new Label("loan balance about 2000", new GaussianFunction(500, 3500, 2000), true, true);
        Label label3 = new Label("loan balance about 3000", new GaussianFunction(1500, 4500, 3000), true, true);
        Label label4 = new Label("loan balance about 4000", new GaussianFunction(2500, 5500, 4000), true, true);
        Label label5 = new Label("loan balance more than 4000", new TrapezoidalFunction(4000, 3000000, 4000, 4000, 3000000, 3000000), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5));
    }
    public ArrayList<Label> initLinguisticLabels8() {
        Label label1 = new Label("the amount of widiction not more than 1500", new TrapezoidalFunction(0, 1500, 0, 0, 1500, 1500), true, true);
        Label label2 = new Label("the amount of widiction from 1500 to 5000", new TrapezoidalFunction(1500, 5000, 1500, 1500, 5000, 5000), true, true);
        Label label3 = new Label("the amount of widiction more than 5000", new TrapezoidalFunction(5000, 99950, 5000, 5000, 99950, 99950), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3));
    }
    public ArrayList<Label> initLinguisticLabels9() {
        Label label1 = new Label("not more than 4000", new TrapezoidalFunction(100, 4000, 100, 100, 3000, 4000), true, true);
        Label label2 = new Label("from 3000 to 20000", new TrapezoidalFunction(3000, 20000, 3000, 4000, 15000, 20000), true, true);
        Label label3 = new Label("more than 15000", new TrapezoidalFunction(15000, 99999, 15000, 20000, 99999, 99999), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3));
    }
    public ArrayList<Label> initLinguisticLabels10() {
        Label label1 = new Label("account balance less than 10000", new TrapezoidalFunction(0, 10000, 0, 0, 9000, 10000), true, true);
        Label label2 = new Label("account balance from 9000 to 50000", new TrapezoidalFunction(9000, 50000, 9000, 10000, 45000, 50000), true, true);
        Label label3 = new Label("account balance from 45000 to 100000", new TrapezoidalFunction(45000, 100000, 45000, 50000, 95000, 100000), true, true);
        Label label4 = new Label("account balance more than 9500", new TrapezoidalFunction(95000, 999999, 95000, 100000, 99999, 99999), true, true);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4));
    }
}
