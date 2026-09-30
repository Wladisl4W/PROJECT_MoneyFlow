import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FinanceManager {
    private final List<Transaction> transactions = new ArrayList<>();



    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }



    //sorts

    public List<Transaction> sortTransactions(Comparator<Transaction> comparator) {
        List<Transaction> sorted = new ArrayList<>(transactions);
        sorted.sort(comparator);
        return sorted;
    }




    //prints

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void printTransactions(TransactionType type) {
        for (Transaction transaction : transactions) {
            if (transaction.getType() == type) {
                System.out.println(transaction);
            }
        }
    }

    public void printSortedTransactions(Comparator<Transaction> comparator) {
        for(Transaction t : this.sortTransactions(comparator)) {
            System.out.println(t);
        }
    }

    public void printFilteredByDateTransactions(LocalDate date, int i) {
        for(Transaction t : this.getTransactionsByDate(date, i)) {
            System.out.println(t);
        }
    }



    //getters

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

    public List<Transaction> getTransactionsByDate(LocalDate date, int i) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            switch (i)
            {
                case -1:
                    if(transaction.getDate().isBefore(date)) {
                        filtered.add(transaction);
                    }
                    break;
                case 0:
                    if(transaction.getDate().isEqual(date)) {
                        filtered.add(transaction);
                    }
                    break;
                case 1:
                    if(transaction.getDate().isAfter(date)) {
                        filtered.add(transaction);
                    }
                    break;
            }

        }
        return filtered;
    }
}
