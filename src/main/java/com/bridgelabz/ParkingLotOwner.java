package com.bridgelabz;

/**
 * @author Lokesh
 * @since 09/11/2021
 * Purpose - To Implement interface parkinglot observer
 */
public class ParkingLotOwner implements ParkingLotObserver{
    public boolean isFullCapacity;

    /**
     * Purpose - to check capacity is full or not if it is full it
     * Returns True
      */
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
