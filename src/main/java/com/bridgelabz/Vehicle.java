package com.bridgelabz;

/**
 * @author Lokesh
 * @since 09/11/21
 */
public class Vehicle {
    public final String vechicleName;
    public final String vechicleNo;

    /**
     * @param vehicleName is taken as input to store the vehicle identity
     * @param vechicleNo  is also taken as input to store vehicle identity
     */
    public Vehicle(String vehicleName, String vechicleNo) {
        this.vechicleName = vehicleName;
        this.vechicleNo = vechicleNo;
    }
}
