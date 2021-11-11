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

    @BeforeEach
    void setUp() {
        parkingLotService = new ParkingLotService();
    }

    @Test
    void givenAVechicle_WhenParked_ShouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            parkingLotService.park(vehicle);
            boolean isParked = parkingLotService.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVechicle_WhenUnParked_ShouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            parkingLotService.park(vehicle);
            parkingLotService.unPark(vehicle);
            boolean isUnParked = parkingLotService.isVehicleUnParked(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenNull_WhenUnParked_ShouldReturnException() {
        try {
            parkingLotService.unPark(null);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Vechicle is not parked", e.getMessage());
        }
    }

    @Test
    void givenAParkedVehicle_WhenUnparkedDifferentVechicle_ShouldReturnException() {
        try {
            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            Vehicle vehicle1 = new Vehicle("ford", "TN-1234");
            parkingLotService.park(vehicle);
            parkingLotService.unPark(vehicle1);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("This not your vehicle", e.getMessage());
        }
    }

    @Test
    void givensameVechicle_WhenParked_ShouldNotAdded() {
        try {
            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
            Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle1);
            parkingLotService.park(vehicle2);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_shouldReturnException() {
        try {
            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
            Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
            Vehicle vehicle3 = new Vehicle("Audi", "TN-9800");
            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle1);
            parkingLotService.park(vehicle2);
            parkingLotService.park(vehicle3);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }
    @Test
    void givenVehicle_WhenParkingLotIsEmpty_shouldNotifyOwner() {
            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
            Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");

      try {
          parkingLotService.park(vehicle);
          parkingLotService.park(vehicle1);
          parkingLotService.park(vehicle2);
          boolean IsParkingLotFull= ParkingLotService.parkingLotOwner();
          Assertions.assertFalse(IsParkingLotFull);
      } catch (ParkingLotException e) {
          Assertions.assertEquals("Parking lot is full", e.getMessage());
      }

    }

}
