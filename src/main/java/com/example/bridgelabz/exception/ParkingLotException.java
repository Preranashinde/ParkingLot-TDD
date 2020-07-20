package com.example.bridgelabz.exception;

public class ParkingLotException extends Exception {
    public enum ExceptionType {

        PARKING_LOT_FULL, VEHICLE_ALREADY_UNPARKED, WRONG_VEHICLE;
    }
    public ExceptionType type;

    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
