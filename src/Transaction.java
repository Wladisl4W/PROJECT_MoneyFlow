public class Transaction {
    private final String description;
    private final int amount;
    private final TransactionType type;

    public Transaction(String description, int amount, TransactionType type) {
        this.description = description;
        this.amount = Math.abs(amount);
        this.type = type;
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

    //toString

    @Override
    public String toString() {
        return "Transaction{" +
                "description:" + description +
                ", amount:" + amount +
                ", type:" + type +
                "}";
    }
}
