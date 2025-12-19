package com.example.demo.service;

import com.example.demo.entity.BudgetSummary;

public interface BudgetSummaryService {

    BudgetSummary generateSummary(Long budgetPlanId);

    BudgetSummary getSummary(Long budgetPlanId);
}
