package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.*;

import java.util.*;

public class SlabServiceImpl implements SlabService{

    private SlabRepository slabRepository;

    public SlabServiceImpl(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    public List<Slab> getSlabsByVehicleType(VehicleType vehicleType){
        return slabRepository.getSlabsByVehicleType(vehicleType);
    }
}
