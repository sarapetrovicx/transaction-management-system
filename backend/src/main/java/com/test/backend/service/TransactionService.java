package com.test.backend.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.test.backend.domain.Status;
import com.test.backend.domain.Transaction;
import com.test.backend.domain.TransactionRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final String csvFile;

    public TransactionService(@Value("${transactions.file}") String csvFile) {
        this.csvFile = csvFile;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(csvFile);

        if (!file.exists()) {
            return transactions;
        }

        try (InputStream is = new FileInputStream(file);
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

    public void appendTransaction(TransactionRequest transaction) throws IOException {
        File file = new File(csvFile);
        boolean fileExists = file.exists();
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                String[] header = {"Transaction Date","Account Number","Account Holder Name","Amount","Status"};
                writer.writeNext(header);
            }
            String[] entry = {
                    LocalDate.now().toString(),
                    transaction.getAccountNumber(),
                    transaction.getAccountHolderName(),
                    transaction.getAmount().toString(),
                    transaction.getStatus().toString()
            };
            writer.writeNext(entry);
        }
    }
}