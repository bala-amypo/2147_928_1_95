package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class BudgetSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long budgetPlanId;
private Double totalIncome;
private Double totalExpense;
private String status;
public Long getId() {
     return id; 
    }
public void setId(Long id) { 
    this.id = id; 
}
public Long getBudgetPlanId() { 
    return budgetPlanId; 
}
public void setBudgetPlanId(Long budgetPlanId) { 
    this.budgetPlanId = budgetPlanId; 
}
public Double getTotalIncome() { 
    return totalIncome; 
}
public void setTotalIncome(Double totalIncome) { 
    this.totalIncome = totalIncome; 
}
public Double getTotalExpense() { 
    return totalExpense; 
}
public void setTotalExpense(Double totalExpense) { 
    this.totalExpense = totalExpense; 
}
public String getStatus() { 
    return status; 
}
public void setStatus(String status) { 
    this.status = status; 
}
public BudgetSummary(Long id, Long budgetPlanId, Double totalIncome, Double totalExpense, String status) {
    this.id = id;
    this.budgetPlanId = budgetPlanId;
    this.totalIncome = totalIncome;
    this.totalExpense = totalExpense;
    this.status = status;
}
public BudgetSummary() {

}
}
