package com.example.bridgelabz.observer;

public class Owner implements Observer {
    public enum Flag {PARKING_IS_VACANT, PARKING_IS_FULL};

    Flag flag;

    public Flag getFlag() {
        return flag;
    }

    @Override
        public void sendParkingStatus(int currentlyOccupiedSlots, int parkingLotCapacity) {
        flag = (currentlyOccupiedSlots == parkingLotCapacity) ? Flag.PARKING_IS_FULL : Flag.PARKING_IS_VACANT;
        }

}
