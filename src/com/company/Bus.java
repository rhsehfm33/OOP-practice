package com.company;

public class Bus extends PublicTransportation<Bus.BusState> {
    public enum BusState {
        AVAILABLE("운행중"),
        NOT_AVAILABLE("차고지행");

        private String description;

        BusState(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static final int MIN_BUS_FUEL_REQUIRED = 10;
    public static final int MAX_BUS_PASSENGER_COUNT = 30;
    public static final long INIT_BUS_FARE = 0;
    public static final long BUS_FARE_PER_PASSENGER = 1000;

    public static final String WARNING_MESSAGE_BUS_FUEL_LACK_CHANGING_SPEED = "주유량을 확인해 주세요.";

    public Bus() {
        super(
                BusNumberManager.getInstance().getNewBusNumber(),
                INIT_BUS_FARE
        );
        this.state = BusState.AVAILABLE;
    }

    public void showCurrentFare() {
        System.out.println("지불할 요금 = " + (this.passengerCount * BUS_FARE_PER_PASSENGER));
    }

    @Override
    public void showSeatsLeft() {
        System.out.println("잔여 승객 수 = " + (MAX_BUS_PASSENGER_COUNT - this.passengerCount));
    }

    @Override
    public void showState() {
        System.out.println("상태 = " + this.state.getDescription());
    }

    @Override
    public void changeState(BusState newBusState) {
        switch (newBusState) {
            case AVAILABLE:
                if (this.fuel >= MIN_BUS_FUEL_REQUIRED) {
                    this.state = BusState.AVAILABLE;
                }
                else {
                    this.state = BusState.NOT_AVAILABLE;
                }
                break;

            case NOT_AVAILABLE:
                this.unloadPassenger();
                this.state = BusState.NOT_AVAILABLE;
                break;
        }
    }

    @Override
    public void changeSpeed(int speedDifference) {
        if (this.fuel >= MIN_BUS_FUEL_REQUIRED) {
            this.currentSpeed += speedDifference;
        }
        else {
            this.warningMessageQueue.add(WARNING_MESSAGE_BUS_FUEL_LACK_CHANGING_SPEED);
        }
    }

    @Override
    public void changeFuel(int fuelDifference) {
        this.fuel += fuelDifference;
        if (this.fuel < MIN_BUS_FUEL_REQUIRED) {
            this.warningMessageQueue.add(WARNING_MESSAGE_FUEL_LACK_CHANGING_FUEL);
            this.state = BusState.NOT_AVAILABLE;
        }
    }


    public void loadPassenger(int newPassengerCount) {
        if (this.passengerCount + newPassengerCount <= MAX_BUS_PASSENGER_COUNT &&
            this.state == BusState.AVAILABLE) {
            this.fare += (newPassengerCount * BUS_FARE_PER_PASSENGER);
            this.passengerCount = newPassengerCount;
        }
        else {
            this.warningMessageQueue.add(PublicTransportation.WARNING_MESSAGE_EXCEEDING_PASSENGERS);
        }
    }

    protected void unloadPassenger() {
        this.passengerCount = 0;
    }
}
