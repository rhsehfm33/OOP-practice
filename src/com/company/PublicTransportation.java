package com.company;

import java.util.Queue;

public abstract class PublicTransportation<E extends Enum, P extends PassengerInfo> {
    public static final int INIT_FUEL = 100;
    public static final int INIT_CURRENT_SPEED = 0;

    public static final String WARNING_MESSAGE_EXCEEDING_PASSENGERS = "최대 승객 수 초과.";
    public static final String WARNING_MESSAGE_FUEL_LACK_CHANGING_FUEL = "주유 필요";

    protected int fuel = INIT_FUEL;
    protected int currentSpeed = INIT_CURRENT_SPEED;
    protected String vehicleNumber;
    protected long fare;
    protected E state;
    protected P passengerInfo;
    protected Queue<String> warningMessageQueue;

    protected PublicTransportation(String vehicleNumber, long fare) {
        this.vehicleNumber = vehicleNumber;
        this.fare = fare;
    }

    public void alertAllWarningMessages() {
        while (warningMessageQueue.size() > 0) {
            System.out.println(warningMessageQueue.peek());
            warningMessageQueue.poll();
        }
    }

    protected abstract void changeState(E newState);

    protected abstract void changeSpeed(int speedDifference);

    protected abstract void changeFuel(int fuelDifference);

    protected abstract void loadPassenger(P newPassenger);

    protected abstract void unloadPassenger();
}
