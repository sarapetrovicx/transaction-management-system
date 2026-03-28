package com.test.backend.service;

import com.test.backend.domain.Transaction;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private static final String CSV_FILE = "transactions.csv";

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        return transactions;
    }

    public void appendTransaction(Transaction transaction) throws IOException {
    }
}