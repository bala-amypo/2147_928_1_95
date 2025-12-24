package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.BudgetSummaryService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final BudgetSummaryRepository budgetSummaryRepository;
    private final BudgetPlanRepository budgetPlanRepository;
    private final TransactionLogRepository transactionLogRepository;

    public BudgetSummaryServiceImpl(BudgetSummaryRepository budgetSummaryRepository,
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

        LocalDate start = LocalDate.of(plan.getYear(), plan.getMonth(), 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<TransactionLog> logs =
                transactionLogRepository.findByUserAndTransactionDateBetween(
                        plan.getUser(), start, end);

        double income = 0;
        double expense = 0;

        for (TransactionLog log : logs) {
            if (Category.TYPE_INCOME.equals(log.getCategory().getType())) {
                income += log.getAmount();
            } else {
                expense += log.getAmount();
            }
        }

        BudgetSummary summary = new BudgetSummary();
        summary.setBudgetPlan(plan);
        summary.setTotalIncome(income);
        summary.setTotalExpense(expense);
        summary.setStatus(
                expense <= plan.getExpenseLimit()
                        ? BudgetSummary.STATUS_UNDER_LIMIT
                        : BudgetSummary.STATUS_OVER_LIMIT
        );

        return budgetSummaryRepository.save(summary);
    }

    @Override
    public BudgetSummary getSummary(Long budgetPlanId) {
        BudgetPlan plan = budgetPlanRepository.findById(budgetPlanId)
                .orElseThrow(() -> new BadRequestException("Budget plan not found"));

        return budgetSummaryRepository.findByBudgetPlan(plan)
                .orElseThrow(() -> new BadRequestException("Summary not found"));
    }
}
