package com.example.bridgelabz;

import com.example.bridgelabz.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private int parkingLotCapacity;
    private String vehicleName;
    private List<String> parkingLot;

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.parkingLot = new ArrayList<String>();
    }

    public void parkVehicle(String vehicle) throws ParkingLotException {
        if (parkingLot.size() >= parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicleName = vehicle;
        parkingLot.add(vehicleName);
    }

    public void unParkVehicle(String vehicle) throws ParkingLotException {
        if (!(parkingLot.contains(vehicle)))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        parkingLot.remove(vehicle);
    }

    public boolean isVehicleParked() {
        if (parkingLot.contains(vehicleName))
            return true;

        return false;
}
}
