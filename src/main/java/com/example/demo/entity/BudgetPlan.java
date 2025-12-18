package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class BudgetPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long userId;
private Integer month;
private Integer year;
private Double incomeTarget;
private Double expenseLimit;

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
public Integer getMonth() { 
    return month; 
}
public void setMonth(Integer month) { 
    this.month = month; 
}
public Integer getYear() { 
    return year; 
}
public void setYear(Integer year) { 
    this.year = year; 
}
public Double getIncomeTarget() { 
    return incomeTarget; 
}
public void setIncomeTarget(Double incomeTarget) { 
    this.incomeTarget = incomeTarget; 
}
public Double getExpenseLimit() { 
    return expenseLimit; 
}
public void setExpenseLimit(Double expenseLimit) { 
    this.expenseLimit = expenseLimit; 
}
public BudgetPlan(Long id, Long userId, Integer month, Integer year, Double incomeTarget, Double expenseLimit) {
    this.id = id;
    this.userId = userId;
    this.month = month;
    this.year = year;
    this.incomeTarget = incomeTarget;
    this.expenseLimit = expenseLimit;
}
public BudgetPlan() {

}
}
