package com.bridgelabz;

/**
 * @author lokesh
 * @since 10/11/21
 * Purpose - Interface parkingLotObserver is used to achieve abstraction
 */
public interface ParkingLotObserver {
  public void capacityIsFull();

  public void capacityIsAvailable();
}
