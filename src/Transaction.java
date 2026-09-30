import java.time.LocalDate;

public class Transaction {
    private final String description;
    private final int amount;
    private final TransactionType type;
    private final Category category;
    private final LocalDate date;

    public Transaction(String description, int amount, TransactionType type, Category category, LocalDate date) {
        this.description = description;
        this.amount = Math.abs(amount);
        this.type = type;
        this.category = category;
        this.date = date;
    }



    //getters

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    //toString

    @Override
    public String toString() {
        return "Transaction{" +
                "description:" + description +
                ", amount:" + amount +
                ", type:" + type +
                ", category:" + category +
                ", date:" + date +
                "}";
    }
}
