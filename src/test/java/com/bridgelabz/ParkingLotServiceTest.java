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
        boolean isParked = parkingLotService.park(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVechicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotService.park(vehicle);
        boolean isUnparked = parkingLotService.unPark(vehicle);
        Assertions.assertTrue(isUnparked);
    }

    @Test
    void givenVehicle_WhenAlreadyParked_shouldReturnFalse() {
        parkingLotService.park(vehicle);
        boolean isParked = parkingLotService.park(new Object());
        Assertions.assertFalse(isParked);
}}
