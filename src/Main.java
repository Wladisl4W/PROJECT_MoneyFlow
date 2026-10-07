import exception.TransactionNotFoundException;
import model.Category;
import model.Transaction;
import model.TransactionType;
import repository.InMemoryTransactionRepository;
import repository.TransactionRepository;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        TransactionService transactionService = new TransactionService(transactionRepository);

        Transaction transaction1 =
                new Transaction(
                        1,
                        "Зарплата",
                        new BigDecimal("50000.00"),
                        TransactionType.INCOME,
                        Category.SALARY,
                        LocalDate.of(2026, 9, 1));
        Transaction transaction2 =
                new Transaction(
                        2,
                        "Молоко",
                        new BigDecimal("100.00"),
                        TransactionType.EXPENSE,
                        Category.FOOD,
                        LocalDate.of(2026, 9, 3));
        Transaction transaction3 =
                new Transaction(
                        3,
                        "Такси",
                        new BigDecimal("500.00"),
                        TransactionType.EXPENSE,
                        Category.TRANSPORT,
                        LocalDate.of(2026, 9, 2));

        transactionService.addTransaction(transaction1);
        transactionService.addTransaction(transaction2);
        transactionService.addTransaction(transaction3);

        System.out.println(transactionService.getBalance());


        try {
            Transaction test1 = transactionService.getTransactionById(2);
            System.out.println(test1);
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Transaction test2 = transactionService.getTransactionById(100);
            System.out.println(test2);
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}