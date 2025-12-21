package com.example.demo.entity;
import jakarta.persistence.*;
@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "month", "year"}
    )
)
public class BudgetPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private Integer month;
    private Integer year;
    private Double incomeTarget;
    private Double expenseLimit;
    public BudgetPlan() {
    }
    public BudgetPlan(Long id,User user,Integer month,Integer year,Double incomeTarget,Double expenseLimit) {
        this.id = id;
        this.user = user;
        this.month = month;
        this.year = year;
        this.incomeTarget = incomeTarget;
        this.expenseLimit = expenseLimit;
    }
    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Integer getMonth() {
        return month;
    }
    public Integer getYear() {
        return year;
    }
    public Double getIncomeTarget() {
        return incomeTarget;
    }
    public Double getExpenseLimit() {
        return expenseLimit;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public void setIncomeTarget(Double incomeTarget) {
        this.incomeTarget = incomeTarget;
    }
    public void setExpenseLimit(Double expenseLimit) {
        this.expenseLimit = expenseLimit;
    }
}