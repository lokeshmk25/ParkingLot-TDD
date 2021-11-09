package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LOKESH
 * @since 09/11/2021
 */
public class ParkingLotServiceTest {
    @Test
    void givenParkinglot_WhenSlotAvailable_ShouldReturnTrue() {
        ParkingLotService parkingLotService = new ParkingLotService();
        boolean available = parkingLotService.IsSlotAvailable(10);
        Assertions.assertTrue(available);
    }

    @Test
    void givenParkingLot_WhenSlotNotAvailable_ShouldReturnFalse() {
        ParkingLotService parkingLotService = new ParkingLotService();
        boolean available = parkingLotService.IsSlotAvailable(101);
        Assertions.assertFalse(available);
    }

    @Test
    void givenSlotAvailable_WhenParked_ShouldReturnTrue() {
        ParkingLotService parkingLotService = new ParkingLotService();
        String park = parkingLotService.park();
        Assertions.assertEquals("parked", park);
    }
}
