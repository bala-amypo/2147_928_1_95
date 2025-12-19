package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BudgetSummary;
import com.example.demo.entity.BudgetPlan;

@Repository
public interface BudgetSummaryRepository
        extends JpaRepository<BudgetSummary, Long> {

    BudgetSummary findByBudgetPlan(BudgetPlan budgetPlan);
}
