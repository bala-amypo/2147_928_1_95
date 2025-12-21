package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.TransactionLog;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionLogService;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TransactionLogServiceImpl(
            TransactionLogRepository transactionLogRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository) {
        this.transactionLogRepository = transactionLogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TransactionLog addTransaction(Long userId, TransactionLog log) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User with ID " + userId + " not found"));

        Long categoryId = log.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BadRequestException("Category with ID " + categoryId + " not found"));

        log.setUser(user);
        log.setCategory(category);

        return transactionLogRepository.save(log);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User with ID " + userId + " not found"));
        return transactionLogRepository.findByUser(user);
    }
}
