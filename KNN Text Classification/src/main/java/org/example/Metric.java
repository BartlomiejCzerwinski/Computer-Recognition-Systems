package org.example;

public abstract class Metric {

    public float calculateTextsDistance(String text1, String text2) {
        return 1.0f - calculateGeneralizedNGramMeasure(text1, text2);
    }

    private float calculateGeneralizedNGramMeasure(String s1, String s2) {
        float measure1 = calculateNGramMeasure(s1, s2);
        float measure2 = calculateNGramMeasure(s2, s1);
        if (measure1 < measure2)
            return measure1;
        else
            return measure2;
    }
    private float calculateNGramMeasure(String s1, String s2) {
        int N, n;
        String shorter, longer;
        if (s1.length() >= s2.length()) {
            N = s1.length();
            n = s2.length();
            longer = s1;
            shorter = s2;
        } else {
            N = s2.length();
            n = s1.length();
            longer = s2;
            shorter = s1;
        }

        float f = calculateF(N, n);
        int h = 0;

        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < n-i+1; j++) {
                String substring = shorter.substring(j, j+i);
                if (longer.contains(substring))
                    h ++;
            }
        }
        return h*f;
    }

    private float calculateF(int N, int n) {
        return 2.0f / ((((float) N - 3f + 1f) * ((float) N - 3f + 2f)) - (((float) N - (float) n + 1f) * ((float) N - (float) n)));
    }
}
