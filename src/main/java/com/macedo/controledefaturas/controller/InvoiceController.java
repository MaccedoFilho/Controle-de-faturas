package com.macedo.controledefaturas.controller;

import com.macedo.controledefaturas.dto.InvoiceDTO;
import com.macedo.controledefaturas.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<Page<InvoiceDTO>> listAll(Pageable pageable) {
        Page<InvoiceDTO> page = invoiceService
                .getAllInvoices(pageable)
                .map(InvoiceDTO::fromEntity);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getById(@PathVariable Long id) {
        var inv = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(InvoiceDTO.fromEntity(inv));
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> create(@Valid @RequestBody InvoiceDTO dto) {
        var created = invoiceService.createInvoice(dto.toEntity());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(InvoiceDTO.fromEntity(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody InvoiceDTO dto
    ) {
        var updated = invoiceService.updateInvoice(id, dto.toEntity());
        return ResponseEntity.ok(InvoiceDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
