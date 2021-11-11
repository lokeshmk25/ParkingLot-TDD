package com.bridgelabz;

import java.util.HashSet;

public class ParkingLotService {

    public static final int MAX_CAPACITY = 3;
    public Object vehicle;
    private static boolean result;

    HashSet<Object> list=new HashSet<>(3);



    public void park(Object vehicle) throws ParkingLotException {

        if (list.size() == MAX_CAPACITY) {
            throw new ParkingLotException("Parking lot is full");
        }
        this.vehicle = vehicle;
        list.add(vehicle);
        result = parkingLotOwner();
    }

    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("Vechicle is not parked");
        if (this.vehicle.equals(vehicle)) {
            list.remove(vehicle);
        } else {
            throw new ParkingLotException("This not your vehicle");
        }
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            return true;
        }
        return false;
    }

    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            return true;
        }
        return false;
    }
    public static boolean parkingLotOwner() {
        return result;
    }
    }
