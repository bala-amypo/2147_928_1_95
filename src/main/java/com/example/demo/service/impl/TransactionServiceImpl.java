package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Category;
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

   public TransactionServiceImpl(TransactionLogRepository transactionLogRepository,
                              UserRepository userRepository) {
    this.transactionLogRepository = transactionLogRepository;
    this.userRepository = userRepository;
    this.categoryRepository = null;
}


    @Override
    public TransactionLog addTransaction(Long userId, TransactionLog log) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        if (log.getCategory() == null || log.getCategory().getId() == null) {
            throw new BadRequestException("Category is required");
        }

        Category category = categoryRepository.findById(log.getCategory().getId())
                .orElseThrow(() -> new BadRequestException("Category not found"));

        log.setUser(user);
        log.setCategory(category);
        log.validate();

        return transactionLogRepository.save(log);
    }

    @Override
    public List<TransactionLog> getUserTransactions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return transactionLogRepository.findByUser(user);
    }
}
