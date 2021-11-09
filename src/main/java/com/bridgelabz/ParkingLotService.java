package com.bridgelabz;

public class ParkingLotService {

    private int Slot;
    public int MAX_SLOT=100;
    public int LOT=2;


    public boolean IsSlotAvailable() {
        return Slot < MAX_SLOT;
    }

    public String park() {
        if (IsSlotAvailable() == true) {
            return "parked";
        } else {
            return "Slot Not Available";
        }
    }
}
