package com.company;

public class TaxiNumberManager {
    public static final int NUMBER_OF_DIGITS_IN_TAXI = 6;
    public static int MAXIMUM_NUMBER = (int)(Math.random() * Math.pow(9, NUMBER_OF_DIGITS_IN_TAXI) + 1);

    private static TaxiNumberManager instance = null;
    private int currentTaxiNumber = 0;

    private TaxiNumberManager() {}

    public static TaxiNumberManager getInstance() {
        if (instance == null) {
            instance = new TaxiNumberManager();
        }
        return instance;
    }

    public String getNewTaxiNumber() {
        StringBuilder newTaxiNumber = new StringBuilder();
        for (int i = 0, tempTaxiNumber = currentTaxiNumber; i < NUMBER_OF_DIGITS_IN_TAXI; ++i) {
            newTaxiNumber.append((char)('1' + tempTaxiNumber % 9));
            tempTaxiNumber /= 9;
        }
        ++currentTaxiNumber;
        return newTaxiNumber.reverse().toString();
    }
}
