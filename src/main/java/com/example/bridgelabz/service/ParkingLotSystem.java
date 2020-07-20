package com.example.bridgelabz.service;

import com.example.bridgelabz.exception.ParkingLotException;
import com.example.bridgelabz.observer.Observer;
import com.example.bridgelabz.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem implements Subject {
    private int parkingLotCapacity;
    private String vehicleName;
    private List<String> parkingLot;
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unRegister(Observer o) {
        observers.remove(observers.indexOf(o));
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.sendParkingStatus(parkingLot.size(), parkingLotCapacity);
        }
    }

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.parkingLot = new ArrayList<String>();
    }

    public void parkVehicle(String vehicle) throws ParkingLotException {
        if (parkingLot.size() >= parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicleName = vehicle;
        parkingLot.add(vehicleName);
        this.notifyObservers();
    }

    public void unParkVehicle(String vehicle) throws ParkingLotException {
        if (!(parkingLot.contains(vehicle)))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        parkingLot.remove(vehicle);
        this.notifyObservers();
    }

    public boolean isVehicleParked() {
        return parkingLot.contains(vehicleName);
    }
}
