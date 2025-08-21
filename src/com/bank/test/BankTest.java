package com.bank.test;

import com.bank.Bank;
import com.bank.BankAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void open_get_close_account_flow() {
        Bank bank = new Bank();
        BankAccount a = bank.openAccount("A1", 100);
        assertEquals(1, bank.totalAccounts());
        assertSame(a, bank.getAccount("A1"));
        bank.closeAccount("A1");
        assertEquals(0, bank.totalAccounts());
        assertThrows(IllegalArgumentException.class, () -> bank.getAccount("A1"));
    }

    @Test
    void openAccount_rejectsDuplicateId() {
        Bank bank = new Bank();
        bank.openAccount("A1", 50);
        assertThrows(IllegalArgumentException.class, () -> bank.openAccount("A1", 10));
    }

    @Test
    void transfer_movesMoneyBetweenAccounts() {
        Bank bank = new Bank();
        bank.openAccount("A1", 100);
        bank.openAccount("B1", 20);
        bank.transfer("A1", "B1", 30);
        assertEquals(70, bank.getAccount("A1").getBalance(), 0.0001);
        assertEquals(50, bank.getAccount("B1").getBalance(), 0.0001);
    }

    @Test
    void transfer_rejectsIfInsufficientFunds_orInvalidAmount() {
        Bank bank = new Bank();
        bank.openAccount("A1", 10);
        bank.openAccount("B1", 10);

        // insufficient funds
        assertThrows(IllegalArgumentException.class, () -> bank.transfer("A1", "B1", 50));

        // invalid amounts
        assertThrows(IllegalArgumentException.class, () -> bank.transfer("A1", "B1", 0));
        assertThrows(IllegalArgumentException.class, () -> bank.transfer("A1", "B1", -5));

        // balances unchanged
        assertEquals(10, bank.getAccount("A1").getBalance(), 0.0001);
        assertEquals(10, bank.getAccount("B1").getBalance(), 0.0001);
    }
}
