import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private final List<Transaction> transactions = new ArrayList<>();



    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(int id) {
        transactions.remove(id);
    }

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public int getBalance() {
        int sum = 0;
        for (Transaction transaction : transactions) {
            if (TransactionType.INCOME == transaction.getType()) {
                sum += transaction.getAmount();
            } else {
                sum -= transaction.getAmount();
            }
        }
        return sum;
    }

}
