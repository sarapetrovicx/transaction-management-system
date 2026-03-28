package com.test.backend.service;

import com.test.backend.domain.Status;
import com.test.backend.domain.Transaction;
import com.test.backend.domain.TransactionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionServiceTest {

    @TempDir
    Path tempDir;
    TransactionService service;

    @BeforeEach
    void init(){
        Path testFile = tempDir.resolve("transactions.csv");
        service = new TransactionService(testFile.toString());
    }

    @Test
    void shouldAppendAndReadTransaction() throws IOException {
        TransactionRequest request = new TransactionRequest(
                "1234567890",
                "John Doe",
                new BigDecimal("150.75"),
                Status.Settled
        );

        service.appendTransaction(request);

        List<Transaction> transactions = service.getAllTransactions();

        assertThat(transactions).hasSize(1);

        Transaction t = transactions.getFirst();
        assertThat(t.getAccountNumber()).isEqualTo("1234567890");
        assertThat(t.getAccountHolderName()).isEqualTo("John Doe");
        assertThat(t.getAmount()).isEqualByComparingTo("150.75");
        assertThat(t.getStatus()).isEqualTo(Status.Settled);
    }

    @Test
    void shouldReturnEmptyListWhenFileDoesNotExist() {
        List<Transaction> transactions = service.getAllTransactions();

        assertThat(transactions).isEmpty();
    }

    @Test
    void shouldAppendMultipleTransactions() throws IOException {
        service.appendTransaction(new TransactionRequest(
                "1111111111", "Alice", new BigDecimal("100"), Status.Pending));

        service.appendTransaction(new TransactionRequest(
                "2222222222", "Bob", new BigDecimal("200"), Status.Settled));

        List<Transaction> transactions = service.getAllTransactions();

        assertThat(transactions).hasSize(2);
    }
}