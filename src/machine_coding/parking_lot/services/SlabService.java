package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.*;

import java.util.*;

public interface SlabService {
    public List<Slab> getSlabsByVehicleType(VehicleType vehicleType);
}
