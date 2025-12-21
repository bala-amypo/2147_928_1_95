package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BudgetPlan;
import com.example.demo.entity.User;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BudgetPlanService;

@Service
public class BudgetPlanServiceImpl implements BudgetPlanService {

    private final BudgetPlanRepository budgetPlanRepository;
    private final UserRepository userRepository;

    public BudgetPlanServiceImpl(
            BudgetPlanRepository budgetPlanRepository,
            UserRepository userRepository) {

        this.budgetPlanRepository = budgetPlanRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BudgetPlan createBudgetPlan(Long userId, BudgetPlan plan) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        plan.setUser(user);
        return budgetPlanRepository.save(plan);
    }

    @Override
    public BudgetPlan getBudgetPlan(Long userId, Integer month, Integer year) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return budgetPlanRepository
                .findByUserAndMonthAndYear(user, month, year)
                .orElseThrow(() ->
                        new RuntimeException("Budget plan not found"));
    }
}
