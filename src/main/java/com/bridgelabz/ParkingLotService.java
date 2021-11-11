package com.bridgelabz;

import java.util.HashSet;

/**
 * @author Lokesh
 * @since 09/11/21
 */
public class ParkingLotService {

    public static final int MAX_CAPACITY = 3;
    private static boolean result;
    public Object vehicle;
    HashSet<Object> list = new HashSet<>(3);

    /**
     * @return result to owner
     */
    public static boolean parkingLotOwner() {
        return result;
    }

    /**
     * PURPOSE -  parking is done in this method
     *
     * @param vehicle is given as input ,in if condition it checks equality of list size and maximum capacity
     *                If both are equal it throws parking lot exception else it adds vechicle to the list and retyrns
     *                result to owner
     * @throws ParkingLotException it occurs when parking lot is full
     */
    public void park(Object vehicle) throws ParkingLotException {

        if (list.size() == MAX_CAPACITY) {
            throw new ParkingLotException("Parking lot is full");
        }
        this.vehicle = vehicle;
        list.add(vehicle);
        result = parkingLotOwner();
    }

    /**
     * PURPOSE - To unpark parked vehicle
     *
     * @param vehicle is given as input , in if condition it checks for null and throws Parkinglot exception
     *                if vehicle equals parked vehicle then unparking is done,else throws parkinglot exception
     * @throws ParkingLotException it occurs when vehicle is null and different vehicle is unparked
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("Vechicle is not parked");
        if (this.vehicle.equals(vehicle)) {
            list.remove(vehicle);
        } else {
            throw new ParkingLotException("This not your vehicle");
        }
    }

    /**
     * @param vehicle checks equality of vehicle
     * @return result of equality
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicle.equals(vehicle);
    }

    /**
     * @param vehicle is given as input
     * @return result of equality check
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return this.vehicle.equals(vehicle);
    }
}
