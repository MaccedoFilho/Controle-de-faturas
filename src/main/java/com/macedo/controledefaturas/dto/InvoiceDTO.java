package com.macedo.controledefaturas.dto;

import com.macedo.controledefaturas.model.Invoice;
import com.macedo.controledefaturas.model.InvoiceCategory;
import com.macedo.controledefaturas.model.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class InvoiceDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 200, message = "Descrição deve ter até 200 caracteres")
    private String description;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal amount;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dueDate;

    @NotNull(message = "Categoria é obrigatória")
    private InvoiceCategory category;

    @NotNull(message = "Status é obrigatório")
    private PaymentStatus status;


    public InvoiceDTO() {}
    public InvoiceDTO(Long id, String description, BigDecimal amount, LocalDate dueDate, InvoiceCategory category, PaymentStatus status) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public InvoiceCategory getCategory() {
        return category;
    }
    public void setCategory(InvoiceCategory category) {
        this.category = category;

    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Invoice toEntity() {
        Invoice inv = new Invoice();
        inv.setId(this.id);
        inv.setDescription(this.description);
        inv.setAmount(this.amount.doubleValue());
        inv.setDueDate(this.dueDate);
        inv.setCategory(this.category);
        inv.setStatus(this.status);
        return inv;
    }

    public static InvoiceDTO fromEntity(Invoice inv) {
        return new InvoiceDTO(
                inv.getId(),
                inv.getDescription(),
                BigDecimal.valueOf(inv.getAmount()),
                inv.getDueDate(),
                inv.getCategory(),
                inv.getStatus()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceDTO)) return false;
        InvoiceDTO that = (InvoiceDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(description, that.description)
                && Objects.equals(amount, that.amount)
                && Objects.equals(dueDate, that.dueDate)
                && category == that.category
                && status   == that.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, dueDate, category, status);
    }
}