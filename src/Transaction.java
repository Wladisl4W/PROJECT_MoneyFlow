public class Transaction {
    private final String description;
    private final int amount;
    private final TransactionType type;
    private final Category category;

    public Transaction(String description, int amount, TransactionType type, Category category) {
        this.description = description;
        this.amount = Math.abs(amount);
        this.type = type;
        this.category = category;
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

    //toString

    @Override
    public String toString() {
        return "Transaction{" +
                "description:" + description +
                ", amount:" + amount +
                ", type:" + type +
                ", category:" + category +
                "}";
    }
}
