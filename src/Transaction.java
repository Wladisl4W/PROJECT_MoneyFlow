public class Transaction {
    private final String description;
    private final int amount;

    public Transaction(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }



    //getters

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }



    //toString

    @Override
    public String toString() {
        return "Transaction{" +
                "description:" + description +
                ", amount:" + amount +
                "}";
    }
}
