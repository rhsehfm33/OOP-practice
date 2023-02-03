package com.company;

public abstract class Vehicle {
    protected String vehicleNumber;
    protected int passenger;
    protected int fuel;
    protected int currentSpeed;
    protected long fare;

    protected Vehicle(String vehicleNumber, int passenger, int fuel, int currentSpeed, long fare) {
        this.vehicleNumber = vehicleNumber;
        this.passenger = passenger;
        this.fuel = fuel;
        this.currentSpeed = currentSpeed;
        this.fare = fare;
    }

    abstract void startRunning();

    abstract void takePassenger(int newPassenger);

    abstract void addSpeed(int addedSpeed);
}
