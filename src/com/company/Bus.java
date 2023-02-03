package com.company;

public class Bus extends PublicTransportation<Bus.BusState, BusPassengerInfo> {
    public enum BusState {
        AVAILABLE,
        NOT_AVAILABLE
    }

    public static final int MIN_BUS_FUEL_REQUIRED = 10;
    public static final int MAX_BUS_PASSENGER_COUNT = 30;
    public static final long INIT_BUS_FARE = 0;
    public static final long DEFAULT_BUS_FARE = 0;

    public static final String WARNING_MESSAGE_BUS_FUEL_LACK_CHANGING_SPEED = "주유량을 확인해 주세요.";

    public Bus() {
        super(
                BusNumberManager.getInstance().getNewBusNumber(),
                INIT_BUS_FARE
        );
        this.state = BusState.AVAILABLE;
        this.passengerInfo = new BusPassengerInfo(0);
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
        this.fuel -= fuelDifference;
        if (this.fuel < MIN_BUS_FUEL_REQUIRED) {
            this.warningMessageQueue.add(WARNING_MESSAGE_FUEL_LACK_CHANGING_FUEL);
            this.state = BusState.NOT_AVAILABLE;
        }
    }


    @Override
    public void loadPassenger(BusPassengerInfo newBusPassengerInfo) {
        if (this.passengerInfo.passengerCount + newBusPassengerInfo.passengerCount <= MAX_BUS_PASSENGER_COUNT &&
            this.state == BusState.AVAILABLE) {
            this.fare += (newBusPassengerInfo.passengerCount * DEFAULT_BUS_FARE);
            this.passengerInfo.passengerCount = newBusPassengerInfo.passengerCount;
        }
        else {
            this.warningMessageQueue.add(PublicTransportation.WARNING_MESSAGE_EXCEEDING_PASSENGERS);
        }
    }

    @Override
    protected void unloadPassenger() {
        this.passengerInfo.passengerCount = 0;
    }
}
