package com.example.demo.controller;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.TransactionLog;
import com.example.demo.service.TransactionLogService;

@RestController
@RequestMapping("/transactions")
public class TransactionLogController {

    private final TransactionLogService transactionLogService;

    public TransactionLogController(TransactionLogService transactionLogService) {
        this.transactionLogService = transactionLogService;
    }

    @PostMapping("/{userId}")
    public TransactionLog addTransaction(
            @PathVariable Long userId,
            @RequestBody TransactionLog log) {

        return transactionLogService.addTransaction(userId, log);
    }

    @GetMapping("/{userId}")
    public List<TransactionLog> getUserTransactions(
            @PathVariable Long userId) {

        return transactionLogService.getUserTransactions(userId);
    }
}
