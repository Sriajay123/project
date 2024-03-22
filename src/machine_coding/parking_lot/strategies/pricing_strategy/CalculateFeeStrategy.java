package machine_coding.parking_lot.strategies.pricing_strategy;

import machine_coding.parking_lot.models.*;

import java.util.*;

public interface CalculateFeeStrategy {
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType);
}
