package com.example.bridgelabz;

import com.example.bridgelabz.exception.ParkingLotException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestParkingLot {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
    Object vehicle = new Object();

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkVehicle(new Object());
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.unParkVehicle(vehicle);
        boolean isVehicleUnParked = parkingLotSystem.isVehicleParked();
        Assert.assertFalse(isVehicleUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {

        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.unParkVehicle(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParkedAndTriedToParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.unParkVehicle(vehicle);
            parkingLotSystem.unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED, e.type);
        }
    }
}
