package repository;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<Transaction>(transactions);
    }
}
