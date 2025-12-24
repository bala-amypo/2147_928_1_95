package com.example.demo.service.impl;

import com.example.demo.model.TransactionLog;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionLogRepository transactionLogRepository;
    private final UserRepository userRepository;

    // ✅ CONSTRUCTOR EXPECTED BY TESTS
    public TransactionServiceImpl(TransactionLogRepository transactionLogRepository,
                                  UserRepository userRepository) {
        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
    }

    // ✅ CONTROLLER METHOD
    @Override
    public TransactionLog addTransaction(Long userId, TransactionLog transactionLog) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        transactionLog.setUser(user);
        return transactionLogRepository.save(transactionLog);
    }

    // ✅ TEST METHOD
    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        return transactionLogRepository.findByUser_Id(userId);
    }
}
