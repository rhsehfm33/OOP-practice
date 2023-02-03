package com.company;

public class Taxi extends PublicTransportation<Taxi.TaxiState, TaxiPassengerInfo> {
    public enum TaxiState {
        FULL,
        EMPTY,
        NOT_AVAILABLE,
    }

    public static final int MIN_TAXI_FUEL_REQUIRED = 10;
    public static final int MAX_TAXI_PASSENGER_COUNT = 4;
    public static final int INIT_TAXI_PASSENGER = 0;
    public static final long INIT_TAXI_FARE = 3000;
    public static final long MILER_PER_TAXI_FARE = 1000;
    public static final long INIT_MILEAGE = 1;
    public static final String INIT_DESTINATION = "";

    public static final String WARNING_MESSAGE_TAXI_FUEL_LACK_CHANGING_STATUS = "주유가 필요하다.";
    public static final String WARNING_MESSAGE_TAXI_FUEL_LACK_SPEEDING_UP = "주유량을 확인해 주세요.";

    TaxiPassengerInfo taxiPassengerInfo;

    public Taxi() {
        super(
                BusNumberManager.getInstance().getNewBusNumber(),
                INIT_TAXI_FARE
        );
        this.changeState(TaxiState.EMPTY);
        this.taxiPassengerInfo = new TaxiPassengerInfo(INIT_TAXI_PASSENGER, INIT_DESTINATION, INIT_MILEAGE);
    }

    @Override
    public void changeState(TaxiState newTaxiState) {
        switch (newTaxiState) {
            case FULL:
                this.state = TaxiState.FULL;
                break;

            case EMPTY:
                this.taxiPassengerInfo = null;
                if (this.fuel >= MIN_TAXI_FUEL_REQUIRED) {
                    this.state = TaxiState.EMPTY;
                }
                else {
                    this.state = TaxiState.NOT_AVAILABLE;
                }
                break;

            case NOT_AVAILABLE:
                this.state = TaxiState.NOT_AVAILABLE;
                break;
        }
    }

    @Override
    public void changeSpeed(int speedDifference) {
        this.currentSpeed += speedDifference;
    }

    @Override
    public void changeFuel(int fuelDifference) {
        this.fuel -= fuelDifference;
        if (this.fuel < MIN_TAXI_FUEL_REQUIRED) {
            this.warningMessageQueue.add(WARNING_MESSAGE_FUEL_LACK_CHANGING_FUEL);
            this.changeState(TaxiState.NOT_AVAILABLE);
        }
    }

    @Override
    protected void loadPassenger(TaxiPassengerInfo newPassenger) {
        if (newPassenger.passengerCount > MAX_TAXI_PASSENGER_COUNT) {
            this.warningMessageQueue.add(WARNING_MESSAGE_EXCEEDING_PASSENGERS);
            return;
        }
        this.changeState(TaxiState.FULL);
        this.taxiPassengerInfo = newPassenger;
        fare += INIT_TAXI_FARE + (newPassenger.mileage - INIT_MILEAGE) * MILER_PER_TAXI_FARE;
    }

    @Override
    protected void unloadPassenger() {
        this.taxiPassengerInfo.passengerCount = 0;
        this.taxiPassengerInfo.destination = INIT_DESTINATION;
        this.taxiPassengerInfo.mileage = INIT_MILEAGE;
    }
}
