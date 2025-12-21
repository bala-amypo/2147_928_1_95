package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BudgetPlan;
import com.example.demo.entity.BudgetSummary;

public interface BudgetSummaryRepository
        extends JpaRepository<BudgetSummary, Long> {

    Optional<BudgetSummary> findFirstByBudgetPlanOrderByGeneratedAtDesc(
            BudgetPlan budgetPlan
    );
}
