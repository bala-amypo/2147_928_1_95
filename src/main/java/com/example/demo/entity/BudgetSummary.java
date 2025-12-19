package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BudgetSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private BudgetPlan budgetPlan;

    private Double totalIncome;
    private Double totalExpense;
    private String status;
    private LocalDateTime generatedAt;
    public BudgetSummary() {
    }
    public BudgetSummary(
            Long id,
            BudgetPlan budgetPlan,
            Double totalIncome,
            Double totalExpense,
            String status) {

        this.id = id;
        this.budgetPlan = budgetPlan;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.status = status;
    }

    @PrePersist
    public void generateTime() {
        this.generatedAt = LocalDateTime.now();
    }

    /* ---------- Getters & Setters ---------- */

    public Long getId() {
        return id;
    }

    public BudgetPlan getBudgetPlan() {
        return budgetPlan;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBudgetPlan(BudgetPlan budgetPlan) {
        this.budgetPlan = budgetPlan;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
