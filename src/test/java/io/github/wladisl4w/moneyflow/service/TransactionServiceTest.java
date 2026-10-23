package io.github.wladisl4w.moneyflow.service;

import io.github.wladisl4w.moneyflow.repository.InMemoryTransactionRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionServiceTest {

    @Test
    void testAddition() {
        int a = 10;
        int b = 20;

        int result = a + b;

        assertEquals(30, result);
    }

    @Test
    void testEmptyBalance() {
        InMemoryTransactionRepository tr = new InMemoryTransactionRepository();
        TransactionService ts = new TransactionService(tr);

        assertEquals(0, BigDecimal.ZERO.compareTo(ts.getBalance()));
    }
}
