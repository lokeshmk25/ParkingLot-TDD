package com.bridgelabz;

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
