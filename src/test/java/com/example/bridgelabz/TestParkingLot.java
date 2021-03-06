package com.example.bridgelabz;

import com.example.bridgelabz.entity.Slot;
import com.example.bridgelabz.entity.Vehicle;
import com.example.bridgelabz.exception.ParkingLotException;
import com.example.bridgelabz.observer.AirportSecurity;
import com.example.bridgelabz.observer.Owner;
import com.example.bridgelabz.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalTime;
import java.util.Map;

@SpringBootTest
public class TestParkingLot {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(100,4);
    Object vehicle = new Object();
    Owner owner = new Owner();
    AirportSecurity airportSecurity = new AirportSecurity();
    Vehicle vehicle1 = new Vehicle("Indigo CS", 4526, "TATA", "Blue");
    Vehicle vehicle2 = new Vehicle("Hexa", 4532, "TATA", "Black");
    Vehicle vehicle3 = new Vehicle("Fortuner", 3261, "TOYOTA", "Blue");
    Vehicle vehicle4 = new Vehicle("Honda city", 2365, "HONDA", "White");
    Vehicle vehicle5 = new Vehicle("Mercedes-Benz", 8745, "MERCEDES", "Blue");
    Vehicle vehicle6 = new Vehicle("Ford Figo", 6754, "FORD", "White");
    Vehicle vehicle7 = new Vehicle("Santro", 9317, "HYUNDAI", "Blue");
    Vehicle vehicle8 = new Vehicle("Audi R8", 4856, "AUDI", "Black");
    Vehicle vehicle9 = new Vehicle("Polo", 8642, "VOLKSWAGEN", "Blue");
    Vehicle vehicle10 = new Vehicle("BMW X5", 5863, "BMW", "Blue");

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle1);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {
        try {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle2);
    } catch (ParkingLotException e) {
        Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
    }
}


    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            for (int i = 0; i <= 101; i++)
                parkingLotSystem.park(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle1);
            parkingLotSystem.unPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 100; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 99; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        for (int i = 0; i < 100; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertTrue(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        for (int i = 0; i < 99; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertFalse(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLotIsFull_IfItHasSpaceAgain_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 100; i++) {
            parkingLotSystem.park(vehicle1);
        }
        parkingLotSystem.unPark(vehicle1);
        Assert.assertEquals(owner.getFlag(), Owner.Flag.PARKING_IS_VACANT);
    }


    @Test
    public void givenVehicle_IfFoundInParkingLot_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle2));
    }

    @Test
    public void givenVehicle_IfNotFoundInParkingLot_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle4));
    }

    @Test
    public void givenAVehicle_WhenParkedAndThenUnparked_ShouldReturnArrivalTimeAndDepartureTime()
            throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        Assert.assertEquals(parkingLotSystem.getArrivalTime(vehicle1), LocalTime.of(11, 10, 37));
    }

    @Test
    public void givenManyVehicles_WhenParkedEvenly_ShouldReturnPosition() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        for (Map.Entry<Slot, Vehicle> entry : parkingLotSystem.vehicleData.entrySet()) {
            if (entry.getValue().equals(vehicle1)) {
                Assert.assertEquals(1,entry.getKey().lot.lotID);
                Assert.assertEquals(1,entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle2)) {
                Assert.assertEquals(2,entry.getKey().lot.lotID);
                Assert.assertEquals(2,entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle3)) {
                Assert.assertEquals(3,entry.getKey().lot.lotID);
                Assert.assertEquals(3,entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle4)) {
                Assert.assertEquals(4,entry.getKey().lot.lotID);
                Assert.assertEquals(4,entry.getKey().slotID);
            }
        }
    }
}
