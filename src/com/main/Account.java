package com.main;

public abstract class Account {

    private final int accountNumber;
    protected float money;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        money = 10000;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public float getMoney() {
        return money;
    }

    public void deposit(int amount) {
        money += amount;
    }

    public abstract void transfer(Account accountTo, int amount);
}