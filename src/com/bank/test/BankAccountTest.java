package com.bank.test;

import com.bank.BankAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void constructor_setsIdAndInitialBalance() {
        BankAccount acct = new BankAccount("A1", 100.0);
        assertEquals("A1", acct.getAccountId());
        assertEquals(100.0, acct.getBalance(), 0.0001);
    }

    @Test
    void constructor_rejectsNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("X", -10));
    }

    @Test
    void deposit_increasesBalance() {
        BankAccount acct = new BankAccount("A1", 50.0);
        acct.deposit(25.0);
        assertEquals(75.0, acct.getBalance(), 0.0001);
    }

    @Test
    void deposit_rejectsZeroOrNegative() {
        BankAccount acct = new BankAccount("A1", 50.0);
        assertThrows(IllegalArgumentException.class, () -> acct.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> acct.deposit(-5));
    }

    @Test
    void withdraw_decreasesBalance() {
        BankAccount acct = new BankAccount("A1", 80.0);
        acct.withdraw(30.0);
        assertEquals(50.0, acct.getBalance(), 0.0001);
    }

    @Test
    void withdraw_rejectsZeroOrNegative() {
        BankAccount acct = new BankAccount("A1", 80.0);
        assertThrows(IllegalArgumentException.class, () -> acct.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> acct.withdraw(-10));
    }

    @Test
    void withdraw_blocksOverdraft() {
        BankAccount acct = new BankAccount("A1", 40.0);
        assertThrows(IllegalArgumentException.class, () -> acct.withdraw(50.0));
        assertEquals(40.0, acct.getBalance(), 0.0001);
    }
}
