package main.java.exception;

public class DuplicateTransactionException extends RuntimeException {
    public DuplicateTransactionException(long id) {
        super("Трансзакция с id " + id + " уже существует!");
    }
}
