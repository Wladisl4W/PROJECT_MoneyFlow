import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Transaction {
    private final String description;
    private final BigDecimal amount;
    private final TransactionType type;
    private final Category category;
    private final LocalDate date;

    public Transaction(String description, BigDecimal amount, TransactionType type, Category category, LocalDate date) {
        this.description = description;
        this.amount = amount.abs().setScale(2, RoundingMode.HALF_UP);
        this.type = type;
        this.category = category;
        this.date = date;
    }



    //getters

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
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
