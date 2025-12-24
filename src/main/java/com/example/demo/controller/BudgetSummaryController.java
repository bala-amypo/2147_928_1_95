package com.example.demo.controller;

import com.example.demo.model.BudgetSummary;
import com.example.demo.service.BudgetSummaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/summary")
public class BudgetSummaryController {

    private final BudgetSummaryService budgetSummaryService;

    public BudgetSummaryController(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    @PostMapping("/generate/{budgetPlanId}")
    public BudgetSummary generateSummary(@PathVariable Long budgetPlanId) {
        return budgetSummaryService.generateSummary(budgetPlanId);
    }

    @GetMapping("/{budgetPlanId}")
    public BudgetSummary getSummary(@PathVariable Long budgetPlanId) {
        return budgetSummaryService.getSummary(budgetPlanId);
    }
}
