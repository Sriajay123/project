package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.*;

import java.util.*;

public class InvoiceRepository {
    private Map<Integer, Invoice> map;

    public InvoiceRepository(Map<Integer, Invoice> map) {
        this.map = map;
    }

    public InvoiceRepository() {
        this.map = new HashMap<>();
    }

    private static int ID = 0;
    public Invoice insertInvoice(Invoice invoice){
        invoice.setId(ID);
        map.put(ID++, invoice);
        return invoice;
    }
}
