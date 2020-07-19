package com.example.bridgelabz;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BridgelabzApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assert.assertTrue(isParked);
    }

}
