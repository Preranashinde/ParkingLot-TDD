package com.example.bridgelabz;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestParkingLot {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
    Object vehicle = new Object();

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldReturnFalse() {
        boolean isUnParked = parkingLotSystem.unParkVehicle(new Object());
        Assert.assertFalse(isUnParked);
    }
}
