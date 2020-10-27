package com.main;

import java.io.Serializable;

public class SpecialAccount extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    public SpecialAccount(String name) {
        super(name);
    }

    public void transfer(Account accountTo, int amount) {
        if(accountTo.getAccountNumber() == this.getAccountNumber() || this.getMoney() < amount) return;
        this.store(-amount);
        accountTo.store(amount);
        this.transactions.add(new Transaction("Transference", amount, this.getAccountNumber(), accountTo.getAccountNumber()));
    }
}
