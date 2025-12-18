package com.example.demo.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BudgetSummary;
@Service
public class BudgetSummaryServiceImpl implements BudgetSummaryService {
Map<Long, BudgetSummary> data = new HashMap<>();
@Override
public BudgetSummary insert(BudgetSummary s) {
data.put(s.getId(), s);
return s;
}
@Override
public List<BudgetSummary> getAll() {
return new ArrayList<>(data.values());
}
@Override
public Optional<BudgetSummary> getOne(Long id) {
return Optional.ofNullable(data.get(id));
}
@Override
public void delete(Long id) {
data.remove(id);
}
}