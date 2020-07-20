package com.example.bridgelabz.observer;

public class AirportSecurity implements Observer {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    @Override
    public void sendParkingStatus(int currentOccupiedSlots, int parkingLotCapacity) {
        isParkingFull = (currentOccupiedSlots >= parkingLotCapacity)? true : false;
    }
}



