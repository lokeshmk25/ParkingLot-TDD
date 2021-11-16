package com.bridgelabz;

/**
 * @author Lokesh
 * @since 11/11/21
 *
 * Purpose - To Handle ParkingLot Exceptions
 */
public class ParkingLotException extends RuntimeException {
    public ParkingLotException(String message) {
        super(message);
    }
}
