package com.test.backend.controller;

import com.test.backend.domain.Transaction;
import com.test.backend.domain.TransactionRequest;
import com.test.backend.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Map<String, String>> addTransaction(@Valid @RequestBody TransactionRequest transaction) {
        try {
            service.appendTransaction(transaction);
            return ResponseEntity.ok(Map.of("message", "Transaction added."));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to add transaction: " + e.getMessage()));
        }
    }
}