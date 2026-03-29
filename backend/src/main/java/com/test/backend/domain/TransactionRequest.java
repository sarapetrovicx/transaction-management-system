package com.test.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = false)
public class TransactionRequest {

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}$", message = "Account number must be in format: 1234-5678-9012")
    private String accountNumber;

    @NotBlank
    private String accountHolderName;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal amount;

    @NotNull
    private Status status;

    public TransactionRequest() {
    }

    public TransactionRequest(String accountNumber, String accountHolderName, BigDecimal amount, Status status) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.amount = amount;
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
