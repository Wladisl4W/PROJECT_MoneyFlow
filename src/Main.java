import model.Category;
import model.Transaction;
import model.TransactionType;
import repository.TransactionRepository;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);

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

        transactionService.addTransaction(transaction1);
        transactionService.addTransaction(transaction2);
        transactionService.addTransaction(transaction3);

        System.out.println(transactionService.getBalance());

    }
}