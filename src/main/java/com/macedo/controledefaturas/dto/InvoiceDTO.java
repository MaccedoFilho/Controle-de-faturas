package com.macedo.controledefaturas.dto;

import com.macedo.controledefaturas.model.Invoice;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class InvoiceDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal amount;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dueDate;

    public InvoiceDTO() {}
    public InvoiceDTO(Long id, String description, BigDecimal amount, LocalDate dueDate) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
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

    public Invoice toEntity() {
        Invoice inv = new Invoice();
        inv.setId(this.id);
        inv.setDescription(this.description);
        inv.setAmount(this.amount.doubleValue());
        inv.setDueDate(this.dueDate);
        return inv;
    }

    public static InvoiceDTO fromEntity(Invoice inv) {
        return new InvoiceDTO(
                inv.getId(),
                inv.getDescription(),
                BigDecimal.valueOf(inv.getAmount()),
                inv.getDueDate()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceDTO)) return false;
        InvoiceDTO that = (InvoiceDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, dueDate);
    }
}


