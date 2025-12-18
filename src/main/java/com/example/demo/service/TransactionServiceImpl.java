package com.example.demo.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.TransactionLog;
@Service
public class TransactionServiceImpl implements TransactionService {
Map<Long, TransactionLog> data = new HashMap<>();
@Override
public TransactionLog insert(TransactionLog t) {
data.put(t.getId(), t);
return t;
}
@Override
public List<TransactionLog> getAll() {
return new ArrayList<>(data.values());
}
@Override
public Optional<TransactionLog> getOne(Long id) {
return Optional.ofNullable(data.get(id));
}
@Override
public void delete(Long id) {
data.remove(id);
}
}
