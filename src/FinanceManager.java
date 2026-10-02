import model.Category;
import model.Transaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FinanceManager {
    private final List<Transaction> transactions = new ArrayList<>();








    //sorts

    public List<Transaction> sortTransactions(Comparator<Transaction> comparator) {
        List<Transaction> sorted = new ArrayList<>(transactions);
        sorted.sort(comparator);
        return sorted;
    }


    //prints

    public void printTransactions() {
        transactions.forEach(System.out::println);
    }

    public void printTransactions(TransactionType type) {
        for (Transaction transaction : transactions) {
            if (transaction.getType() == type) {
                System.out.println(transaction);
            }
        }
    }

    public void printSortedTransactions(Comparator<Transaction> comparator) {
        for (Transaction t : this.sortTransactions(comparator)) {
            System.out.println(t);
        }
    }




    //getters

    public int getTransactionCount() {
        return transactions.size();
    }

    public BigDecimal getBalance() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (TransactionType.INCOME == transaction.getType()) {
                sum = sum.add(transaction.getAmount());
            } else {
                sum = sum.subtract(transaction.getAmount());
            }
        }
        return sum;
    }


    public List<String> getTransactionsDescription() {
        return transactions.stream().map(Transaction::getDescription).toList();
    }

    public List<String> getTransactionsDescriptionExpense() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .map(Transaction::getDescription)
                .toList();
    }

    public List<BigDecimal> getTransactionsAmount() {
        return transactions.stream().map(Transaction::getAmount).toList();
    }

















    public BigDecimal getSum() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
