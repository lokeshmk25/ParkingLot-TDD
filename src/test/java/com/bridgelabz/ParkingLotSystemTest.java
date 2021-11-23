package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author LOKESH
 * @since 09/11/2021
 */
public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
    }

    @Test
    void givenAVechicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "white");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVechicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Blue");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenNull_WhenUnParked_ShouldReturnException() {

        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL, null));

    }

    @Test
    void givenAParkedVehicle_WhenUnparkedDifferentVechicle_ShouldReturnException() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Green");
        Vehicle vehicle1 = new Vehicle("ford", "TN-1234", "Red");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL, vehicle1));
    }

    @Test
    void givensameVechicle_WhenParked_ShouldNotAdded() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Yellow");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle));
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_shouldReturnException() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "White");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "Green");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321", "Green");
        Vehicle vehicle3 = new Vehicle("Audi", "TN-9800", "White");
        Vehicle vehicle4 = new Vehicle("Audi", "TN-9800", "Red");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle3);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle4));
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerObservers(airportSecurity);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Red");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "Blue");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321", "Yellow");
        Vehicle vehicle3 = new Vehicle("audi", "TN-9321", "blue");
        Vehicle vehicle4 = new Vehicle("audi", "TN-9321", "Orange");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle4));
        boolean fullCapacity = airportSecurity.isFullCapacity();
        Assertions.assertTrue(fullCapacity);
    }

    @Test
    void givenVehicle_WhenParkingLotIsNotFull_shouldNotifyOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Red");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        boolean isFull = owner.isFullCapacity();
        Assertions.assertFalse(isFull);
    }

    @Test
    void givenVehicle_WhenParkingLotFull_shouldNotifyOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerObservers(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Blue");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "Pink");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321", "Blue");
        Vehicle vehicle3 = new Vehicle("audi", "TN-9321", "orange");
        Vehicle vehicle4 = new Vehicle("audi", "TN-9321", "Red");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle4));
        boolean IsParkingLotFull = owner.isFullCapacity();
        Assertions.assertTrue(IsParkingLotFull);
    }

    @Test
    void givenVechicle_WhenParkedByAttendent_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Yellow");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenVechicle_WhenUnParkedByAttendent_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Blue");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        boolean isParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAParkedVehicle_WhenFound_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "White");
        Vehicle vehicle1 = new Vehicle("BMW", "TN-9765", "Orange");
        Vehicle vehicle2 = new Vehicle("Ferrari", "TN-7695", "Red");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        boolean isAvailable = parkingLotSystem.searchVechicle(vehicle1);
        Assertions.assertTrue(isAvailable);
    }

    @Test
    void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerObservers(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "White");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "Black");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321", "Blue");
        Vehicle vehicle3 = new Vehicle("audi", "TN-9321", "yellow");
        Vehicle vehicle4 = new Vehicle("audi", "TN-9321", "White");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle4));
        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        boolean IsParkingLotFull = owner.isFullCapacity();
        Assertions.assertFalse(IsParkingLotFull);
    }

    @Test
    void givenWhenParkingLotSpaceIsAvailable_ShouldReturn() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "White");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "Black");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        int availableSlots = parkingLotSystem.getAvailableSlots();
        Assertions.assertEquals(2, availableSlots);
    }

    @Test
    void givenWhenVehicleIsUnParkedAfterFull_ShouldReturnAvailableSlots() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876", "Green");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "Red");
        Vehicle vehicle2 = new Vehicle("Ford", "TN-954", "white");
        Vehicle vehicle3 = new Vehicle("Ford", "TN-954", "white");
        Vehicle vehicle4 = new Vehicle("Ford", "TN-954", "Black");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle4));
        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        int availableSlots = parkingLotSystem.getAvailableSlots();
        Assertions.assertEquals(2, availableSlots);
    }

//    @Test
//    void givenWhenVehicleIsParked_ShouldReturnTimeOfParking() {
//        Vehicle vehicle = new Vehicle("Tata", "TN-9876","Yellow");
//        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
//        String Time=parkingLotSystem.parkingTime();
//        Assertions.assertEquals("19:42",Time);
//    }

    @Test
    void givenWhenVehicleIsParkedByAttendent_IfEvenlyDistributed_ShouldReturnTrue() {
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "White");
        Vehicle vehicle2 = new Vehicle("Ford", "TN-954", "Black");
        Vehicle vehicle3 = new Vehicle("Ford", "TN-954", "Grey");
        Vehicle vehicle4 = new Vehicle("Ford", "TN-954", "Green");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle3);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle4);
        boolean vehicleParked = parkingLotSystem.isVehicleParked(vehicle4);
        Assertions.assertTrue(vehicleParked);
    }

    @Test
    void givenWhenWhiteColourVehicleIsParked_ShouldReturnPosition() {
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "White");
        Vehicle vehicle2 = new Vehicle("Ford", "TN-954", "Black");
        Vehicle vehicle3 = new Vehicle("Ford", "TN-954", "Grey");
        Vehicle vehicle4 = new Vehicle("Ford", "TN-954", "Green");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle3);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle4);
        int position = parkingLotSystem.getPosition(vehicle1,"White");
        Assertions.assertEquals(0,position);

    }

    @Test
    void givenWhenBlueColourVehicleIsParked_ShouldReturnPosition() {
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "White");
        Vehicle vehicle2 = new Vehicle("Ford", "TN-954", "Black");
        Vehicle vehicle3 = new Vehicle("Ford", "TN-954", "Blue");
        Vehicle vehicle4 = new Vehicle("Ford", "TN-954", "Green");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle3);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle4);
        int position = parkingLotSystem.getPosition(vehicle3,"Blue");
        Assertions.assertEquals(1,position);

    }

    @Test
    void givenWhenVehicleNameAndColour_ShouldReturnVehiclePosition() {
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954", "White");
        Vehicle vehicle2 = new Vehicle("Ford", "TN-954", "Black");
        Vehicle vehicle3 = new Vehicle("Ford", "TN-954", "Blue");
        Vehicle vehicle4 = new Vehicle("Ford", "TN-954", "Green");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle2);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle3);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle4);
        int position = parkingLotSystem.getPosition(vehicle3,"Ford","Blue");
        Assertions.assertEquals(1,position);

    }

    @Test
    void givenVehicleNumber_WhenValidated_ShouldReturnTrue() {
        Vehicle vehicle1 = new Vehicle("Ford", "TN-L9547", "White");
        boolean isValidated = parkingLotSystem.validateNumberPlate(vehicle1.getVechicleNo());
        Assertions.assertTrue(isValidated);
    }

}

