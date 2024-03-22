package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.*;
import machine_coding.parking_lot.strategies.spot_assignment.*;

import java.util.*;

public class TicketServiceImpl implements  TicketService{

    private GateService gateService;
    
    private VehicleService vehicleService;

    private ParkingLotService parkingLotService;

    private TicketRepository ticketRepository;

    private AssignSpotStrategy assignSpotStrategy;

    public TicketServiceImpl(GateService gateService, VehicleService vehicleService, ParkingLotService parkingLotService, TicketRepository ticketRepository, AssignSpotStrategy assignSpotStrategy) {
        this.gateService = gateService;
        this.vehicleService = vehicleService;
        this.parkingLotService = parkingLotService;
        this.ticketRepository = ticketRepository;
        this.assignSpotStrategy = assignSpotStrategy;
    }

    @Override
    public Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType)throws Exception {
        Gate gate = gateService.getGateById(gateId);
        VehicleType type=VehicleType.getTypeFromString(vehicleType);
        Vehicle vehicle = vehicleService.createIfNotExists(vehicleNumber, type);
        ParkingLot parkingLot = parkingLotService.getParkingLotByGateId(gateId);
        if(parkingLot==null){
            throw new Exception("Invalid gateId");
        }
       Spot spot= assignSpotStrategy.assignSpot(type,parkingLot);
        Ticket ticket=new Ticket();
        ticket.setAssignedSpot(spot);
        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());

        return ticketRepository.insertTicket(ticket);

    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }
}
