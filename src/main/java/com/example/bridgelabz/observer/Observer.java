package com.example.bridgelabz.observer;

import java.util.HashMap;

public interface Observer {
    public void sendParkingStatus(HashMap<Integer, String> parkingLot);
}
