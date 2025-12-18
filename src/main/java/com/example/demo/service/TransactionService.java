package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.TransactionLog;
import org.springframework.stereotype.Service;
@Service
public interface TransactionService {
TransactionLog insert(TransactionLog t);
List<TransactionLog> getAll();
Optional<TransactionLog> getOne(Long id);
void delete(Long id);
}