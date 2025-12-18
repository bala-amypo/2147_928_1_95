package com.example.demo.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BudgetPlan;
@Service
public class BudgetPlanServiceImpl implements BudgetPlanService {
Map<Long, BudgetPlan> data = new HashMap<>();
@Override
public BudgetPlan insert(BudgetPlan b) {
data.put(b.getId(), b);
return b;
}
@Override
public List<BudgetPlan> getAll() {
return new ArrayList<>(data.values());
}
@Override
public Optional<BudgetPlan> getOne(Long id) {
return Optional.ofNullable(data.get(id));
}
@Override
public void delete(Long id) {
data.remove(id);
}
}