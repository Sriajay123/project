package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.*;

import java.util.*;

public class VehicleRepository {
     private Map<Integer, Vehicle>map;

     private static int id=1;

    public VehicleRepository(Map<Integer, Vehicle> map) {
        this.map = map;
    }

    public VehicleRepository() {
        this.map=new HashMap<>();
    }


    public Vehicle createIfNotExists(String vehicleNumber,VehicleType vehicleType){
        for (Map.Entry<Integer, Vehicle> entry : map.entrySet()) {
            Vehicle vehicle= entry.getValue();
            if(vehicle.getVehicleNumber().equals(vehicleNumber)){
                return vehicle;
            }
        }
        Vehicle vehicle=new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setId(id);
        map.put(id++,vehicle);
        return  vehicle;
    }
}
