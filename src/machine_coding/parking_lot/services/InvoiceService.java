package machine_coding.parking_lot.services;

import machine_coding.parking_lot.exceptions.*;
import machine_coding.parking_lot.models.*;

public interface InvoiceService {
    public Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException,InvalidGateException;
}
