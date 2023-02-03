package com.company;

public class Taxi extends Vehicle {
    public static final int MIN_TAXI_FUEL_REQUIRED = 10;
    public static final int MAX_TAXI_PASSENGER = 4;
    public static final int INIT_TAXI_FUEL = 100;
    public static final int INIT_TAXI_PASSENGER = 0;
    public static final int INIT_TAXI_CURRENT_SPEED = 0;
    public static final int TAXI_FARE_PER_MILE = 300;
    public static final long INIT_TAXI_FARE = 3000;

    public static final String WARNING_MESSAGE_TAXI_FUEL_LACK_CHANGING_STATUS = "주유가 필요하다.";
    public static final String WARNING_MESSAGE_TAXI_FUEL_LACK_SPEEDING_UP = "주유량을 확인해 주세요.";

    public enum TaxiState {
        FULL,
        EMPTY,
        NOT_RUNNABLE,
    }

    private TaxiState taxiState;
    private long mileage;

    public Taxi() {
        super(
                BusNumberManager.getInstance().getNewBusNumber(),
                INIT_TAXI_PASSENGER,
                INIT_TAXI_FUEL,
                INIT_TAXI_CURRENT_SPEED,
                INIT_TAXI_FARE
        );
        this.startRunning();
    }

    @Override
    void startRunning() {
        if (this.fuel >= MIN_TAXI_FUEL_REQUIRED) {
            this.taxiState = TaxiState.EMPTY;
        }
        else {
            this.taxiState = TaxiState.NOT_RUNNABLE;
        }
    }

    @Override
    void takePassenger(int newPassenger) {
        if (this.taxiState == TaxiState.EMPTY && this.passenger + newPassenger <= MAX_TAXI_PASSENGER) {
            this.taxiState = TaxiState.FULL;
            this.passenger += newPassenger;
        }
    }

    @Override
    void addSpeed(int addedSpeed) {
        this.currentSpeed += addedSpeed;
    }

    long calculateTotalFare() {
        this.mileage = 0;
        this.startRunning();
        long totalFare = INIT_TAXI_FARE + mileage * TAXI_FARE_PER_MILE;
        return totalFare;
    }
}
