package com.test.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = false)
public class TransactionRequest {

    @NotBlank
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
