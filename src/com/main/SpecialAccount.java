package com.main;

public class SpecialAccount extends Account {
    public SpecialAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public void transfer(Account accountTo, int amount) {
        if(this.money < amount) { return; }
        this.money -= amount;
        accountTo.deposit(amount);
    }
}
