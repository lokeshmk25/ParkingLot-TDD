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

            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            parkingLotService.park(vehicle);
            boolean isParked = parkingLotService.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVechicle_WhenUnParked_ShouldReturnTrue() {

            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            parkingLotService.park(vehicle);
            parkingLotService.unPark(vehicle);
            boolean isUnParked = parkingLotService.isVehicleUnParked(vehicle);
            Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenNull_WhenUnParked_ShouldReturnException() {

        Assertions.assertThrows(ParkingLotException.class,()->{parkingLotService.unPark(null);});

    }

    @Test
    void givenAParkedVehicle_WhenUnparkedDifferentVechicle_ShouldReturnException() {

            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            Vehicle vehicle1 = new Vehicle("ford", "TN-1234");
            parkingLotService.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class,()->{parkingLotService.unPark(vehicle1);});
    }

    @Test
    void givensameVechicle_WhenParked_ShouldNotAdded() {

            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
            Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle1);
            parkingLotService.park(vehicle2);
        boolean isvehicleParked = parkingLotService.isVehicleParked(vehicle2);
        Assertions.assertTrue(isvehicleParked);
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_shouldReturnException() {

            Vehicle vehicle = new Vehicle("Tata", "TN-9876");
            Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
            Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
            Vehicle vehicle3 = new Vehicle("Audi", "TN-9800");
            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle1);
            parkingLotService.park(vehicle2);
            Assertions.assertThrows(ParkingLotException.class,()->{parkingLotService.park(vehicle3);});
    }

    @Test
    void givenVehicle_WhenParkingLotIsEmpty_shouldNotifyOwner() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");

            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle1);
            parkingLotService.park(vehicle2);
            boolean IsParkingLotFull = ParkingLotService.parkingLotOwner();
            Assertions.assertFalse(IsParkingLotFull);
    }
}
