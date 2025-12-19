package com.example.demo.service;

import com.example.demo.entity.BudgetPlan;

public interface BudgetPlanService {

    BudgetPlan createBudgetPlan(Long userId, BudgetPlan plan);

    BudgetPlan getBudgetPlan(Long userId, Integer month, Integer year);
}
