package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.*;

public interface VehicleService {
    public Vehicle createIfNotExists(String vehicleNumber, VehicleType vehicleType);

}
