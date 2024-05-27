package com.example.summarization;

public class SimpleSummary implements Comparable<SimpleSummary> {
    String sentence;
    double T1;
    double T2;
    double T3;
    double T4;
    double T5;
    double T6;
    double T7;
    double T8;
    double T9;
    double T10;
    double T11;
    double T;

    public SimpleSummary(String sentence, double t1, double t2, double t3, double t4, double t5, double t6, double t7, double t8, double t9, double t10, double t11) {
        this.sentence = sentence;
        T1 = t1;
        T2 = t2;
        T3 = t3;
        T4 = t4;
        T5 = t5;
        T6 = t6;
        T7 = t7;
        T8 = t8;
        T9 = t9;
        T10 = t10;
        T11 = t11;
        T = (T1 + T2 + T3 + T4 + T5 + T6 + T6 + T7 + T8 + T9 + T10 + T11)/11.0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleSummary{");
        sb.append("sentence='").append(sentence).append('\'');
        sb.append(", T1=").append(String.format("%.2f", T1));
        sb.append(", T2=").append(String.format("%.2f", T2));
        sb.append(", T3=").append(String.format("%.2f", T3));
        sb.append(", T4=").append(String.format("%.2f", T4));
        sb.append(", T5=").append(String.format("%.2f", T5));
        sb.append(", T6=").append(String.format("%.2f", T6));
        sb.append(", T7=").append(String.format("%.2f", T7));
        sb.append(", T8=").append(String.format("%.2f", T8));
        sb.append(", T9=").append(String.format("%.2f", T9));
        sb.append(", T10=").append(String.format("%.2f", T10));
        sb.append(", T11=").append(String.format("%.2f", T11));
        sb.append(", T=").append(String.format("%.2f", T));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(SimpleSummary other) {
        return Double.compare(this.T, other.T);
    }

}
