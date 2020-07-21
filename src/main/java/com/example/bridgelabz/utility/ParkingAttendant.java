package com.example.bridgelabz.utility;

import com.example.bridgelabz.entity.ParkingLot;
import com.example.bridgelabz.entity.Slot;
import com.example.bridgelabz.entity.Vehicle;
import com.example.bridgelabz.exception.ParkingLotException;
import com.example.bridgelabz.observer.Observer;
import com.example.bridgelabz.observer.Subject;

import java.time.LocalTime;
import java.util.*;

public class ParkingAttendant implements Subject {
    public int parkingLotCapacity = 0;
    public int noOfParkingLots = 0;
    public int noOfSlotsPerLot = 0;
    public int slotCounter = 0;
    private List<Observer> observers = new ArrayList<Observer>();
    public HashMap<Slot, Vehicle> vehicleData;
    ParkingLot lot1 = new ParkingLot(1);
    ParkingLot lot2 = new ParkingLot(2);
    ParkingLot lot3 = new ParkingLot(3);

    public ParkingAttendant(int parkingLotCapacity, int noOfParkingLots, int noOfSlotsPerLot) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.noOfParkingLots = noOfParkingLots;
        this.noOfSlotsPerLot = noOfSlotsPerLot;
        this.vehicleData = new HashMap<>();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }
    @Override
    public void unRegister(Observer o) {
        observers.remove(observers.indexOf(o));
    }
    @Override
    public void notifyObservers(int currentlyOccupiedSlots) {
        for (Observer observer : observers) {
            observer.sendParkingStatus(currentlyOccupiedSlots, this.parkingLotCapacity);
        }
    }
    public HashMap<Slot, Vehicle> park(Vehicle vehicle) throws ParkingLotException {
        if (vehicleData.size() > this.parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT FULL");
        Slot slot = new Slot();
        slotCounter = slotCounter + 1;
        slot.setSlotID(slotCounter);
        slot.setArrivalTime(LocalTime.of(11, 10, 37));
        ParkingLot lot = new ParkingLot(ParkingLotSystemUtilities.assignLot(slot.getSlotID()));
        slot.setLot(lot);
        vehicleData.put(slot, vehicle);
        this.notifyObservers(vehicleData.size());
        return vehicleData;
    }

    public HashMap<Slot, Vehicle> unPark(Vehicle vehicle) throws ParkingLotException {
        if (!vehicleData.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_PRESENT, "VEHICLE NOT PRESENT");
        Set<Slot> slots = vehicleData.keySet();
        for (Slot slot : slots) {
            if (vehicleData.get(slot).equals(vehicle)) {
                slot.setDepartureTime(LocalTime.of(12, 19, 56));
                vehicleData.remove(slot);
                this.notifyObservers(vehicleData.size());
            }
        }
            return vehicleData;
        }


}
