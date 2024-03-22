package machine_coding.parking_lot.strategies.spot_assignment;

import machine_coding.parking_lot.exceptions.*;
import machine_coding.parking_lot.models.*;

public interface AssignSpotStrategy {
    public Spot assignSpot(VehicleType vehicleType,ParkingLot parkingLot) throws NoSpotAvailableException;
}
