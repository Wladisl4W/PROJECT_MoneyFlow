package service;

import model.Category;
import model.Transaction;
import model.TransactionType;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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



    // Checks

    public boolean isThereATransactionBiggerThan(BigDecimal amount) {
        return repository.findAll().stream()
                .anyMatch(t -> t.getAmount().compareTo(amount) > 0);
    }

    public boolean ifAllTransactionsHigherThan(BigDecimal amount) {
        return repository.findAll().stream()
                .allMatch(t -> t.getAmount().compareTo(amount) > 0);
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
        return repository
                .findAll()
                .stream()
                .filter(transactionPredicate)
                .toList();
    }

    public List<Transaction> getBiggestTransactions(int n) {
        return repository
                .findAll()
                .stream()
                .sorted(Comparator
                        .comparing(Transaction::getAmount)
                        .reversed())
                .limit(n)
                .toList();
    }

    public Optional<Transaction> getFirstTransactionOfCategory(Category category) {
        return repository
                .findAll()
                .stream()
                .filter(t -> t.getCategory() == category)
                .findFirst();
    }

    public long getExpenseCount() {
        return repository
                .findAll()
                .stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .count();
    }

    public BigDecimal getSumOfAllTransactions(TransactionType transactionType) {
        return repository
                .findAll()
                .stream()
                .filter(t -> t.getType() == transactionType)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAvgOfAllTransactions() {
        if (repository.findAll().isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = repository
                .findAll()
                .stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(
                BigDecimal.valueOf((repository.findAll().size())),
                2,
                RoundingMode.HALF_UP
        );
    }

    public Map<Category, List<Transaction>> getCategoryMap() {
        return repository
                .findAll()
                .stream()
                .collect(Collectors
                        .groupingBy(Transaction::getCategory));
    }

    public Map<Category, BigDecimal> getCategoryMapExpense() {
        return repository
                .findAll()
                .stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors
                        .groupingBy(Transaction::getCategory,
                                Collectors.reducing(
                                        BigDecimal.ZERO,
                                        Transaction::getAmount,
                                        BigDecimal::add
                                )));
    }

    public Map<Category, Long> getCategoryTransactionsCount() {
        return repository.findAll().stream()
                .collect(Collectors
                        .groupingBy(Transaction::getCategory,
                                Collectors.counting()));
    }
}
