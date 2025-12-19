package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TransactionLog;
import com.example.demo.entity.User;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionLogService;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;
    private final UserRepository userRepository;

    public TransactionLogServiceImpl(
            TransactionLogRepository transactionLogRepository,
            UserRepository userRepository) {

        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TransactionLog addTransaction(Long userId, TransactionLog log) {
        User user = userRepository.findById(userId).orElse(null);
        log.setUser(user);
        return transactionLogRepository.save(log);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return transactionLogRepository.findByUser(user);
    }
}
