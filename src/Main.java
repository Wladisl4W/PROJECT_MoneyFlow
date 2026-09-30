import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();

        Transaction t1 = new Transaction("Зарплата", 50000, TransactionType.INCOME, Category.SALARY);
        Transaction t2 = new Transaction("Молоко", 100, TransactionType.EXPENSE, Category.FOOD);
        Transaction t3 = new Transaction("Такси", 500, TransactionType.EXPENSE, Category.TRANSPORT);

        financeManager.addTransaction(t1);
        financeManager.addTransaction(t2);
        financeManager.addTransaction(t3);

        System.out.println(financeManager.getBalance());

        System.out.println();
        financeManager.printTransactions();

        System.out.println();
        financeManager.printTransactions(TransactionType.INCOME);



        Comparator<Transaction> byDescription = new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return String.CASE_INSENSITIVE_ORDER.compare(t1.getDescription(), t2.getDescription());
            }
        };

        Comparator<Transaction> byAmount = new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return Integer.compare(t1.getAmount(), t2.getAmount());
            }
        };

        System.out.println();
        financeManager.printSortedTransactions(byDescription);

        System.out.println();
        financeManager.printSortedTransactions(byAmount);

        System.out.println();
        financeManager.printSortedTransactions(byAmount.reversed());
    }
}