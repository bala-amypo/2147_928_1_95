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

        if (plan.getMonth() < 1 || plan.getMonth() > 12) {
            throw new BadRequestException("Invalid month");
        }

        if (plan.getIncomeTarget() < 0 || plan.getExpenseLimit() < 0) {
            throw new BadRequestException("Invalid budget values");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        if (budgetPlanRepository
                .findByUserAndMonthAndYear(user, plan.getMonth(), plan.getYear())
                .isPresent()) {
            throw new BadRequestException("Budget plan already exists");
        }

        plan.setUser(user);
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
