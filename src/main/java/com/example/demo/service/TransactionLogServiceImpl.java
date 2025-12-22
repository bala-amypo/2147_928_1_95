package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.entity.TransactionLog;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TransactionLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionLogService;

@Service
@Transactional
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

        // ✅ Validate request body
        if (log == null) {
            throw new BadRequestException("Transaction body cannot be null");
        }

        if (log.getCategory() == null || log.getCategory().getId() == null) {
            throw new BadRequestException("Category ID must be provided");
        }

        if (log.getAmount() == null || log.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }

        // ✅ Fetch managed User
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new BadRequestException("User with ID " + userId + " not found"));

        // ✅ Fetch managed Category
        Long categoryId = log.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new BadRequestException("Category with ID " + categoryId + " not found"));

        // ✅ Attach managed entities
        log.setUser(user);
        log.setCategory(category);
        if (log.getTransactionDate() == null) {
            log.setTransactionDate(LocalDate.now());
        }

        return transactionLogRepository.save(log);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionLog> getUserTransactions(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new BadRequestException("User with ID " + userId + " not found"));

        return transactionLogRepository.findByUserOrderByTransactionDateDesc(user);
    }
}
