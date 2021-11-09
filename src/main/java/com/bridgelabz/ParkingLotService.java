package com.bridgelabz;

public class ParkingLotService {

    public int MAX_SLOT = 100;
    private boolean result;

    public boolean IsSlotAvailable(int Slot) {
        return result = Slot < MAX_SLOT;
    }

    public String park() {
        if (result == true) {
            return "parked";
        } else {
            return "Slot Not Available";
        }
    }
}
