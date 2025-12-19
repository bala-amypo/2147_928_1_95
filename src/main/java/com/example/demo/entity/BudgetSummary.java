package com.example.demo.entity;

public class BudgetSummary {

    private int id;
    private double totalSpent;

    public BudgetSummary() {
    }

    public BudgetSummary(int id, double totalSpent) {
        this.id = id;
        this.totalSpent = totalSpent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
}
