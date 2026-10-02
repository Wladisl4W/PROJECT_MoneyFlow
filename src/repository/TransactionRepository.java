package repository;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public List<Transaction> findAll() {
        return new ArrayList<Transaction>(transactions);
    }
}
