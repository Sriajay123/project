package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.*;

import java.util.*;

public class GateRepository {
    private Map<Integer, Gate> map;

    public GateRepository(Map<Integer, Gate> map) {
        this.map = map;
    }

    public GateRepository(){
        this.map=new HashMap<>();
    }

    public Gate getGateById(int gateId){
        return map.get(gateId);
    }
}
