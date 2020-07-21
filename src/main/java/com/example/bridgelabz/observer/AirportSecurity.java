package com.example.bridgelabz.observer;

public class AirportSecurity implements Observer {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    public void sendParkingStatus(int currentlyOccupiedSlots, int parkingLotCapacity) {
        isParkingFull = (currentlyOccupiedSlots == parkingLotCapacity) ? true : false;
    }
}



