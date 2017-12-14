import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParkingLot {
    private Map<Integer, Car> parkingSlots = new HashMap<Integer, Car>();
    ParkingLotOwner parkingLotOwner;
    AirportSecurityPersonal airportSecurityPersonal;
    private int capacity=3;
    private int slotCounter = 0;

    public ParkingLot(ParkingLotOwner parkingLotOwner, AirportSecurityPersonal airportSecurityPersonal, int capacity) {
        this.parkingLotOwner = parkingLotOwner;
        this.airportSecurityPersonal = airportSecurityPersonal;
        this.capacity = capacity;
    }

    public Integer park(Car car) throws Exception {
        if(car!=null && !parkingSlots.containsValue(car) && !isParkingLotFull()) {
            parkingSlots.put(car.hashCode(), car);
            slotCounter++;
            if(isParkingLotFull())
                notifyConcernedPeople();
            return car.hashCode();
        }
        throw new Exception("Could not able to park the car");
    }

    public Car unPark(int slotNumber) throws Exception{
        if(parkingSlots.containsKey(slotNumber)){
            Car car = parkingSlots.get(slotNumber);
            parkingSlots.remove(slotNumber);
            slotCounter--;
            return car;
        }
        throw new Exception("could not unpark the car");
    }
    private boolean isParkingLotFull(){
        if(capacity==slotCounter)
            return true;
        return false;
    }
    private void notifyConcernedPeople(){
        parkingLotOwner.setParkingLotFull(true);
        airportSecurityPersonal.setParkingLotFull(true);
    }
}
