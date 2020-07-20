package com.example.bridgelabz.observer;

import com.example.bridgelabz.service.ParkingLotSystem;

import java.util.HashMap;

public class AirportSecurity implements Observer {
    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    public void sendParkingStatus(HashMap<Integer, String> parkingLot) {
        isParkingFull = (ParkingLotSystem.isParkingLotFull(parkingLot))? true : false;
    }
}



