package com.test.backend.controller;

import com.test.backend.domain.Transaction;
import com.test.backend.domain.TransactionRequest;
import com.test.backend.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return service.getAllTransactions();
    }

    @PostMapping
    public String addTransaction(@Valid @RequestBody TransactionRequest transaction) {
        try {
            service.appendTransaction(transaction);
            return "Transaction added.";
        } catch (IOException e) {
            return "Failed to add transaction: " + e.getMessage();
        }
    }
}