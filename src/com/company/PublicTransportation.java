package com.company;

import java.util.LinkedList;
import java.util.Queue;

public abstract class PublicTransportation<E extends Enum> {
    public static final int INIT_FUEL = 100;
    public static final int INIT_CURRENT_SPEED = 0;
    public static final int INIT_PASSENGER_COUNT = 0;

    public static final String WARNING_MESSAGE_EXCEEDING_PASSENGERS = "최대 승객 수 초과";
    public static final String WARNING_MESSAGE_FUEL_LACK_CHANGING_FUEL = "주유 필요";

    protected int fuel = INIT_FUEL;
    protected int currentSpeed = INIT_CURRENT_SPEED;
    protected int passengerCount =INIT_PASSENGER_COUNT;
    protected String vehicleNumber;
    protected long fare;
    protected E state;
    protected Queue<String> warningMessageQueue = new LinkedList<>();

    public void showFuel() {
        System.out.println("주유량 = " + fuel);
    }

    public void showCurrentSpeed() {
        System.out.println("속도 = " + currentSpeed);
    }

    public void showVehicleNumber() {
        System.out.println("차량 번호 = " + vehicleNumber);
    }

    public void showFare() {
        System.out.println("누적 요금 확인 = " + fare);
    }

    public void showPassengerCount() {
        System.out.println("탑승 승객 수 : " + this.passengerCount);
    }

    public abstract void showState();

    public abstract void showSeatsLeft();

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
}
