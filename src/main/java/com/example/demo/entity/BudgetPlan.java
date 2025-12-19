package com.example.demo.entity;

public class BudgetPlan {

    private int id;
    private String month;
    private double limitAmount;

    public BudgetPlan() {
    }

    public BudgetPlan(int id, String month, double limitAmount) {
        this.id = id;
        this.month = month;
        this.limitAmount = limitAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }
}
