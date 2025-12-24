package com.example.demo.service;

import com.example.demo.model.TransactionLog;

import java.util.List;

public interface TransactionService {

    // ✅ REQUIRED BY CONTROLLER
    TransactionLog addTransaction(Long userId, TransactionLog transactionLog);

    // ✅ REQUIRED BY TESTS
    List<TransactionLog> getUserTransactions(Long userId);
}
