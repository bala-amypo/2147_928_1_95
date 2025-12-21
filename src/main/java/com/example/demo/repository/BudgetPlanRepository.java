package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BudgetPlan;

public interface BudgetPlanRepository
        extends JpaRepository<BudgetPlan, Long> {
}
