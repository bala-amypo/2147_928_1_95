package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Long> {
    Optional<BudgetPlan> findByUserAndMonthAndYear(User user, Integer month, Integer year);
}