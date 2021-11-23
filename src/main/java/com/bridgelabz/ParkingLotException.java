package com.bridgelabz;

/**
 * @author Lokesh
 * @since 11/11/21
 *
 * Purpose - To Handle ParkingLot Exceptions
 */
public class ParkingLotException extends RuntimeException {
    public final ExceptionType exceptionType;


    public enum ExceptionType {
        PARKING_LOT_FULL,PARKING_LOT_NOT_FULL,VEHICLE_ALREADY_PARKED,VEHICLE_NOT_PARKED,NOT_YOUR_VEHICLE,VEHICLE_NOT_FOUND
    }

    public ParkingLotException(ExceptionType exceptionType) {
//        super(message);
        this.exceptionType=exceptionType;
    }
}
