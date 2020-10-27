package com.main;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String userName;
    private final int accountNumber;
    private static int accountNumberGenerator = 100;
    private float money;
    protected LinkedList<Transaction> transactions = new LinkedList<>();

    public Account(String name) {
        this.accountNumber = accountNumberGenerator++;
        this.userName = name;
        this.money = 0;
    }

    public static void setInitialAccountNumber(int initialAccountNumber){
        Account.accountNumberGenerator = initialAccountNumber;
    }

    public static int getAccountNumberGenerator() {
        return accountNumberGenerator;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public float getMoney() {
        return money;
    }

    protected void store(int amount){
        money += amount;
    }

    protected void store(float amount){
        money += amount;
    }

    public void deposit(int amount) {
        this.store(amount);
        this.transactions.add(new Transaction("Deposit", amount));
    }

    public abstract void transfer(Account accountTo, int amount);

    public LinkedList<String> getTransactions(){
        LinkedList<String> transactionsText = new LinkedList<>();
        for(Transaction transaction : transactions){
            transactionsText.add(transaction.getTransactionText());
        }
        return transactionsText;
    }

    public String getName(){
        return userName;
    }
}