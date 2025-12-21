package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    private Double amount;
    private String description;
    private LocalDate transactionDate;
    public TransactionLog() {
    }
    public TransactionLog(
            Long id,
            User user,
            Category category,
            Double amount,
            String description,
            LocalDate transactionDate) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }
    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Category getCategory() {
        return category;
    }
    public Double getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
