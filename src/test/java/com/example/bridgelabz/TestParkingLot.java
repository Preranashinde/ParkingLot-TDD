package com.example.bridgelabz;

import com.example.bridgelabz.exception.ParkingLotException;
import com.example.bridgelabz.observer.AirportSecurity;
import com.example.bridgelabz.observer.Owner;
import com.example.bridgelabz.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestParkingLot {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(5);
    Object vehicle = new Object();
    Owner owner = new Owner();
    AirportSecurity airportSecurity = new AirportSecurity();

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkVehicle("Tata Indigo CS");
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.parkVehicle("Toyota Fortuner");
        boolean isVehicleUnParked = parkingLotSystem.unParkVehicle("Toyota Fortuner");
        Assert.assertTrue(isVehicleUnParked);
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

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.parkVehicle("Tata Indigo CS");
        parkingLotSystem.parkVehicle("Toyota Fortuner");
        parkingLotSystem.parkVehicle("Maruti Swift Dzire");
        parkingLotSystem.parkVehicle("Tata Hexa");
        parkingLotSystem.parkVehicle("Maruti 800");
//        parkingLotSystem.parkVehicle("Tata Hexa");
//        parkingLotSystem.parkVehicle("Maruti 800");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.parkVehicle("Tata Indigo CS");
        parkingLotSystem.parkVehicle("Toyota Fortuner");
        parkingLotSystem.parkVehicle("Maruti Swift Dzire");
        parkingLotSystem.parkVehicle("Tata Hexa");
//        parkingLotSystem.parkVehicle("Tata Hexa");
//        parkingLotSystem.parkVehicle("Tata Hexa");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.parkVehicle("Tata Indigo CS");
        parkingLotSystem.parkVehicle("Toyota Fortuner");
        parkingLotSystem.parkVehicle("Maruti Swift Dzire");
        parkingLotSystem.parkVehicle("Tata Hexa");
        parkingLotSystem.parkVehicle("Maruti 800");
        Assert.assertEquals(true, airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.parkVehicle("Tata Indigo CS");
        parkingLotSystem.parkVehicle("Toyota Fortuner");
        parkingLotSystem.parkVehicle("Maruti Swift Dzire");
        Assert.assertFalse(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLotIsFull_IfItHasSpaceAgain_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.parkVehicle("Tata Indigo CS");
        parkingLotSystem.parkVehicle("Toyota Fortuner");
        parkingLotSystem.parkVehicle("Maruti Swift Dzire");
        parkingLotSystem.parkVehicle("Tata Hexa");
        parkingLotSystem.parkVehicle("Maruti 800");
        parkingLotSystem.unParkVehicle("Maruti Swift Dzire");
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }
}
