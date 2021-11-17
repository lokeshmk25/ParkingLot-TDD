package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Lokesh
 * @since 09/11/21
 */

public class ParkingLotService {

    private final int capacity;
    public Object vehicle;
    ArrayList<Object> list;
    private ParkingType parkingType;
    private List<ParkingLotObserver> observers;

    public ParkingLotService(int capacity) {
        list = new ArrayList<>();
        this.observers=new ArrayList<>();
        this.capacity = capacity;
    }

    public void registerObservers(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * PURPOSE -  parking is done in this method
     *
     * @param vehicle is given as input ,in if condition it checks equality of list size and maximum capacity
     *                If both are equal it throws parking lot exception else it adds vechicle to the list and retyrns
     *                result to owner
     * @throws ParkingLotException it occurs when parking lot is full
     */
    public void park(ParkingType parkingType, Object vehicle) {
        if (list.size() != capacity) {
            this.parkingType = parkingType;
            this.vehicle = vehicle;
            if(isVehicleParked(vehicle))
                throw new ParkingLotException("Vehicle already parked");
            list.add(vehicle);
        }
        else if (list.size() == capacity) {
            for (ParkingLotObserver observer:observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking lot is full");
        }
    }

    /**
     * PURPOSE - To unpark parked vehicle
     *
     * @param vehicle is given as input , in if condition it checks for null and throws Parkinglot exception
     *                if vehicle equals parked vehicle then unparking is done,else throws parkinglot exception
     * @throws ParkingLotException it occurs when vehicle is null and different vehicle is unparked
     */
    public void unPark(ParkingType parkingType, Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("Vechicle is not parked");
        if (list.contains(vehicle)) {
            this.parkingType = parkingType;
            list.remove(vehicle);
            for (ParkingLotObserver observer:observers) {
                observer.capacityIsAvailable();
            }
        } else {
            throw new ParkingLotException("This not your vehicle");
        }
    }

    /**
     * @param vehicle checks equality of vehicle
     * @return result of equality
     */
    public boolean isVehicleParked(Object vehicle) {
        return list.contains(vehicle);
    }

    /**
     * @param vehicle is given as input
     * @return result of equality check
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return !list.contains(vehicle);
    }


    /**
     * Purpose - To check the vehicle if it is present or Not
     *
     * @param vehicle is taken as input to check if the vehicle is present or not.
     * @return boolean true if it contains vehicleeelse returns false
     */
    public boolean searchVechicle(Object vehicle) {
        return list.contains(vehicle);
    }

    /**
     * Purpose - Enum is created to specify the parking type of the vechicle
     * Normal is given if te driver himself wishes to park the vehicle
     * Attendent is used to park the vehicle by attendent
     */
    enum ParkingType {NORMAL, ATTENDENT}

}





