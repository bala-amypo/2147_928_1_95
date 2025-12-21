package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BudgetPlan;
import com.example.demo.entity.BudgetSummary;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.BudgetSummaryRepository;
import com.example.demo.service.BudgetSummaryService;

@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final BudgetSummaryRepository budgetSummaryRepository;
    private final BudgetPlanRepository budgetPlanRepository;

    public BudgetSummaryServiceImpl(
            BudgetSummaryRepository budgetSummaryRepository,
            BudgetPlanRepository budgetPlanRepository) {

        this.budgetSummaryRepository = budgetSummaryRepository;
        this.budgetPlanRepository = budgetPlanRepository;
    }

    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {

        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new RuntimeException("Budget plan not found"));

        BudgetSummary summary = budgetSummaryRepository.findByBudgetPlan(plan);

        // ✅ AUTO-CREATE if missing (prevents 500)
        if (summary == null) {
            summary = new BudgetSummary(
                    null,
                    plan,
                    0.0,
                    0.0,
                    "UNDER_LIMIT"
            );
            summary = budgetSummaryRepository.save(summary);
        }

        return summary;
    }

    @Override
    public BudgetSummary generateSummary(Long budgetPlanId) {

        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new RuntimeException("Budget plan not found"));

        BudgetSummary existing = budgetSummaryRepository.findByBudgetPlan(plan);
        if (existing != null) {
            return existing;
        }

        BudgetSummary summary = new BudgetSummary(
                null,
                plan,
                0.0,
                0.0,
                "UNDER_LIMIT"
        );

        return budgetSummaryRepository.save(summary);
    }
}
