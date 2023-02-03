package com.company;

public class Bus extends Vehicle {
    public static final int MIN_BUS_FUEL_REQUIRED = 10;
    public static final int MAX_BUS_PASSENGER = 25;
    public static final int INIT_BUS_FUEL = 100;
    public static final int INIT_BUS_PASSENGER = 0;
    public static final int INIT_BUS_CURRENT_SPEED = 0;
    public static final long INIT_BUS_FARE = 1200;

    public static final String WARNING_MESSAGE_BUS_FUEL_LACK_CHANGING_STATUS = "주유가 필요하다.";
    public static final String WARNING_MESSAGE_BUS_FUEL_LACK_SPEEDING_UP = "주유량을 확인해 주세요.";

    public enum BusState {
        USABLE,
        NOT_RUNNABLE
    }

    private BusState busState;

    public Bus() {
        super(
                BusNumberManager.getInstance().getNewBusNumber(),
                INIT_BUS_PASSENGER,
                INIT_BUS_FUEL,
                INIT_BUS_CURRENT_SPEED,
                INIT_BUS_FARE
        );
        this.startRunning();
    }

    @Override
    void startRunning() {
        if (this.fuel >= MIN_BUS_FUEL_REQUIRED) {
            this.busState = BusState.USABLE;
        }
        else {
            System.out.println(WARNING_MESSAGE_BUS_FUEL_LACK_CHANGING_STATUS);
        }
    }

    @Override
    void takePassenger(int newPassenger) {
        if (this.passenger + newPassenger <= MAX_BUS_PASSENGER && this.busState == BusState.USABLE) {
            this.passenger += newPassenger;
        }
    }

    @Override
    void addSpeed(int addedSpeed) {
        if (this.fuel >= MIN_BUS_FUEL_REQUIRED) {
            this.currentSpeed += addedSpeed;
        }
        else {
            System.out.println(WARNING_MESSAGE_BUS_FUEL_LACK_SPEEDING_UP);
        }
    }
}
