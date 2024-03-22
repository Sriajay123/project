package machine_coding.parking_lot;

import machine_coding.parking_lot.controllers.*;
import machine_coding.parking_lot.dtos.*;
import machine_coding.parking_lot.factories.*;
import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.*;
import machine_coding.parking_lot.services.*;
import machine_coding.parking_lot.strategies.spot_assignment.*;

import java.util.*;

public class ParkingLotRunner {
    public static void main(String[] args) {

        Gate gate1 = new Gate();
        gate1.setGateType(GateType.ENTRY);
        gate1.setName("1A");
        gate1.setOperator(new Operator());
        gate1.setId(1);

        Gate gate2 = new Gate();
        gate2.setId(2);
        gate2.setOperator(new Operator());
        gate2.setName("4Z");
        gate2.setGateType(GateType.EXIT);

        Map<Integer, Gate> gateMap = new HashMap<Integer, Gate>(){{
            put(1, gate1);
            put(2, gate2);
        }};

        List<Spot> spots = Arrays.asList(new Spot("1A", SpotStatus.UNOCCUPIED, VehicleType.CAR)
                //, new Spot("1B", SpotStatus.UNOCCUPIED, VehicleType.CAR)
        );
        List<Section> sections = new ArrayList<>();
        Section section = new Section();
        section.setName("AA");
        section.setId(1);
        section.setSpots(spots);
        sections.add(section);

        List<Floor> floors = new ArrayList<>();
        Floor floor = new Floor();
        floor.setFloorNum(1);
        floor.setFloorStatus(FloorStatus.OPERATIONAL);
        floor.setSections(sections);
        floor.setId(1);
        floors.add(floor);

        List<Gate> gates = Arrays.asList(gate1, gate2);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setFloors(floors);
        parkingLot.setGates(gates);
        parkingLot.setId(1);

        Map<Integer, ParkingLot> parkingLotMap = new HashMap<Integer, ParkingLot>(){{
            put(1, parkingLot);
        }};

        Slab slab1 = new Slab(1,VehicleType.CAR, 0, 2, 10);
        Slab slab2 = new Slab(2, VehicleType.CAR, 2, 4, 20);
        Slab slab3 = new Slab(3, VehicleType.CAR, 4, 8, 25);
        Slab slab4 = new Slab(4, VehicleType.CAR, 8, -1, 40);

        Map<Integer,Slab> slabMap = new HashMap<Integer, Slab>(){{
            put(1, slab1);
            put(2, slab2);
            put(3, slab3);
            put(4, slab4);
        }};

        SlabRepository slabRepository = new SlabRepository(slabMap);
        InvoiceRepository invoiceRepository = new InvoiceRepository();
        SlabService slabService=new SlabServiceImpl(slabRepository);

        CalculateFeeStrategyFactory calculateFeesStrategyFactory = new CalculateFeeStrategyFactory(slabService);





        GateRepository gateRepository = new GateRepository(gateMap);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotMap);
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        GateService gateService = new GateServiceImpl(gateRepository);
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository);
        ParkingLotService parkingLotService=new ParkingLotServiceImpl(parkingLotRepository);

        AssignSpotStrategy assignSpotStrategy  = new NearestFirstSpotAssignmentStrategy();
        TicketService ticketService = new TicketServiceImpl(gateService, vehicleService,parkingLotService,ticketRepository,assignSpotStrategy);

        InvoiceService invoiceService = new InvoiceServiceImpl( ticketService,gateService, calculateFeesStrategyFactory, invoiceRepository);
        TicketController ticketController = new TicketController(ticketService);
        InvoiceController invoiceController = new InvoiceController(invoiceService);

        // Create objects of generateticketrequestdto and call generateTicket method;
        GenerateTicketRequestDto requestDto = new GenerateTicketRequestDto();
        requestDto.setGateId(1);
        requestDto.setVehicleType(VehicleType.CAR.toString());
        requestDto.setVehicleNumber("KA 05 1234");

        GenerateTicketResponseDto responseDto = ticketController.generateTicket(requestDto);
        System.out.println(responseDto);
        int ticketId = responseDto.getTicket().getId();


        requestDto.setVehicleNumber("KA 05 6789");
        responseDto = ticketController.generateTicket(requestDto);
        System.out.println(responseDto);

        try {
            Thread.sleep(5000);
        } catch (Exception e){
            System.out.println("Exception while sleeping");
        }

        GenerateInvoiceRequestDto generateInvoiceRequestDto = new GenerateInvoiceRequestDto();
        generateInvoiceRequestDto.setTicketId(ticketId);
        generateInvoiceRequestDto.setGateId(gate2.getId());

        GenerateInvoiceResponseDto generateInvoiceResponseDto = invoiceController.generateInvoice(generateInvoiceRequestDto);
        System.out.println(generateInvoiceResponseDto);

    }

    }

