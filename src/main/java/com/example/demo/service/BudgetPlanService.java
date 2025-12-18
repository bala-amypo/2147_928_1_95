package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.BudgetPlan;
public interface BudgetPlanService {
BudgetPlan insert(BudgetPlan b);
List<BudgetPlan> getAll();
Optional<BudgetPlan> getOne(Long id);
void delete(Long id);
}