package com.example.demo.service.impl;

import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionLogRepository transactionLogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // REQUIRED BY TESTS
    public TransactionServiceImpl(TransactionLogRepository transactionLogRepository,
                                  UserRepository userRepository,
                                  CategoryRepository categoryRepository) {
        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // REQUIRED BY SPRING
    public TransactionServiceImpl(TransactionLogRepository transactionLogRepository,
                                  UserRepository userRepository) {
        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = null;
    }

    @Override
    public TransactionLog addTransaction(Long userId, TransactionLog transactionLog) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        transactionLog.setUser(user);
        transactionLog.validate(); // REQUIRED BY TESTS

        return transactionLogRepository.save(transactionLog);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        User user = new User(userId);
        return transactionLogRepository.findByUser(user);
    }
}
