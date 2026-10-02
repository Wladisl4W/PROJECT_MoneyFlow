import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();

        Transaction transaction1 =
                new Transaction(
                        "Зарплата",
                        new BigDecimal("50000.00"),
                        TransactionType.INCOME,
                        Category.SALARY,
                        LocalDate.of(2026, 9, 1));
        Transaction transaction2 =
                new Transaction(
                        "Молоко",
                        new BigDecimal("100.00"),
                        TransactionType.EXPENSE,
                        Category.FOOD,
                        LocalDate.of(2026, 9, 3));
        Transaction transaction3 =
                new Transaction(
                        "Такси",
                        new BigDecimal("500.00"),
                        TransactionType.EXPENSE,
                        Category.TRANSPORT,
                        LocalDate.of(2026, 9, 2));

        financeManager.addTransaction(transaction1);
        financeManager.addTransaction(transaction2);
        financeManager.addTransaction(transaction3);

        System.out.println(financeManager.getBalance());

        System.out.println();
        financeManager.printTransactions();

        System.out.println();
        financeManager.printTransactions(TransactionType.INCOME);


        Comparator<Transaction> byDescription =
                (t1, t2) ->
                        String.CASE_INSENSITIVE_ORDER
                                .compare(t1.getDescription(), t2.getDescription());

        Comparator<Transaction> byAmount =
                Comparator.comparing(Transaction::getAmount);

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
                transaction -> transaction
                        .getDate()
                        .isBefore(LocalDate.of(2026, 9, 2));
        System.out.println();
        financeManager.printFilteredTransactions(dateIsBefore);

        System.out.println();
        financeManager.printSortedTransactions(byCategory);

        Predicate<Transaction> isExpense =
                transaction -> transaction
                        .getType() == TransactionType.EXPENSE;
        System.out.println();
        financeManager.printFilteredTransactions(isExpense);

        Predicate<Transaction> isMoreThan1000 =
                transaction -> transaction.getAmount().compareTo(new BigDecimal("1000.00")) > 0;
        System.out.println();
        financeManager.printFilteredTransactions(isMoreThan1000);

        Predicate<Transaction> isCategoryFood =
                transaction -> transaction
                        .getCategory() == Category.FOOD;
        System.out.println();
        financeManager.printFilteredTransactions(isCategoryFood);

        System.out.println();
        BigDecimal optionalDouble = financeManager.getAvgSumOfAllTransactions();
        System.out.println(optionalDouble);

        System.out.println();
        Map<Category, List<Transaction>> listMap = financeManager.getCategoryMap();
        System.out.println(listMap);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = transaction1.getDate().format(formatter);

        NumberFormat moneyFormat =
                NumberFormat.getCurrencyInstance(
                        Locale.forLanguageTag("ru-RU"));
        String money = transaction1.getType().getSymbol() + " " +
                moneyFormat.format(transaction1.getAmount());

        System.out.println(date + " | " +
                transaction1.getDescription() + " | " +
                transaction1.getCategory() + " | " +
                money);
    }
}