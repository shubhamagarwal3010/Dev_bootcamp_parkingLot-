import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {


    ParkingLotOwner parkingLotOwner= new ParkingLotOwner();
    AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();

    @Test
    public void verifyIfCarIsParked() throws Exception {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,2);
        int slotNumber = parkingLot.park(car);
        Assert.assertEquals(car.hashCode(), slotNumber);
    }

    @Test
    public void verifyIfCarIsUnparked() throws Exception {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,2);
        int hashcode = parkingLot.park(car);
        Car unParkedCar = parkingLot.unPark(hashcode);
        Assert.assertEquals(car.hashCode(), unParkedCar.hashCode());
    }

    @Test(expected = Exception.class)
    public void verifyIfUnParkingCarThrowsExceptionForInvalidSlotNumber() throws Exception {
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,2);
        parkingLot.unPark(123);
    }

    @Test(expected = Exception.class)
    public void verifyIfParkingSameCarTwiceThrowsException() throws Exception {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,2);
        int slotNumber1 = parkingLot.park(car);
        Assert.assertEquals(car.hashCode(), slotNumber1);
        parkingLot.park(car);
    }

    @Test(expected = Exception.class)
    public void verifyIfUnParkingSameCarTwiceThrowsException() throws Exception {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,2);
        int hashcode = parkingLot.park(car);
        Car unParkedCar = parkingLot.unPark(hashcode);
        Assert.assertEquals(car.hashCode(), unParkedCar.hashCode());
        parkingLot.unPark(hashcode);
    }

    @Test(expected = Exception.class)
    public void verifyIfParkingMoreThanCapacityThrowsException() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,2);
        int slotNumber1 = parkingLot.park(car1);
        int slotNumber2 = parkingLot.park(car2);
        Assert.assertEquals(car1.hashCode(), slotNumber1);
        Assert.assertEquals(car2.hashCode(), slotNumber2);
        parkingLot.park(car3);

    }
    @Test
    public void verifyIfOwnerIsNotifiedIfLotIsFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,1);
        Car car = new Car();
        parkingLot.park(car);
        Assert.assertTrue(parkingLotOwner.isParkingLotFull());
    }
    @Test
    public void verifyIfSecurityPersonalIsNotifiedIfLotIsFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(parkingLotOwner,airportSecurityPersonal,1);
        Car car = new Car();
        parkingLot.park(car);
        Assert.assertTrue(airportSecurityPersonal.isParkingLotFull());
    }
}