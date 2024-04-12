package org.example;

public class TextVector {
    String country;
    String firstBigLettersSeries;
    double relativeNumberOfWordsLongerThan9Signs;
    double relativeNumberOfUniqueWords;
    String mostCommonBigLetterWordInFirst20Percent;
    String mostCommonBigLetterWordInFirst50Percent;
    String mostCommonBigLetterWordInFirst100Percent;
    double relativeNumberOfNumbers;
    String mostCommonBigLettersSeriesInFirst100Percent;
    String mostCommonCurrencyFromDict;
    String mostCommonCountryFromDict;
    String mostCommonContinentFromDict;

    public TextVector(String country, String firstBigLettersSeries,
                      double relativeNumberOfWordsLongerThan9Signs,
                      double relativeNumberOfUniqueWords,
                      String mostCommonBigLetterWordInFirst20Percent,
                      String mostCommonBigLetterWordInFirst50Percent,
                      String mostCommonBigLetterWordInFirst100Percent,
                      double relativeNumberOfNumbers,
                      String mostCommonBigLettersSeriesInFirst100Percent,
                      String mostCommonCurrencyFromDict,
                      String mostCommonCountryFromDict,
                      String mostCommonContinentFromDict) {
        this.country = country;
        this.firstBigLettersSeries = firstBigLettersSeries;
        this.relativeNumberOfWordsLongerThan9Signs = relativeNumberOfWordsLongerThan9Signs;
        this.relativeNumberOfUniqueWords = relativeNumberOfUniqueWords;
        this.mostCommonBigLetterWordInFirst20Percent = mostCommonBigLetterWordInFirst20Percent;
        this.mostCommonBigLetterWordInFirst50Percent = mostCommonBigLetterWordInFirst50Percent;
        this.mostCommonBigLetterWordInFirst100Percent = mostCommonBigLetterWordInFirst100Percent;
        this.relativeNumberOfNumbers = relativeNumberOfNumbers;
        this.mostCommonBigLettersSeriesInFirst100Percent = mostCommonBigLettersSeriesInFirst100Percent;
        this.mostCommonCurrencyFromDict = mostCommonCurrencyFromDict;
        this.mostCommonCountryFromDict = mostCommonCountryFromDict;
        this.mostCommonContinentFromDict = mostCommonContinentFromDict;
    }

    public Object get(int index) {
        switch (index) {
            case 0:
                return firstBigLettersSeries;
            case 1:
                return relativeNumberOfWordsLongerThan9Signs;
            case 2:
                return relativeNumberOfUniqueWords;
            case 3:
                return mostCommonBigLetterWordInFirst20Percent;
            case 4:
                return mostCommonBigLetterWordInFirst50Percent;
            case 5:
                return mostCommonBigLetterWordInFirst100Percent;
            case 6:
                return relativeNumberOfNumbers;
            case 7:
                return mostCommonBigLettersSeriesInFirst100Percent;
            case 8:
                return mostCommonCurrencyFromDict;
            case 9:
                return mostCommonCountryFromDict;
            case 10:
                return mostCommonContinentFromDict;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextVector{");
        sb.append("country='").append(country).append('\'');
        sb.append(", firstBigLettersSeries='").append(firstBigLettersSeries).append('\'');
        sb.append(", relativeNumberOfWordsLongerThan9Signs=").append(relativeNumberOfWordsLongerThan9Signs);
        sb.append(", relativeNumberOfUniqueWords=").append(relativeNumberOfUniqueWords);
        sb.append(", mostCommonBigLetterWordInFirst20Percent='").append(mostCommonBigLetterWordInFirst20Percent).append('\'');
        sb.append(", mostCommonBigLetterWordInFirst50Percent='").append(mostCommonBigLetterWordInFirst50Percent).append('\'');
        sb.append(", mostCommonBigLetterWordInFirst100Percent='").append(mostCommonBigLetterWordInFirst100Percent).append('\'');
        sb.append(", relativeNumberOfNumbers=").append(relativeNumberOfNumbers);
        sb.append(", mostCommonBigLettersSeriesInFirst100Percent='").append(mostCommonBigLettersSeriesInFirst100Percent).append('\'');
        sb.append(", mostCommonCurrencyFromDict='").append(mostCommonCurrencyFromDict).append('\'');
        sb.append(", mostCommonCountryFromDict='").append(mostCommonCountryFromDict).append('\'');
        sb.append(", mostCommonContinentFromDict='").append(mostCommonContinentFromDict).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
