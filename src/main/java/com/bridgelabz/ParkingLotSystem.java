package com.bridgelabz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Lokesh
 * @since 09/11/21
 */

public class ParkingLotSystem {

    private final int capacity;
    ArrayList<Object> list3;
    public Object vehicle;
    public String parkingTime;
    ArrayList<Object> list;
    ArrayList<Object> list1;
    public final List<ParkingLotObserver> observers;

    public ParkingLotSystem(int capacity) {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list3=new ArrayList<>();
        this.observers = new ArrayList<>();
        this.capacity = capacity;
    }
    /**
     * Purpose - Enum is created to specify the parking type of the vechicle
     * Normal is given if te driver himself wishes to park the vehicle
     * Attendent is used to park the vehicle by attendent
     */

    enum ParkingType {NORMAL, ATTENDENT}

    /**
     * Purpose - Enum DriverType is created to specify handicapped driver
     */
    enum DriverType{
        HANDICAPPED
    }

    /**
     * PURPOSE -  parking is done in this method
     *
     * @param vehicle     is given as input ,in if condition it checks equality of list size and maximum capacity
     *                    If both are equal it throws parking lot exception else it adds vechicle
     *                    to the list and return result to owner
     * @throws ParkingLotException it occurs when parking lot is full
     */

    public void park(Object vehicle) {
        if ((list.size() != capacity) || (list1.size() != capacity)) {
            this.vehicle = vehicle;
            if (list.contains(vehicle) || list1.contains(vehicle))
                throw new ParkingLotException
                        (ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED);
            if (list.size() >= list1.size())
                list1.add(vehicle);
            else
                list.add(vehicle);
            parkingTime();
        } else {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_FULL);
        }
    }

    /**
     * Purpose - To get the parking Time of vehicle here System local Time
     * is given and the time pattern is changed by time formatter
     *
     * @return Parking time of vehicle
     */
    public String parkingTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        parkingTime = dateTime.format(formatter);
        return parkingTime;
    }

    /**
     * Purpose - To Register the observers of Parking Lot
     *
     * @param observer is added to register the observer of Parking lot system
     */
    public void registerObservers(ParkingLotObserver observer) {
        this.observers.add(observer);
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
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_NOT_PARKED);
        if (list.contains(vehicle)) {
            list.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
        } else if (list1.contains(vehicle)) {
            list1.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
        } else {
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.NOT_YOUR_VEHICLE);
        }
    }

    /**
     * Purpose - To check the given vehicle is parked or not
     * @param vehicle checks equality of vehicle
     * @return result of equality
     */
    public boolean isVehicleParked(Object vehicle) {
        if (list.contains(vehicle) || list1.contains(vehicle)) ;
        return true;
    }

    /**
     * Purpose - To check whether the given vehicle is unparked or not
     * @param vehicle is given as input
     * @return result of equality check
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return !(list.contains(vehicle) || list1.contains(vehicle));
    }

    /**
     * Purpose - To check the vehicle if it is present or Not
     * @param vehicle is taken as input to check if the vehicle is present or not.
     * @return boolean true if it contains vehicleeelse returns false
     */
    public boolean searchVechicle(Object vehicle) {
        return list.contains(vehicle);
    }

    /**
     * Purpose - To get available slots for Parking the vehicle
     * @return - Available slots in list & list1
     * @throws ParkingLotException if the parking lot is full
     */
    public int getAvailableSlots() throws ParkingLotException {
        if (list1 != null || (list != null))
            return (2 * capacity - (list.size() + list1.size()));
        else {
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_NOT_FULL);
        }
    }

    /**
     * Purpose - To get the vehicle position for given colour
     * @param vehicle : vehicle is given as parameter for validation.
     * @param colour  : colour is given to check equality with vehicle colour
     * @return        : if the given colour equals vehicle colour it returns position of the vehicle.
     * @throws ParkingLotException : If the vehicle is not found
     */
    public int getPosition(Vehicle vehicle, String colour) throws ParkingLotException {
        if (list.contains(vehicle)) {
            vehicle.getVehicleColour().equals(colour);
            for (Object position : list) {
                if (position.equals(vehicle))
                    return list.indexOf(position);
            }
        } else if (list1.contains(vehicle)) {
            vehicle.getVehicleColour().equals(colour);
            for (Object position : list1) {
                if (position.equals(vehicle))
                    return list1.indexOf(position);
            }
        } else
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
        return 0;
    }

    /**
     * Purpose  -  To get position of vehicle based on vehicle name and colour
     * @param vehicle : It is given to check if the vehicleName and colour equals the vehicle
     * @param vehicleName : vehicle name is taken as parameter to get the position of vehicle
     * @param colour : colour is given to check equality with vehicle colour
     * @return : if the given colou and name equals vehicle it returns position of the vehicle.
     * @throws ParkingLotException : If the vehicle is not found
     */
    public int getPosition(Vehicle vehicle, String vehicleName, String colour) throws ParkingLotException {
        if (list.contains(vehicle) &&
                vehicle.getVehicleColour().equals(colour)
                && vehicle.getVechicleName().equals(vehicleName)) ;
        for (Object position : list) {
            if (position.equals(vehicle))
                return list.indexOf(position);

        }
        if (list1.contains(vehicle) &&
                vehicle.getVehicleColour().equals(colour)
                && vehicle.getVechicleName().equals(vehicleName)) ;
        for (Object position : list1) {
            if (position.equals(vehicle))
                return list1.indexOf(position);
        }
        throw new ParkingLotException
                (ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    /**
     * Purpose - To validate the given vehicle Number using Regex
     * @param vehicleNo : Taken as input to validate using Regex
     * @return : True if the given pattern matches
     */
    public boolean validateNumberPlate(String vehicleNo) {
        if (vehicleNo.isEmpty())
            return false;
        Pattern pattern = Pattern.compile("^[A-Z]{2}[ -][A-Z]{1,2}[0-9]{4}$");
        Matcher matcher = pattern.matcher(vehicleNo);
        return matcher.matches();
    }

    /**
     * Purpose - To provide parking lot for Handicapped driver
     * @param driverType : enum handicapped is provided as driver type
     * @param vehicle : Is taken as input for parking
     */
    public void handicapParking(DriverType driverType,Vehicle vehicle){
        if(driverType == DriverType.HANDICAPPED) {
            if(list3.size()!=capacity)
            list3.add(vehicle);
            parkingTime();
        }
        else
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_FULL);
    }

    /**
     * Purpose - To check the given vehicle is parked or not
     * @param vehicle : Is taken as input to check the vehicle is parked or not
     * @return : True if the vehicle is parked
     */
    public boolean isHandicapVehicleParked(Object vehicle){
        return list3.contains(vehicle);
    }

}






