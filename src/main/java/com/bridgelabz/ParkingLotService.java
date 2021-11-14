package com.bridgelabz;

import java.util.HashSet;

/**
 * @author Lokesh
 * @since 09/11/21
 */

public class ParkingLotService {

    public static final int MAX_CAPACITY = 3;
    public Object vehicle;
    HashSet<Object> list = new HashSet<>(3);
    private ParkingLotOwner owner;
    private ParkingType parkingType;

    /**
     * Purpose - Enum Parking Type is used to determine who should park
     */
    enum ParkingType{Normal,Attendent}


    /**
     * PURPOSE -  parking is done in this method
     *
     * @param vehicle is given as input ,in if condition it checks equality of list size and maximum capacity
     *                If both are equal it throws parking lot exception else it adds vechicle to the list and retyrns
     *                result to owner
     * @throws ParkingLotException it occurs when parking lot is full
     */
    public void park(ParkingType parkingType,Object vehicle) {
        if (list.size() == MAX_CAPACITY) {
            owner.capacityFull();
            throw new ParkingLotException("Parking lot is full");
        }
        this.parkingType=parkingType;
        this.vehicle = vehicle;
        list.add(vehicle);
        owner.capacityNotFull();

    }

    /**
     * PURPOSE - To unpark parked vehicle
     *
     * @param vehicle is given as input , in if condition it checks for null and throws Parkinglot exception
     *                if vehicle equals parked vehicle then unparking is done,else throws parkinglot exception
     * @throws ParkingLotException it occurs when vehicle is null and different vehicle is unparked
     */
    public void unPark(ParkingType parkingType,Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("Vechicle is not parked");
        if (this.vehicle.equals(vehicle)) {
            this.parkingType=parkingType;
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

    /**
     * Purpose - to register the parking lot owner
     * @param owner is given as owner of parking lot
     */
    public void registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }
}

