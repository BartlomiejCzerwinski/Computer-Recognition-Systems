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

    private ArrayList<ArrayList<Label>> LINGUISTIC_LABELS = new ArrayList<>();


    public ArrayList<LinguisticVariable> getAllLinguisticVariables() {
        ArrayList<LinguisticVariable> linguisticVariables = new ArrayList<>();
        initLinguisticLabels();
        for (int i = 0; i < NUMBER_OF_VARIABLES; i++) {
            linguisticVariables.add(new LinguisticVariable(LINGUISTIC_VARIABLE_NAMES[i], LINGUISTIC_LABELS.get(i), L[i], R[i]));
        }
        return linguisticVariables;
    }

    public ArrayList<LinguisticVariable> getSingleLinguisticVariable(String name) {
        ArrayList<LinguisticVariable> linguisticVariables = getAllLinguisticVariables();
        for (LinguisticVariable linguisticVariable : linguisticVariables) {
            if (linguisticVariable.getName() == name)
                return new ArrayList<LinguisticVariable>(Arrays.asList(linguisticVariable));
        }
        return null;
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
        Label label1 = new Label("a loan for less than 10000", new TrapezoidalFunction(300, 10000, UniverseOfDiscourse.CONTINUOUS, 300, 300, 10000, 10000), true, true, 0);
        Label label2 = new Label("loan for about 11000", new TrapezoidalFunction(9500, 12500, UniverseOfDiscourse.CONTINUOUS, 9500, 10500, 11500, 12500), true, true, 0);
        Label label3 = new Label("loan from 12000 to 18000", new TrapezoidalFunction(12000, 18000, UniverseOfDiscourse.CONTINUOUS, 12000, 12000, 18000, 18000), true, true, 0);
        Label label4 = new Label("loan for about 20000", new TrapezoidalFunction(17000, 23000, UniverseOfDiscourse.CONTINUOUS, 17000, 19000, 21000, 23000), true, true, 0);
        Label label5 = new Label("loan from 22000 to 30000", new TrapezoidalFunction(22000, 30000, UniverseOfDiscourse.CONTINUOUS, 22000, 22000, 30000, 30000), true, true, 0);
        Label label6 = new Label("a loan for more than 30000", new TrapezoidalFunction(30000, 40000, UniverseOfDiscourse.CONTINUOUS, 30000, 30000, 40000, 40000), true, true, 0);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5, label6));
    }
    public ArrayList<Label> initLinguisticLabels2() {
        Label label1 = new Label("interest rate no more than 10%", new TrapezoidalFunction(5.32, 10, UniverseOfDiscourse.CONTINUOUS, 5.32, 5.32, 8, 10), true, true, 1);
        Label label2 = new Label("interest rate from 9% to 20%", new TrapezoidalFunction(9, 20, UniverseOfDiscourse.CONTINUOUS, 9, 9, 20, 29), true, true, 1);
        Label label3 = new Label("interest rate more than 20%", new TrapezoidalFunction(20, 30, UniverseOfDiscourse.CONTINUOUS, 20, 20, 30, 30), true, true, 1);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3));
    }
    public ArrayList<Label> initLinguisticLabels3() {
        Label label1 = new Label("income up to 23000", new TrapezoidalFunction(0, 23000, UniverseOfDiscourse.CONTINUOUS, 0, 0, 23000, 23000), true, true, 2);
        Label label2 = new Label("income about 25000", new GaussianFunction(15000, 35000, UniverseOfDiscourse.CONTINUOUS, 25000, 2), true, true, 2);
        Label label3 = new Label("income from 27000 to 68000", new TrapezoidalFunction(27000,  68000, UniverseOfDiscourse.CONTINUOUS, 27000, 27000, 68000, 68000), true, true, 2);
        Label label4 = new Label("income about 70000", new GaussianFunction(60000, 80000, UniverseOfDiscourse.CONTINUOUS, 70000, 2), true, true, 2);
        Label label5 = new Label("income from 72000 to 98000", new TrapezoidalFunction(72000, 98000, UniverseOfDiscourse.CONTINUOUS, 72000, 72000, 98000, 98000), true, true, 2);
        Label label6 = new Label("income about 100000", new GaussianFunction(90000, 110000, UniverseOfDiscourse.CONTINUOUS, 100000, 2), true, true, 2);
        Label label7 = new Label("income more than 102000", new TrapezoidalFunction(102000, 950000, UniverseOfDiscourse.CONTINUOUS,102000, 102000, 950000, 950000), true, true, 2);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5, label6, label7));
    }
    public ArrayList<Label> initLinguisticLabels4() {
        Label label1 = new Label("normal amount of requests", new TrapezoidalFunction(0, 3, UniverseOfDiscourse.DISCRETE, 0, 0, 3, 3), true, true, 3);
        Label label2 = new Label("abnormal amount of requests", new TrapezoidalFunction(3, 35, UniverseOfDiscourse.DISCRETE, 3, 3, 35, 35), true, true, 3);
        return new ArrayList<>(Arrays.asList(label1, label2));
    }
    public ArrayList<Label> initLinguisticLabels5() {
        Label label1 = new Label("installment less than 100", new TrapezoidalFunction(16, 115, UniverseOfDiscourse.CONTINUOUS,15, 57.5, 57.5, 115), true, true, 4);
        Label label2 = new Label("installment from 100 to 400", new TrapezoidalFunction(57.5, 400, UniverseOfDiscourse.CONTINUOUS, 57.5, 115, 275, 400), true, true, 4);
        Label label3 = new Label("installment from 300 to 700", new TrapezoidalFunction(275, 1000, UniverseOfDiscourse.CONTINUOUS, 275, 400, 700, 1000), true, true, 4);
        Label label4 = new Label("installment about 1000", new TrapezoidalFunction(700, 1500, UniverseOfDiscourse.CONTINUOUS, 700, 1000, 1200, 1500), true, true, 4);
        Label label5 = new Label("installment more than 1200", new TrapezoidalFunction(1200, 1500, UniverseOfDiscourse.CONTINUOUS, 1200, 1500, 1500, 1500), true, true, 4);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5));
    }
    public ArrayList<Label> initLinguisticLabels6() {
        Label label1 = new Label("very low DTI", new TrapezoidalFunction(0, 20, UniverseOfDiscourse.CONTINUOUS, 0, 0, 10, 20), true, true, 5);
        Label label2 = new Label("low DTI", new TrapezoidalFunction(10, 60, UniverseOfDiscourse.CONTINUOUS, 10, 20, 40, 60), true, true, 5);
        Label label3 = new Label("medium DTI", new TrapezoidalFunction(40, 100, UniverseOfDiscourse.CONTINUOUS, 40, 60, 80, 100), true, true, 5);
        Label label4 = new Label("high DTI", new TrapezoidalFunction(70, 110, UniverseOfDiscourse.CONTINUOUS, 70, 80, 90, 110), true, true, 5);
        Label label5 = new Label("very high DTI", new TrapezoidalFunction(80, 100, UniverseOfDiscourse.CONTINUOUS, 80, 100, 100, 100), true, true, 5);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5));
    }
    public ArrayList<Label> initLinguisticLabels7() {
        Label label1 = new Label("loan balance less than 1500", new TrapezoidalFunction(0, 1500, UniverseOfDiscourse.CONTINUOUS, 0, 0, 1500, 1500), true, true, 6);
        Label label2 = new Label("loan balance about 2000", new GaussianFunction(500, 3500, UniverseOfDiscourse.CONTINUOUS,2000, 500), true, true, 6);
        Label label3 = new Label("loan balance about 3000", new GaussianFunction(1500, 4500, UniverseOfDiscourse.CONTINUOUS, 3000, 500), true, true, 6);
        Label label4 = new Label("loan balance about 4000", new GaussianFunction(2500, 5500, UniverseOfDiscourse.CONTINUOUS, 4000, 500), true, true, 6);
        Label label5 = new Label("loan balance more than 4000", new TrapezoidalFunction(4000, 3000000, UniverseOfDiscourse.CONTINUOUS, 4000, 4000, 3000000, 3000000), true, true, 6);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4, label5));
    }
    public ArrayList<Label> initLinguisticLabels8() {
        Label label1 = new Label("the amount of vindication not more than 1500", new TrapezoidalFunction(0, 1500, UniverseOfDiscourse.CONTINUOUS, 0, 0, 1500, 1500), true, true, 7);
        Label label2 = new Label("the amount of vindication from 1500 to 5000", new TrapezoidalFunction(1500, 5000, UniverseOfDiscourse.CONTINUOUS, 1500, 1500, 5000, 5000), true, true, 7);
        Label label3 = new Label("the amount of vindication more than 5000", new TrapezoidalFunction(5000, 99950, UniverseOfDiscourse.CONTINUOUS, 5000, 5000, 99950, 99950), true, true, 7);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3));
    }
    public ArrayList<Label> initLinguisticLabels9() {
        Label label1 = new Label("credit limit not more than 4000", new TrapezoidalFunction(100, 4000, UniverseOfDiscourse.CONTINUOUS, 100, 100, 3000, 4000), true, true, 8);
        Label label2 = new Label("credit limit from 3000 to 20000", new TrapezoidalFunction(3000, 20000, UniverseOfDiscourse.CONTINUOUS, 3000, 4000, 15000, 20000), true, true, 8);
        Label label3 = new Label("credit limit more than 15000", new TrapezoidalFunction(15000, 99999, UniverseOfDiscourse.CONTINUOUS, 15000, 20000, 99999, 99999), true, true, 8);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3));
    }
    public ArrayList<Label> initLinguisticLabels10() {
        Label label1 = new Label("account balance less than 10000", new TrapezoidalFunction(0, 10000, UniverseOfDiscourse.CONTINUOUS, 0, 0, 9000, 10000), true, true, 9);
        Label label2 = new Label("account balance from 9000 to 50000", new TrapezoidalFunction(9000, 50000, UniverseOfDiscourse.CONTINUOUS, 9000, 10000, 45000, 50000), true, true, 9);
        Label label3 = new Label("account balance from 45000 to 100000", new TrapezoidalFunction(45000, 100000, UniverseOfDiscourse.CONTINUOUS, 45000, 50000, 95000, 100000), true, true, 9);
        Label label4 = new Label("account balance more than 9500", new TrapezoidalFunction(95000, 999999, UniverseOfDiscourse.CONTINUOUS, 95000, 100000, 99999, 99999), true, true, 9);
        return new ArrayList<>(Arrays.asList(label1, label2, label3, label3, label4));
    }

    public ArrayList<Quantifier> createQuantifiers() {
        Label label1 = new Label("a little", new GaussianFunction(0, 0.4, UniverseOfDiscourse.CONTINUOUS, 0.05, 0.1), true, true, -1);
        Label label2 = new Label("not a lot", new GaussianFunction(0, 0.9, UniverseOfDiscourse.CONTINUOUS, 0.2, 0.2), true, true, -1);
        Label label3 = new Label("about half of all", new GaussianFunction(0.3, 0.7, UniverseOfDiscourse.CONTINUOUS, 0.5, 0.05), true, true, -1);
        Label label4 = new Label("more than half", new GaussianFunction(0.4, 1, UniverseOfDiscourse.CONTINUOUS, 0.7, 0.1), true, true, -1);
        Label label5 = new Label("almost all", new GaussianFunction(0.7, 1, UniverseOfDiscourse.CONTINUOUS, 0.9, 0.05), true, true, -1);
        ArrayList<Quantifier> quantifiers = new ArrayList<>();
        quantifiers.add(new Quantifier("number of objects", label1, false));
        quantifiers.add(new Quantifier("number of objects", label2, false));
        quantifiers.add(new Quantifier("number of objects", label3, false));
        quantifiers.add(new Quantifier("number of objects", label4, false));
        quantifiers.add(new Quantifier("number of objects", label5, false));
        return quantifiers;
    }

    public ArrayList<String> getLinguisticLabelsNamesList() {
        ArrayList<String> names = new ArrayList<>();
        for (LinguisticVariable linguisticVariable : getAllLinguisticVariables()) {
            for (Label label : linguisticVariable.getLabels()) {
                names.add(label.getName());
            }
        }
        names.add("----");
        return names;
    }
}
