package com.bridgelabz;

/**
 * @author Lokesh
 * @since 09/11/21
 * Purpose - To create entities of vehicle
 */
public class Vehicle {
    private final String vechicleName;
    private final String vechicleNo;
    private final String vehicleColour;

    /**
     * @param vehicleName is taken as input to store the vehicle identity
     * @param vechicleNo  is also taken as input to store vehicle identity
     */
    public Vehicle(String vehicleName, String vechicleNo,String vehicleColour) {
        this.vechicleName = vehicleName;
        this.vechicleNo = vechicleNo;
        this.vehicleColour=vehicleColour;
    }


    public String getVechicleName() {
        return vechicleName;
    }

    public String getVechicleNo() {
        return vechicleNo;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vechicleName='" + vechicleName + '\'' +
                ", vechicleNo='" + vechicleNo + '\'' +
                ", vehicleColour='" + vehicleColour + '\'' +
                '}';
    }
}
