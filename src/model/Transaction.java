package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private final long id;
    private final String description;
    private final BigDecimal amount;
    private final TransactionType type;
    private final Category category;
    private final LocalDate date;

    public Transaction(long id, String description, BigDecimal amount, TransactionType type, Category category, LocalDate date) {
        if (id <= 0) {
            throw new IllegalArgumentException("id должен быть > 0!");
        } else {
            this.id = id;
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException();
        } else {
            this.description = description;
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        }

        this.type = Objects.requireNonNull(type, "Тип не может быть пустым!");

        this.category = Objects.requireNonNull(category);

        this.date = Objects.requireNonNull(date);
    }



    // Getters

    public long getId() { return id; }

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



    // ToString

    @Override
    public String toString() {
        return "model.Transaction{" +
                "id:" + id +
                ", description:" + description +
                ", amount:" + amount +
                ", type:" + type +
                ", category:" + category +
                ", date:" + date +
                "}";
    }
}
