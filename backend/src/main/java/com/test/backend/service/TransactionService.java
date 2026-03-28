package com.test.backend.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.test.backend.domain.Status;
import com.test.backend.domain.Transaction;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream("/transactions.csv");
             InputStreamReader isr = new InputStreamReader(is);
             CSVReader reader = new CSVReader(isr)) {
            String[] line;
            boolean isFirstLine = true;
            while ((line = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (line.length < 5) continue;
                Transaction t = new Transaction(
                        LocalDate.parse(line[0]),
                        line[1],
                        line[2],
                        new BigDecimal(line[3]),
                        Status.valueOf(line[4])
                );
                transactions.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public void appendTransaction(Transaction transaction) throws IOException {
    }
}