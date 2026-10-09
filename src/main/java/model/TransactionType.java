package main.java.model;

public enum TransactionType {
    INCOME("+"),
    EXPENSE("-");

    private final String symbol;

    TransactionType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
