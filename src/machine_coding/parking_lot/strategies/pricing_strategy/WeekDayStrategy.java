package machine_coding.parking_lot.strategies.pricing_strategy;

import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.utils.*;

import java.util.*;

public class WeekDayStrategy implements CalculateFeeStrategy{
    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        return DateTimeUtils.calcHours(entryTime, exitTime) * 10;
    }
}
