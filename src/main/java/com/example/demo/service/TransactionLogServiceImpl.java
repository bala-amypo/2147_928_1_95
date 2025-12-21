package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.TransactionLog;
import com.example.demo.entity.User;
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

        // ✅ Attach managed User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Attach managed Category
        Long categoryId = log.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        log.setUser(user);
        log.setCategory(category);

        return transactionLogRepository.save(log);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionLogRepository.findByUser(user);
    }
}
