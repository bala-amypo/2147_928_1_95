package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.BudgetPlan;
import com.example.demo.service.BudgetPlanService;

@RestController
@RequestMapping("/budget-plans")
public class BudgetPlanController {

    private final BudgetPlanService budgetPlanService;

    public BudgetPlanController(BudgetPlanService budgetPlanService) {
        this.budgetPlanService = budgetPlanService;
    }

    @PostMapping("/{userId}")
    public BudgetPlan createPlan( @PathVariable Long userId, @RequestBody BudgetPlan plan) {
        return budgetPlanService.createBudgetPlan(userId, plan);
    }

    @GetMapping("/{userId}/{month}/{year}")
    public BudgetPlan getPlan( @PathVariable Long userId, @PathVariable Integer month, @PathVariable Integer year) {
        return budgetPlanService.getBudgetPlan(userId, month, year);
    }
}