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
        parkingLotSystem = new ParkingLotSystem(3);
    }

    @Test
    void givenAVechicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVechicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
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
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("ford", "TN-1234");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL, vehicle1));
    }

    @Test
    void givensameVechicle_WhenParked_ShouldNotAdded() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle));
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_shouldReturnException() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
        Vehicle vehicle3 = new Vehicle("Audi", "TN-9800");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle2);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3));
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerObservers(airportSecurity);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
        Vehicle vehicle3 = new Vehicle("audi", "TN-9321");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3));
        boolean fullCapacity = airportSecurity.isFullCapacity();
        Assertions.assertTrue(fullCapacity);
    }

    @Test
    void givenVehicle_WhenParkingLotIsNotFull_shouldNotifyOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle);
        boolean isFull = owner.isFullCapacity();
        Assertions.assertFalse(isFull);
    }

    @Test
    void givenVehicle_WhenParkingLotFull_shouldNotifyOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerObservers(owner);
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
        Vehicle vehicle3 = new Vehicle("audi", "TN-9321");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3));
        boolean IsParkingLotFull = owner.isFullCapacity();
        Assertions.assertTrue(IsParkingLotFull);
    }

    @Test
    void givenVechicle_WhenParkedByAttendent_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenVechicle_WhenUnParkedByAttendent_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        boolean isParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAParkedVehicle_WhenFound_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("BMW", "TN-9765");
        Vehicle vehicle2 = new Vehicle("Ferrari", "TN-7695");
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
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        Vehicle vehicle2 = new Vehicle("BMW", "TN-9321");
        Vehicle vehicle3 = new Vehicle("audi", "TN-9321");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3));
        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        boolean IsParkingLotFull = owner.isFullCapacity();
        Assertions.assertFalse(IsParkingLotFull);
    }

    @Test
    void givenWhenParkingLotSpaceIsAvailable_ShouldReturn() {
        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
        int availableSlots=parkingLotSystem.getAvailableSlots();
        Assertions.assertEquals(1,availableSlots);
    }
//    @Test
//    void givenWhenVehicleIsUnParkedAfterFull_ShouldReturnAvailableSlots() {
//        Vehicle vehicle = new Vehicle("Tata", "TN-9876");
//        Vehicle vehicle1 = new Vehicle("Ford", "TN-954");
//        Vehicle vehicle2 = new Vehicle("Ford", "TN-954");
//        Vehicle vehicle3 = new Vehicle("Ford", "TN-954");
//        parkingLotSystem.park(ParkingLotSystem.ParkingType.ATTENDENT, vehicle);
//        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle1);
//        parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle2);
//        Assertions.assertThrows(ParkingLotException.class,
//                () -> parkingLotSystem.park(ParkingLotSystem.ParkingType.NORMAL, vehicle3));
//        parkingLotSystem.unPark(ParkingLotSystem.ParkingType.NORMAL,vehicle);
//        int availableSlots=parkingLotSystem.getAvailableSlots();
//        Assertions.assertEquals(1,availableSlots);
//    }
}
