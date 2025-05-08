package com.macedo.controledefaturas.service;

import com.macedo.controledefaturas.exception.ResourceNotFoundException;
import com.macedo.controledefaturas.model.Invoice;
import com.macedo.controledefaturas.repository.InvoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repo;

    public InvoiceServiceImpl(InvoiceRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found: " + id));
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return repo.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Long id, Invoice invoiceData) {
        Invoice inv = getInvoiceById(id);
        inv.setDescription(invoiceData.getDescription());
        inv.setAmount(invoiceData.getAmount());
        inv.setDueDate(invoiceData.getDueDate());
        return repo.save(inv);
    }

    @Override
    public void deleteInvoice(Long id) {
        Invoice inv = getInvoiceById(id);
        repo.delete(inv);
    }
}
