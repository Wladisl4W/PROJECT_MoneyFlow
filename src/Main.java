public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();

        Transaction t1 = new Transaction("Зарплата", 50000, TransactionType.INCOME);
        Transaction t2 = new Transaction("Молоко", 100, TransactionType.EXPENSE);
        Transaction t3 = new Transaction("Хлеб", 50, TransactionType.EXPENSE);

        financeManager.addTransaction(t1);
        financeManager.addTransaction(t2);
        financeManager.addTransaction(t3);

        System.out.println(financeManager.getBalance());
        financeManager.printTransactions();
    }
}