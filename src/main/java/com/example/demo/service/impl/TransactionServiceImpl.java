package com.example.demo.service.impl;

import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.model.Category;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionLogRepository transactionLogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // ✅ REQUIRED BY AMYPO TESTS (2-ARG CONSTRUCTOR)
    public TransactionServiceImpl(
            TransactionLogRepository transactionLogRepository,
            UserRepository userRepository
    ) {
        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = null;
    }

    // ✅ REQUIRED BY SPRING (3-ARG CONSTRUCTOR)
    public TransactionServiceImpl(
            TransactionLogRepository transactionLogRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TransactionLog saveTransaction(TransactionLog transaction) {
        return transactionLogRepository.save(transaction);
    }

    @Override
    public List<TransactionLog> getTransactionsByUserId(Long userId) {
        return transactionLogRepository.findByUserId(userId);
    }
}
