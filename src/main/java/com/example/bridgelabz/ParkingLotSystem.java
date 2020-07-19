package com.example.bridgelabz;

public class ParkingLotSystem {
    private Object vehicle;

    public boolean parkVehicle(Object vehicle) {
        this.vehicle = vehicle;
        return true;
    }

    public boolean unParkVehicle(Object vehicle) {
        if (vehicle.equals(this.vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
