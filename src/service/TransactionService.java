package service;

import model.Transaction;
import model.TransactionType;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void addTransaction(Transaction transaction) {
        repository.addTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        repository.removeTransaction(transaction);
    }

    public BigDecimal getBalance() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Transaction transaction : repository.findAll()) {
            if (TransactionType.INCOME == transaction.getType()) {
                sum = sum.add(transaction.getAmount());
            } else {
                sum = sum.subtract(transaction.getAmount());
            }
        }
        return sum;
    }
}
