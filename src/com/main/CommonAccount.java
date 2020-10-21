package com.main;

public class CommonAccount extends Account{
    public CommonAccount(int accountNumber) {
        super(accountNumber);
    }

    private static final float tax = 0.01f;

    @Override
    public void transfer(Account accountTo, int amount) {
        if(this.money < amount + amount * tax) { return; }
        this.money -= amount + amount * tax;
        accountTo.deposit(amount);
    }
}
