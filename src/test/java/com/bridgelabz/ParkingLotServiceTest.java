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
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        parkingLotService = new ParkingLotService();
    }

    @Test
    void givenAVechicle_WhenParked_ShouldReturnTrue() {
        try {
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
            parkingLotService.park(vehicle);
            parkingLotService.unPark(vehicle);
            boolean isUnParked = parkingLotService.isVehicleUnParked(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenVehicle_WhenAlreadyParked_shouldReturnException() {
        try {
            parkingLotService.park(vehicle);
           parkingLotService.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking lot is full",e.getMessage());
        }
    }

    @Test
    void givenNull_WhenUnParked_ShouldReturnException() {
        try {
            parkingLotService.unPark(null);
        } catch (ParkingLotException e) {
                Assertions.assertEquals("Vechicle is not parked",e.getMessage());
        }
    }

    @Test
    void givenAParkedVehicle_WhenUnparkedDifferentVechicle_ShouldReturnException() {
        try {
            parkingLotService.park(vehicle);
            parkingLotService.unPark(new Object());
        } catch (ParkingLotException e) {
           Assertions.assertEquals("This not your vehicle",e.getMessage());
        }
    }
}
