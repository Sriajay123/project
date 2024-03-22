package machine_coding.parking_lot.strategies.spot_assignment;

import machine_coding.parking_lot.exceptions.*;
import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.strategies.spot_assignment.*;

public class NearestFirstSpotAssignmentStrategy implements AssignSpotStrategy {
    @Override
    public Spot assignSpot(VehicleType vehicleType, ParkingLot parkingLot)throws NoSpotAvailableException  {

        for (Floor floor : parkingLot.getFloors()) {
            if(floor.getFloorStatus().equals(FloorStatus.OPERATIONAL)){
                for (Section section : floor.getSections()) {
                    for (Spot spot : section.getSpots()) {
                       if(spot.getVehicleType().equals(vehicleType)&&spot.getStatus().equals(SpotStatus.UNOCCUPIED)){
                           spot.setStatus(SpotStatus.OCCUPIED);
                           return spot;
                       }
                    }
                }
            }
        }

        throw new NoSpotAvailableException("No spots available for " + vehicleType);
    }
}
