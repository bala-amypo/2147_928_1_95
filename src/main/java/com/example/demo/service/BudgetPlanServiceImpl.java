package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BudgetPlan;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
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
                .orElseThrow(() -> new BadRequestException("User with ID " + userId + " not found"));

        // Check if a plan already exists for this user/month/year
        boolean exists = budgetPlanRepository
                .findByUserAndMonthAndYear(user, plan.getMonth(), plan.getYear())
                .isPresent();

        if (exists) {
            throw new BadRequestException("Budget plan already exists for user " + userId
                    + " for month " + plan.getMonth() + " and year " + plan.getYear());
        }

        plan.setUser(user);
        return budgetPlanRepository.save(plan);
    }

    @Override
    public BudgetPlan getBudgetPlan(Long userId, Integer month, Integer year) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User with ID " + userId + " not found"));

        BudgetPlan plan = budgetPlanRepository
                .findByUserAndMonthAndYear(user, month, year)
                .orElseThrow(() -> new BadRequestException("Budget plan not found for user " + userId
                        + " for month " + month + " and year " + year));

        return plan;
    }
}
