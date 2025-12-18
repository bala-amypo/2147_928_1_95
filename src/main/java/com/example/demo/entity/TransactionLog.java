package com.example.demo.entity;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long userId;
private String category;
private Double amount;
private String description;
private LocalDate transactionDate;
public Long getId() { 
    return id; 
}
public void setId(Long id) { 
    this.id = id; 
}
public Long getUserId() { 
    return userId; 
}
public void setUserId(Long userId) { 
    this.userId = userId; 
}
public String getCategory() {
     return category; 
    }
public void setCategory(String category) { 
    this.category = category; 
}
public Double getAmount() { 
    return amount; 
}
public void setAmount(Double amount) {
     this.amount = amount; 
    }
public String getDescription() { 
    return description; 
}
public void setDescription(String description) { 
    this.description = description; 
}
public LocalDate getTransactionDate() { 
    return transactionDate; 
}
public void setTransactionDate(LocalDate transactionDate) {
this.transactionDate = transactionDate; 
}
public TransactionLog(Long id, Long userId, String category, Double amount, String description,
        LocalDate transactionDate) {
    this.id = id;
    this.userId = userId;
    this.category = category;
    this.amount = amount;
    this.description = description;
    this.transactionDate = transactionDate;
}
public TransactionLog() {
}
}
