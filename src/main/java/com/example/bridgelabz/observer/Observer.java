package com.example.bridgelabz.observer;

public interface Observer {
    public void sendParkingStatus(int currentOccupiedSlots, int parkingLotCapacity);
}
