package com.company;

public class Taxi extends PublicTransportation<Taxi.TaxiState> {
    public enum TaxiState {
        FULL("운행중"),
        EMPTY("일반"),
        NOT_AVAILABLE("운행 불가");

        private String description;

        TaxiState(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static final int MIN_TAXI_FUEL_REQUIRED = 10;
    public static final int MAX_TAXI_PASSENGER_COUNT = 4;
    public static final long INIT_TAXI_FARE = 0;
    public static final long DEFAULT_TAXI_FARE = 3000;
    public static final long MILER_PER_TAXI_FARE = 1000;
    public static final long INIT_MILEAGE = 1;
    public static final String INIT_DESTINATION = "";

    protected long mileage;
    protected String destination;

    public Taxi() {
        super(
                TaxiNumberManager.getInstance().getNewTaxiNumber(),
                INIT_TAXI_FARE
        );
        this.changeState(TaxiState.EMPTY);
        this.mileage = INIT_MILEAGE;
        this.destination = INIT_DESTINATION;
    }

    public void showInitTaxiFare() {
        System.out.println("기본 요금 확인 = " + DEFAULT_TAXI_FARE);
    }

    public void showDestination() {
        System.out.println("목적지 = " + destination);
    }

    public void showMileage() {
        System.out.println("목적지까지 거리 = " + mileage + "km");
    }

    public void showCurrentFare() {
        System.out.println("지불할 요금 = " + (DEFAULT_TAXI_FARE + (this.mileage - INIT_MILEAGE) * MILER_PER_TAXI_FARE));
    }

    @Override
    public void showSeatsLeft() {
        System.out.println("잔여 승객 수 = " + (MAX_TAXI_PASSENGER_COUNT - this.passengerCount));
    }

    @Override
    public void showState() {
        System.out.println("상태 = " + this.state.getDescription());
    }

    @Override
    public void changeState(TaxiState newTaxiState) {
        switch (newTaxiState) {
            case FULL:
                this.state = TaxiState.FULL;
                break;

            case EMPTY:
                this.passengerCount = INIT_PASSENGER_COUNT;
                this.mileage = INIT_MILEAGE;
                this.destination = INIT_DESTINATION;

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
        this.fuel += fuelDifference;
        if (this.fuel < MIN_TAXI_FUEL_REQUIRED) {
            this.warningMessageQueue.add(WARNING_MESSAGE_FUEL_LACK_CHANGING_FUEL);
            this.changeState(TaxiState.NOT_AVAILABLE);
        }
    }

    protected void loadPassenger(int newPassengerCount, long newMileage, String newDestination) {
        if (newPassengerCount > MAX_TAXI_PASSENGER_COUNT) {
            this.warningMessageQueue.add(WARNING_MESSAGE_EXCEEDING_PASSENGERS);
            return;
        }
        this.changeState(TaxiState.FULL);
        this.passengerCount = newPassengerCount;
        this.mileage = newMileage;
        this.destination = newDestination;
        fare += DEFAULT_TAXI_FARE + (newMileage - INIT_MILEAGE) * MILER_PER_TAXI_FARE;
    }

    protected void unloadPassenger() {
        this.passengerCount = 0;
        this.destination = INIT_DESTINATION;
        this.mileage = INIT_MILEAGE;
    }
}
