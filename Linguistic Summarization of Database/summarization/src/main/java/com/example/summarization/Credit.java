package com.example.summarization;

public class Credit {
    private float amount;
    private float intRate;
    private float annualIncome;
    private int numberOfQuestions;
    private float installment;
    private int dti;
    private float revolBalance;
    private float totalCollAmount;
    private float creditLimit;
    private float totalAccountsBalance;

    public Credit(float amount, float intRate, float annualIncome, int numberOfQuestions, float installment, int dti, float revolBalance, float totalCollAmount, float creditLimit, float totalAccountsBalance) {
        this.amount = amount;
        this.intRate = intRate;
        this.annualIncome = annualIncome;
        this.numberOfQuestions = numberOfQuestions;
        this.installment = installment;
        this.dti = dti;
        this.revolBalance = revolBalance;
        this.totalCollAmount = totalCollAmount;
        this.creditLimit = creditLimit;
        this.totalAccountsBalance = totalAccountsBalance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Credit{");
        sb.append("amount=").append(amount);
        sb.append(", intRate=").append(intRate);
        sb.append(", annualIncome=").append(annualIncome);
        sb.append(", numberOfQuestions=").append(numberOfQuestions);
        sb.append(", installment=").append(installment);
        sb.append(", dti=").append(dti);
        sb.append(", revolBalance=").append(revolBalance);
        sb.append(", totalCollAmount=").append(totalCollAmount);
        sb.append(", creditLimit=").append(creditLimit);
        sb.append(", totalAccountsBalance=").append(totalAccountsBalance);
        sb.append('}');
        return sb.toString();
    }

    public float getAmount() {
        return amount;
    }

    public float getIntRate() {
        return intRate;
    }

    public float getAnnualIncome() {
        return annualIncome;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public float getInstallment() {
        return installment;
    }

    public int getDti() {
        return dti;
    }

    public float getRevolBalance() {
        return revolBalance;
    }

    public float getTotalCollAmount() {
        return totalCollAmount;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public float getTotalAccountsBalance() {
        return totalAccountsBalance;
    }

    public double getValueByColumnIndex(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return getAmount();
            case 1:
                return getIntRate();
            case 2:
                return getAnnualIncome();
            case 3:
                return getNumberOfQuestions();
            case 4:
                return getInstallment();
            case 5:
                return getDti();
            case 6:
                return getRevolBalance();
            case 7:
                return getTotalCollAmount();
            case 8:
                return getCreditLimit();
            case 9:
                return getTotalAccountsBalance();
        }
        return 0.0;
    }
}
