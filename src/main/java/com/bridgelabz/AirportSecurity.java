package com.bridgelabz;

/**
 * @author LOKESH
 * @since 10/11/21
 * Purpose - To implement interface parking lot observer
 */
public class AirportSecurity implements ParkingLotObserver {
    public boolean isFullCapacity;

    public void capacityIsFull() {
        isFullCapacity = true;
    }

    @Override
    public void capacityIsAvailable() {
        isFullCapacity = false;
    }

    public boolean isFullCapacity(){
        return this.isFullCapacity;
    }
}
