package com.macedo.controledefaturas.service;

import com.macedo.controledefaturas.model.Invoice;
import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Long id, Invoice invoice);
    void deleteInvoice(Long id);
}
