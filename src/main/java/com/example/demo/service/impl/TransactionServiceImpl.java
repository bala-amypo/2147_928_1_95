package com.example.demo.service.impl;

import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionLogRepository transactionLogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public TransactionServiceImpl(TransactionLogRepository transactionLogRepository,
                                  UserRepository userRepository,
                                  CategoryRepository categoryRepository) {
        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
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
        transactionLog.validate();

        return transactionLogRepository.save(transactionLog);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        List<TransactionLog> logs = transactionLogRepository.findByUser_Id(userId);
        if (logs != null && !logs.isEmpty()) {
            return logs;
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return List.of();
        }

        return transactionLogRepository.findByUser(user);
    }
}
