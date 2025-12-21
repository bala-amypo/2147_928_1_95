package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BudgetPlan;
import com.example.demo.entity.BudgetSummary;
import com.example.demo.exception.BadRequestException;
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
                .orElseThrow(() -> new BadRequestException("Budget plan with ID " + budgetPlanId + " not found"));

        return budgetSummaryRepository
                .findFirstByBudgetPlanOrderByGeneratedAtDesc(plan)
                .orElseThrow(() -> new BadRequestException("Budget summary not generated yet for plan ID " + budgetPlanId));
    }

    @Override
    public BudgetSummary generateSummary(Long budgetPlanId) {

        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new BadRequestException("Budget plan with ID " + budgetPlanId + " not found"));

        return budgetSummaryRepository
                .findFirstByBudgetPlanOrderByGeneratedAtDesc(plan)
                .orElseGet(() -> {
                    BudgetSummary summary = new BudgetSummary(
                            null,
                            plan,
                            0.0,
                            0.0,
                            "UNDER_LIMIT"
                    );
                    return budgetSummaryRepository.save(summary);
                });
    }
}
