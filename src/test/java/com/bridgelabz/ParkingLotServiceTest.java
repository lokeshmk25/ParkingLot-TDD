package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author LOKESH
 * @since 09/11/2021
 */
public class ParkingLotServiceTest {

    @BeforeEach
    void setUp() {
        Object vehicle = new Object();
        ParkingLotService parkingLotService = new ParkingLotService();
    }

    @Test
    void givenAVechicle_WhenParked_ShouldReturnTrue() {
        ParkingLotService parkingLotService = new ParkingLotService();
        boolean isParked = parkingLotService.park(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVechicle_WhenUnParked_ShouldReturnTrue() {
        Object vehicle = new Object();
        ParkingLotService parkingLotService = new ParkingLotService();
        parkingLotService.park(vehicle);
        boolean isUnparked = parkingLotService.unPark(vehicle);
        Assertions.assertTrue(isUnparked);
    }
}
