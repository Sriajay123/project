package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.*;

public class ParkingLotServiceImpl implements ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingLot getParkingLotByGateId(int gateId) {
        return parkingLotRepository.getParkingLotByGateId(gateId);
    }
}
