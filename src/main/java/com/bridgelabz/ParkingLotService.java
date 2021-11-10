package com.bridgelabz;

public class ParkingLotService {

    private Object vehicle;

    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking lot is full");
        this.vehicle = vehicle;
    }

    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("Vechicle is not parked");
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
        }
         throw new ParkingLotException("This not your vehicle");
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
}
