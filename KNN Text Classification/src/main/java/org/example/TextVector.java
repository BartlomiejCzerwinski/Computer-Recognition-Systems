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
}
