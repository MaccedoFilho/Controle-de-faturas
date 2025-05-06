package com.macedo.controledefaturas.controller;

import com.macedo.controledefaturas.model.Invoice;
import com.macedo.controledefaturas.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class invoiceController {

    private final InvoiceService invoiceService;

    public invoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @GetMapping                          // 3) GET /invoices
    public ResponseEntity<List<Invoice>> listAll() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        Invoice saved = invoiceService.createInvoice(invoice);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(
            @PathVariable Long id,
            @RequestBody Invoice invoice
    ) {
        Invoice updated = invoiceService.updateInvoice(id, invoice);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
