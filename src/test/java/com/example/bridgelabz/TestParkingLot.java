package com.example.bridgelabz;

import com.example.bridgelabz.exception.ParkingLotException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestParkingLot {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(5);
    Object vehicle = new Object();

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkVehicle("Tata Indigo CS");
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkVehicle("Toyota Fortuner");
        parkingLotSystem.unParkVehicle("Toyota Fortuner");
        boolean isVehicleUnParked = parkingLotSystem.isVehicleParked();
        Assert.assertFalse(isVehicleUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {

        try {
            parkingLotSystem.parkVehicle("Tata Indigo CS");
            parkingLotSystem.unParkVehicle("Toyota Fortuner");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle("Tata Indigo CS");
            parkingLotSystem.parkVehicle("Toyota Fortuner");
            parkingLotSystem.parkVehicle("Maruti Swift Dzire");
            parkingLotSystem.parkVehicle("Tata Hexa");
            parkingLotSystem.parkVehicle("Maruti 800");
            parkingLotSystem.parkVehicle("Suzuki Nexa");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle("Tata Indigo CS");
            parkingLotSystem.unParkVehicle("Tata Indigo CS");
            parkingLotSystem.unParkVehicle("Tata Indigo CS");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }
}
