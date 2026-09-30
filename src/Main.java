import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();

        Transaction transaction1 = new Transaction("Зарплата", 50000, TransactionType.INCOME, Category.SALARY, LocalDate.of(2026, 9, 1));
        Transaction transaction2 = new Transaction("Молоко", 100, TransactionType.EXPENSE, Category.FOOD, LocalDate.of(2026, 9, 3));
        Transaction transaction3 = new Transaction("Такси", 500, TransactionType.EXPENSE, Category.TRANSPORT, LocalDate.of(2026, 9, 2));

        financeManager.addTransaction(transaction1);
        financeManager.addTransaction(transaction2);
        financeManager.addTransaction(transaction3);

        System.out.println(financeManager.getBalance());

        System.out.println();
        financeManager.printTransactions();

        System.out.println();
        financeManager.printTransactions(TransactionType.INCOME);



        Comparator<Transaction> byDescription =
                (t1, t2) -> String.CASE_INSENSITIVE_ORDER.compare(t1.getDescription(), t2.getDescription());

        Comparator<Transaction> byAmount =
                Comparator.comparingInt(Transaction::getAmount);

        Comparator<Transaction> byDate =
                Comparator.comparing(Transaction::getDate);

        Comparator<Transaction> byCategory =
                Comparator.comparing(Transaction::getCategory);

        System.out.println();
        financeManager.printSortedTransactions(byDescription);

        System.out.println();
        financeManager.printSortedTransactions(byAmount);

        System.out.println();
        financeManager.printSortedTransactions(byAmount.reversed());

        System.out.println();
        financeManager.printSortedTransactions(byDate);

        Predicate<Transaction> dateIsBefore =
                transaction -> transaction.getDate().isBefore(LocalDate.of(2026, 9, 2));
        System.out.println();
        financeManager.printFilteredTransactions(dateIsBefore);

        System.out.println();
        financeManager.printSortedTransactions(byCategory);

        Predicate<Transaction> isExpense =
                transaction -> transaction.getType() == TransactionType.EXPENSE;
        System.out.println();
        financeManager.printFilteredTransactions(isExpense);

        Predicate<Transaction> isMoreThan1000 =
                transaction -> transaction.getAmount() > 1000;
        System.out.println();
        financeManager.printFilteredTransactions(isMoreThan1000);

        Predicate<Transaction> isCategoryFood =
                transaction -> transaction.getCategory() == Category.FOOD;
        System.out.println();
        financeManager.printFilteredTransactions(isCategoryFood);
    }
}