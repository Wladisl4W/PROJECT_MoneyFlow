package repository;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions= new ArrayList<>();

    public TransactionRepository(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public ArrayList<Transaction> findAll() {
        return new ArrayList<Transaction>(transactions);
    }
}
