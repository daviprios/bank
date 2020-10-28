package com.main;

import java.io.Serializable;

public class CommonAccount extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private float tax = 0.01f;

    public CommonAccount(String name) {
        super(name);
    }

    public void transfer(Account accountTo, int amount) {
        if(accountTo.getAccountNumber() == this.getAccountNumber() || this.getMoney() < amount + amount * tax) return;
        this.store(-( amount + amount * tax));
        accountTo.store(amount);
        this.transactions.add(new Transaction("Transference", amount, tax, this.getAccountNumber(), accountTo.getAccountNumber()));
        accountTo.transactions.add(new Transaction("Transference", amount, tax, this.getAccountNumber(), accountTo.getAccountNumber()));
    }
}
