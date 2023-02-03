package com.company;

public class TaxiPassengerInfo extends PassengerInfo {
    String destination;
    long mileage;
    TaxiPassengerInfo(int passengerCount, String destination, long mileage) {
        super(passengerCount);
        this.destination = destination;
        this.mileage = mileage;
    }
}
