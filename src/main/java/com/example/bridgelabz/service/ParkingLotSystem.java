package com.example.bridgelabz.service;

import com.example.bridgelabz.entity.Slot;
import com.example.bridgelabz.entity.Vehicle;
import com.example.bridgelabz.exception.ParkingLotException;
import com.example.bridgelabz.observer.AirportSecurity;
import com.example.bridgelabz.observer.Owner;
import com.example.bridgelabz.utility.ParkingAttendant;
import com.example.bridgelabz.utility.ParkingLotSystemUtilities;
import java.time.LocalTime;
import java.util.*;

public class ParkingLotSystem {
    public int noOfSlotsPerLot;
    public LocalTime arrivalTime;
    ParkingAttendant parkingAttendant = null;
    ParkingLotSystemUtilities parkingUtilities = null;
    public HashMap<Slot, Vehicle> vehicleData = null;

    public ParkingLotSystem(int parkingLotCapacity, int noOfParkingLots) {
        this.parkingUtilities = new ParkingLotSystemUtilities(parkingLotCapacity, noOfParkingLots);
        this.noOfSlotsPerLot = parkingUtilities.getNoOfSlotsPerLot();
        this.parkingAttendant = new ParkingAttendant(parkingLotCapacity, noOfParkingLots, parkingUtilities.getNoOfSlotsPerLot());
        this.vehicleData = new HashMap<>();
    }
    public void register(Owner owner) {
        parkingAttendant.register(owner);
    }

    public void register(AirportSecurity airportSecurity) {
        parkingAttendant.register(airportSecurity);
    }

    public boolean isVehiclePresentInLot(Vehicle vehicle) {
        return vehicleData.containsValue(vehicle);
    }

    public void park(Vehicle vehicle) throws ParkingLotException {
        vehicleData = parkingAttendant.park(vehicle);
    }

    public void unPark(Vehicle vehicle) throws ParkingLotException {
        vehicleData = parkingAttendant.unPark(vehicle);
    }

    public LocalTime getArrivalTime(Vehicle vehicle) {
        for (Slot slot : vehicleData.keySet()) {
            if (vehicleData.get(slot).equals(vehicle)) {
                arrivalTime = slot.getArrivalTime();
            }
        }
        return arrivalTime;
    }
}
