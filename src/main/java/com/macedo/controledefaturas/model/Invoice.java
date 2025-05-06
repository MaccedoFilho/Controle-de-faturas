package com.macedo.controledefaturas.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String description;
    private Double amount;
    private LocalDate dueDate;

    public Invoice() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}
