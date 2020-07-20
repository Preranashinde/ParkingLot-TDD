package com.example.bridgelabz.utility;

import com.example.bridgelabz.exception.ParkingLotException;
import com.example.bridgelabz.observer.Owner;
import com.example.bridgelabz.service.ParkingLotSystem;

import java.util.HashMap;

public class ParkingAttendant {
    Owner owner = new Owner();
    int parkingLotCapacity = 0;

    public ParkingAttendant(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public HashMap<Integer, String> park(String vehicle, HashMap<Integer, String> parkingLot) throws ParkingLotException {
        Integer emptyParkingSlot = owner.getEmptyParkingSlot(parkingLot);
        if (ParkingLotSystem.isParkingLotFull(parkingLot))
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        parkingLot.put(emptyParkingSlot, vehicle);
        return parkingLot;
    }
}
