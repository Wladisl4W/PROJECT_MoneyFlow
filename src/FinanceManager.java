import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FinanceManager {
    private final List<Transaction> transactions = new ArrayList<>();


    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public boolean isThereATransactionBiggerThan(BigDecimal amount) {
        return transactions.stream()
                .anyMatch(t -> t.getAmount().compareTo(amount) > 0);
    }

    public boolean ifAllTransactionsHigherThan(BigDecimal amount) {
        return transactions.stream()
                .allMatch(t -> t.getAmount().compareTo(amount) > 0);
    }


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

    public void printFilteredTransactions(
            Predicate<Transaction> transactionPredicate) {
        for (Transaction t : this.getFilteredTransactions(transactionPredicate)) {
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
                sum = sum.add(transaction.getAmount());
            }
        }
        return sum;
    }

    public List<Transaction> getFilteredTransactions(
            Predicate<Transaction> transactionPredicate) {
        return transactions.stream().filter(transactionPredicate).toList();
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

    public long getExpenseCount() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .count();
    }

    public List<Transaction> getBiggestTransactions(int n) {
        return transactions.stream().
                sorted(Comparator
                        .comparing(Transaction::getAmount)
                        .reversed())
                .limit(n)
                .toList();
    }

    public Optional<Transaction> getFirstTransactionOfCategory(Category category) {
        return transactions.stream()
                .filter(t -> t.getCategory() == category)
                .findFirst();
    }

    public BigDecimal getSumOfAllTransactions(TransactionType transactionType) {
        return transactions.stream()
                .filter(t -> t.getType() == transactionType)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAvgSumOfAllTransactions() {
        if (transactions.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(
                BigDecimal.valueOf((transactions.size())),
                2,
                RoundingMode.HALF_UP
        );
    }

    public Map<Category, List<Transaction>> getCategoryMap() {
        return transactions.stream()
                .collect(Collectors
                        .groupingBy(Transaction::getCategory));
    }

    public Map<Category, BigDecimal> getCategoryExpense() {
        return transactions.stream()
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
        return transactions.stream()
                .collect(Collectors
                        .groupingBy(Transaction::getCategory,
                                Collectors.counting()));
    }

    public BigDecimal getSum() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
