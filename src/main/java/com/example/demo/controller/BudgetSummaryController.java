package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.BudgetSummary;
import com.example.demo.service.BudgetSummaryService;

@RestController
@RequestMapping("/budget-summary")
public class BudgetSummaryController {

    private final BudgetSummaryService budgetSummaryService;

    public BudgetSummaryController(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    @PostMapping("/{budgetPlanId}")
    public BudgetSummary generateSummary(
            @PathVariable Long budgetPlanId) {

        return budgetSummaryService.generateSummary(budgetPlanId);
    }

    @GetMapping("/{budgetPlanId}")
    public BudgetSummary getSummary(
            @PathVariable Long budgetPlanId) {

        return budgetSummaryService.getSummary(budgetPlanId);
    }
}
