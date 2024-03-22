package machine_coding.parking_lot.services;

import machine_coding.parking_lot.exceptions.*;
import machine_coding.parking_lot.factories.*;
import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.*;
import machine_coding.parking_lot.strategies.pricing_strategy.*;

import java.util.*;

public class InvoiceServiceImpl implements InvoiceService{

    private TicketService ticketService;

    private GateService gateService;

    private CalculateFeeStrategyFactory factory;

    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketService ticketService, GateService gateService, CalculateFeeStrategyFactory factory, InvoiceRepository invoiceRepository) {
        this.ticketService = ticketService;
        this.gateService = gateService;
        this.factory = factory;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException,InvalidGateException {
        Ticket ticket = this.ticketService.getTicketById(ticketId);
        if(ticket==null){
            throw new InvalidTicketException("Ticket is not present in DB");
        }
        Gate gate = this.gateService.getGateById(gateId);
        if(gate == null){
            throw new InvalidGateException("Gate is not present in DB");
        }
        if(gate.getGateType()==GateType.ENTRY){
            throw new InvalidGateException("Invoice cannot be created at entry gate");
        }

        Date entryDate = ticket.getEntryTime();
        Date exitDate = new Date();
        CalculateFeeStrategy calculateFeeStrategy = factory.getCalculateFeesStrategy(exitDate);
        double totalAmount = calculateFeeStrategy.calculateFees(entryDate, exitDate, ticket.getVehicle().getVehicleType());

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setName("Parking fees");
        invoiceDetail.setPrice(totalAmount);


        Invoice invoice = new Invoice();
        invoice.setDetails(Arrays.asList(invoiceDetail));
        invoice.setTicket(ticket);
        invoice.setExitTime(exitDate);
        return invoiceRepository.insertInvoice(invoice);
    }
}
