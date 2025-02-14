package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double amount;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}

