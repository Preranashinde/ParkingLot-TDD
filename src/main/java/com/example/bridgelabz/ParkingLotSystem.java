package com.example.bridgelabz;

import com.example.bridgelabz.exception.ParkingLotException;

public class ParkingLotSystem {
    private Object vehicle;

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (isVehicleParked())
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicle = vehicle;
    }

    public void unParkVehicle(Object vehicle) throws ParkingLotException {
        if ((!isVehicleParked()))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED, "VEHICLE IS ALREADY UNPARKED");
        if (!(vehicle.equals(this.vehicle)))
            throw new ParkingLotException(ParkingLotException.ExceptionType.WRONG_VEHICLE, "WRONG VEHICLE DETAILS PROVIDED");
        this.vehicle = null;
    }

    public boolean isVehicleParked() {
        if (this.vehicle != null)
            return true;

        return false;
}
}
