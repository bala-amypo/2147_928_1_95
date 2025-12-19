package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BudgetPlan;
import com.example.demo.entity.BudgetSummary;
import com.example.demo.entity.TransactionLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.BudgetPlanRepository;
import com.example.demo.repository.BudgetSummaryRepository;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.service.BudgetSummaryService;

@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final BudgetSummaryRepository budgetSummaryRepository;
    private final BudgetPlanRepository budgetPlanRepository;
    private final TransactionLogRepository transactionLogRepository;

    public BudgetSummaryServiceImpl(
            BudgetSummaryRepository budgetSummaryRepository,
            BudgetPlanRepository budgetPlanRepository,
            TransactionLogRepository transactionLogRepository) {

        this.budgetSummaryRepository = budgetSummaryRepository;
        this.budgetPlanRepository = budgetPlanRepository;
        this.transactionLogRepository = transactionLogRepository;
    }

    @Override
    public BudgetSummary generateSummary(Long budgetPlanId) {

        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new BadRequestException("Budget plan not found"));

        YearMonth ym = YearMonth.of(plan.getYear(), plan.getMonth());
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        List<TransactionLog> logs =
                transactionLogRepository.findByUserAndTransactionDateBetween(
                        plan.getUser(), start, end);

        double income = 0;
        double expense = 0;

        for (TransactionLog t : logs) {
            if ("INCOME".equals(t.getCategory().getType())) {
                income += t.getAmount();
            } else {
                expense += t.getAmount();
            }
        }

        String status = expense <= plan.getExpenseLimit()
                ? "UNDER_LIMIT"
                : "OVER_LIMIT";

        BudgetSummary summary = new BudgetSummary(
                null,
                plan,
                income,
                expense,
                status
        );

        return budgetSummaryRepository.save(summary);
    }

    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {

        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new BadRequestException("Budget plan not found"));

        return budgetSummaryRepository
                .findByBudgetPlan(plan)
                .orElseThrow(() -> new BadRequestException("Summary not found"));
    }
}
