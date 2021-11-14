package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author LOKESH
 * @since 09/11/2021
 */
public class ParkingLotServiceTest {
    ParkingLotService parkingLotService = null;
    ParkingLotOwner owner = null;

    @BeforeEach
    void setUp() {
        parkingLotService = new ParkingLotService();
        owner = new ParkingLotOwner();
    }

    @Test
    void givenAVechicle_WhenParked_ShouldReturnTrue() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle);
        boolean isParked = parkingLotService.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVechicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle);
        parkingLotService.unPark(ParkingLotService.ParkingType.Attendent,vehicle);
        boolean isUnParked = parkingLotService.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenNull_WhenUnParked_ShouldReturnException() {

        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotService.unPark(ParkingLotService.ParkingType.Normal,null));

    }

    @Test
    void givenAParkedVehicle_WhenUnparkedDifferentVechicle_ShouldReturnException() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("ford", "TN-1234");
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotService.unPark(ParkingLotService.ParkingType.Normal,vehicle1));
    }

    @Test
    void givensameVechicle_WhenParked_ShouldNotAdded() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle);
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle);
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle1);
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle2);
        boolean isvehicleParked = parkingLotService.isVehicleParked(vehicle2);
        Assertions.assertTrue(isvehicleParked);
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_shouldReturnException() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
        Vehicle vehicle3 = new Vehicle("Audi", "TN-9800");
        parkingLotService.park(ParkingLotService.ParkingType.Attendent,vehicle);
        parkingLotService.park(ParkingLotService.ParkingType.Attendent,vehicle1);
        parkingLotService.park(ParkingLotService.ParkingType.Attendent,vehicle2);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle3));
    }

    @Test
    void givenVehicle_WhenParkingLotIsNotFull_shouldNotifyOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle);
        boolean isFull = owner.capacityNotFull();
        Assertions.assertTrue(isFull);
    }

    @Test
    void givenVehicle_WhenParkingLotFull_shouldNotifyOwner() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
        Vehicle vehicle3 = new Vehicle("audi", "TN-9321");
        parkingLotService.park(ParkingLotService.ParkingType.Attendent,vehicle);
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle1);
        parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle3);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotService.park(ParkingLotService.ParkingType.Normal,vehicle3));
        boolean IsParkingLotFull = owner.capacityFull();
        Assertions.assertTrue(IsParkingLotFull);
    }

    @Test
    void givenVechicle_WhenParkedByAttendent_ShouldReturnTrue() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotService.park(ParkingLotService.ParkingType.Attendent,vehicle);
        boolean isParked = parkingLotService.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenVechicle_WhenUnParkedByAttendent_ShouldReturnTrue() {
        parkingLotService.registerOwner(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotService.park(ParkingLotService.ParkingType.Attendent,vehicle);
        parkingLotService.unPark(ParkingLotService.ParkingType.Attendent,vehicle);
        boolean isParked = parkingLotService.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }
}
