package com.bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public BankAccount openAccount(String accountId, double initialBalance) {
        if (accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account already exists: " + accountId);
        }
        BankAccount acct = new BankAccount(accountId, initialBalance);
        accounts.put(accountId, acct);
        return acct;
    }

    public void closeAccount(String accountId) {
        if (accounts.remove(accountId) == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
    }

    public BankAccount getAccount(String accountId) {
        BankAccount acct = accounts.get(accountId);
        if (acct == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        return acct;
    }

    public void transfer(String fromId, String toId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        BankAccount from = getAccount(fromId); // throws if not found
        BankAccount to = getAccount(toId);     // throws if not found
        from.withdraw(amount);                 // throws if insufficient
        to.deposit(amount);
    }

    public int totalAccounts() {
        return accounts.size();
    }
}
