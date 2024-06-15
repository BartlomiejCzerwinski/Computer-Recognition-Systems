package com.example.summarization;

public enum ColumnVariableEnum {
    CREDIT_AMOUNT("kwota kredytu"),
    INT_RATE("kwota oprocentowania"),
    ANNUAL_INCOME("wysokość przychodów"),
    NUMBER_OF_QUESTIONS("liczba zapytań"),
    INSTALLMENT("wysokość raty"),
    DTI("dti"),
    REVOL_BALANCE("saldo kredytów odnawialnych"),
    TOTAL_COLL_AMOUNT("łączna kwota windykacji"),
    CREDIT_LIMIT("limit kredytowy"),
    TOTAL_ACCOUNT_BALANCE("saldo wszystkich kont");

    private String value;

    ColumnVariableEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getColumnIndex(ColumnVariableEnum columnVariableEnum) {
        switch (columnVariableEnum) {
            case CREDIT_AMOUNT -> {
                return 0;
            }
            case INT_RATE -> {
                return 1;
            }
            case ANNUAL_INCOME -> {
                return 2;
            }
            case NUMBER_OF_QUESTIONS -> {
                return 3;
            }
            case INSTALLMENT -> {
                return 4;
            }
            case DTI -> {
                return 5;
            }
            case REVOL_BALANCE -> {
                return 6;
            }
            case TOTAL_COLL_AMOUNT -> {
                return 7;
            }
            case CREDIT_LIMIT -> {
                return 8;
            }
            case TOTAL_ACCOUNT_BALANCE -> {
                return 9;
            }
        }
        return -1;
    }

    public static ColumnVariableEnum fromString(String text) {
        for (ColumnVariableEnum variable : ColumnVariableEnum.values()) {
            if (variable.value.equalsIgnoreCase(text)) {
                return variable;
            }
        }
        return null;
    }
}
