package com.bridgelabz;

/**
 * @author Lokesh
 * @since 09/11/2021
 */
public class ParkingLotOwner {
    public boolean isFullCapacity;

    public void capacityIsFull() {
        isFullCapacity = true;
    }

    public boolean isFullCapacity(){
        return this.isFullCapacity;
    }
}
