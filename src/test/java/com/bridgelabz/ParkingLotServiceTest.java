package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author LOKESH
 * @since  09/11/2021
 */
public class ParkingLotServiceTest {
    @Test
    void givenParkinglot_WhenSlotAvailable_ShouldReturnTrue() {
        ParkingLotService parkingLotService = new ParkingLotService();
        boolean available = parkingLotService.IsSlotAvailable();
        Assertions.assertTrue(available);
    }

}
