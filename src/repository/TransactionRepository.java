package repository;

import model.Transaction;

import java.util.List;

public interface TransactionRepository {
    public void addTransaction(Transaction transaction);

    public void removeTransaction(Transaction transaction);

    public List<Transaction> findAll();
}
