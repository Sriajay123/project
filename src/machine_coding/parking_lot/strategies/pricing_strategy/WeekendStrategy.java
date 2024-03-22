package machine_coding.parking_lot.strategies.pricing_strategy;

import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.services.*;
import machine_coding.parking_lot.utils.*;

import java.util.*;

public class WeekendStrategy implements CalculateFeeStrategy{

     private SlabService slabService;

    public WeekendStrategy(SlabService slabService) {
        this.slabService = slabService;
    }

    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> slabs = this.slabService.getSlabsByVehicleType(vehicleType);
        int hours = DateTimeUtils.calcHours(entryTime, exitTime);
        double totalAmount = 0;
        for(Slab slab: slabs){
            if(hours >= slab.getStartHour() && slab.getEndHour() != -1){
                if(hours >= slab.getEndHour()){
                    totalAmount += (slab.getEndHour() - slab.getStartHour()) * slab.getPricePerHour();
                } else {
                    totalAmount += (hours - slab.getStartHour()) * slab.getPricePerHour();
                }
            } else if (slab.getEndHour() == -1) {
                totalAmount += (hours - slab.getStartHour()) * slab.getPricePerHour();
            }
            else {
                break;
            }
        }
        return totalAmount;
    }
}
