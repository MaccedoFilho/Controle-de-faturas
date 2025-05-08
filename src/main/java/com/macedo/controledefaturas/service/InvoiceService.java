package com.macedo.controledefaturas.service;

import com.macedo.controledefaturas.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Page<Invoice> getAllInvoices(Pageable pageable);
    Invoice getInvoiceById(Long id);
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Long id, Invoice invoice);
    void deleteInvoice(Long id);
}
