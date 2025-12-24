package com.example.demo.controller;

import com.example.demo.model.BudgetPlan;
import com.example.demo.service.BudgetPlanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budgets")
public class BudgetPlanController {

    private final BudgetPlanService budgetPlanService;

    public BudgetPlanController(BudgetPlanService budgetPlanService) {
        this.budgetPlanService = budgetPlanService;
    }

    @PostMapping("/{userId}")
    public BudgetPlan createBudgetPlan(@PathVariable Long userId,
                                       @RequestBody BudgetPlan plan) {
        return budgetPlanService.createBudgetPlan(userId, plan);
    }

    @GetMapping("/{userId}/{month}/{year}")
    public BudgetPlan getBudgetPlan(@PathVariable Long userId,
                                    @PathVariable Integer month,
                                    @PathVariable Integer year) {
        return budgetPlanService.getBudgetPlan(userId, month, year);
    }
}
