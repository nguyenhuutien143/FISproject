package org.example.model;

import lombok.Data;

import java.util.Date;
@Data
public class Transaction {
    private TransactionType transactionType;
    private String bankAccount;
    private Double amount;
    private String message;
    private Date dateTime;

    public Transaction(TransactionType transactionType, String bankAccount, Double amount, String message,
                       Date dateTime) {
        this.transactionType = transactionType;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.message = message;
        this.dateTime = dateTime;
    }
    public Transaction(){

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", bankAccount='" + bankAccount + '\'' +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
