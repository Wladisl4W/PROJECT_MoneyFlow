package repository;

import model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);

    void removeTransaction(Transaction transaction);

    List<Transaction> findAll();

    Optional<Transaction> findById(long id);
}
