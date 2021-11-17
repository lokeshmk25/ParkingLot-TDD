package com.bridgelabz;

/**
 * @author Lokesh
 * @since 09/11/2021
 */
public class ParkingLotOwner implements ParkingLotObserver{
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
