package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TransactionLog;

public interface TransactionLogService {

    TransactionLog addTransaction(Long userId, TransactionLog log);

    List<TransactionLog> getUserTransactions(Long userId);
}
