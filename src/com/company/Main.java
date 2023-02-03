package com.company;

public class Main {

    public static void testBusClass() {
        // 버스 번호와 차량 번호를 최소 1111~9999 까지 숫자 0을 포함하지 아니한 경우로 잡았음
        // 이런 이유는 실제 세계가 이렇고, 가능한 한 현실적인 상황과 맞게 객체지향을 연습해보기 위함이었음.
        // 1.
        Bus bus1 = new Bus();
        Bus bus2 = new Bus();
        PublicTransportation publicTransportation = new Bus();
        publicTransportation.showCurrentSpeed();

        // 2.
        bus1.showVehicleNumber();
        bus2.showVehicleNumber();

        // 1.
        Bus bus3 = new Bus();
        bus3.loadPassenger(2);

        // 2.
        bus3.showPassengerCount();
        bus3.showSeatsLeft();
        bus3.showCurrentFare();

        // 3.
        bus3.changeFuel(-50);

        // 4.
        bus3.showFuel();

        // 5.
        bus3.changeState(Bus.BusState.NOT_AVAILABLE);

        // 6.
        bus3.changeFuel(10);

        // 7.
        bus3.showState();
        bus3.showFuel();

        // 8.
        bus3.changeState(Bus.BusState.AVAILABLE);

        // 9.
        bus3.loadPassenger(45);

        // 10.
        bus3.alertAllWarningMessages();

        // 11.
        bus3.loadPassenger(5);

        // 12.
        bus3.showPassengerCount();
        bus3.showSeatsLeft();
        bus3.showCurrentFare();

        // 13.
        bus3.changeFuel(-55);

        // 14.
        bus3.showFuel();
        bus3.showState();

        // 15.
        bus3.alertAllWarningMessages();

        System.out.println();
    }

    public static void testTaxiClass() {
        // 1.
        Taxi taxi1 = new Taxi();
        Taxi taxi2 = new Taxi();

        // 2.
        taxi1.showVehicleNumber();
        taxi2.showVehicleNumber();
        taxi1.showFuel();
        taxi1.showState();

        // 1.
        Taxi taxi3 = new Taxi();
        taxi3.loadPassenger(2, 2, "서울역");

        // 2.
        taxi3.showPassengerCount();
        taxi3.showSeatsLeft();
        taxi3.showInitTaxiFare();
        taxi3.showDestination();
        taxi3.showMileage();
        taxi3.showCurrentFare();
        taxi3.showState();

        // 3.
        taxi3.changeFuel(-80);

        // 4.
        taxi3.unloadPassenger();

        // 5.
        taxi3.showFuel();
        taxi3.showFare();

        // 6.
        taxi3.loadPassenger(5, Taxi.INIT_MILEAGE, "");

        // 7.
        taxi3.alertAllWarningMessages();

        // 8.
        taxi3.loadPassenger(3, 12, "구로디지털단지역");

        // 9.
        taxi3.showPassengerCount();
        taxi3.showSeatsLeft();
        taxi3.showInitTaxiFare();
        taxi3.showDestination();
        taxi3.showMileage();
        taxi3.showCurrentFare();

        // 10.
        taxi3.changeFuel(-20);

        // 11.
        taxi3.unloadPassenger();

        // 12.
        taxi3.showFuel();
        taxi3.showState();
        taxi3.showFare();

        // 13.
        taxi3.alertAllWarningMessages();

        System.out.println();
    }

    public static void main(String[] args) {
        testBusClass();
        testTaxiClass();
    }
}
