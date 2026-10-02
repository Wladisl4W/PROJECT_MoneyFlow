package service;

import model.Category;
import model.Transaction;
import model.TransactionType;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }



    // Basic

    public void addTransaction(Transaction transaction) {
        repository.addTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        repository.removeTransaction(transaction);
    }



    // Getters

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

    public List<Transaction> getFilteredTransactions(
            Predicate<Transaction> transactionPredicate) {
        return repository.findAll().stream().filter(transactionPredicate).toList();
    }

    public List<Transaction> getBiggestTransactions(int n) {
        return repository.findAll().stream().
                sorted(Comparator
                        .comparing(Transaction::getAmount)
                        .reversed())
                .limit(n)
                .toList();
    }

    public Optional<Transaction> getFirstTransactionOfCategory(Category category) {
        return repository.findAll().stream()
                .filter(t -> t.getCategory() == category)
                .findFirst();
    }
}
