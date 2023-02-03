package com.company;

public class BusNumberManager {
    public static final int NUMBER_OF_DIGITS_IN_BUS = 4;
    public static int MAXIMUM_NUMBER = (int)(Math.random() * Math.pow(9, 4) + 1);

    private static BusNumberManager instance = null;
    private int currentBusNumber = 0;

    private BusNumberManager() {}

    public static BusNumberManager getInstance() {
        if (instance == null) {
            instance = new BusNumberManager();
        }
        return instance;
    }

    public String getNewBusNumber() {
        StringBuilder newBusNumber = new StringBuilder();
        for (int tempBusNumber = currentBusNumber; tempBusNumber > 0; tempBusNumber /= 9) {
            newBusNumber.append((char)('1' + tempBusNumber % 9));
        }
        return newBusNumber.toString();
    }
}