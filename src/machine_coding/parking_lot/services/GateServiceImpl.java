package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.*;

public class GateServiceImpl implements GateService{

    private GateRepository gateRepository;

    public GateServiceImpl(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    @Override
    public Gate getGateById(int gateId) {
        return gateRepository.getGateById(gateId) ;
    }
}
