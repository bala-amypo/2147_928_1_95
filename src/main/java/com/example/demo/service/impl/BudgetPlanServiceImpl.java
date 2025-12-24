package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.BudgetPlan;
import com.example.demo.model.User;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BudgetPlanService;

public class BudgetPlanServiceImpl implements BudgetPlanService {

    private final BudgetPlanRepository budgetPlanRepository;
    private final UserRepository userRepository;

    public BudgetPlanServiceImpl(BudgetPlanRepository budgetPlanRepository,
                                 UserRepository userRepository) {
        this.budgetPlanRepository = budgetPlanRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BudgetPlan createBudgetPlan(Long userId, BudgetPlan plan) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        plan.setUser(user);
        plan.validate();

        if (budgetPlanRepository
                .findByUserAndMonthAndYear(user, plan.getMonth(), plan.getYear())
                .isPresent()) {
            throw new BadRequestException("Budget plan already exists");
        }

        return budgetPlanRepository.save(plan);
    }

    @Override
    public BudgetPlan getBudgetPlan(Long userId, Integer month, Integer year) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return budgetPlanRepository
                .findByUserAndMonthAndYear(user, month, year)
                .orElseThrow(() -> new BadRequestException("Budget plan not found"));
    }
}
