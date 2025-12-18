package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.BudgetSummary;
public interface BudgetSummaryService {
BudgetSummary insert(BudgetSummary s);
List<BudgetSummary> getAll();
Optional<BudgetSummary> getOne(Long id);
void delete(Long id);
}
