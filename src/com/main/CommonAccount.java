package com.main;

public class CommonAccount extends Account{

    public CommonAccount(int accountNumber) {
        super(accountNumber);
    }

    public void transfer(Account accountTo, int amount) {
        float tax = 0.01f;
        if(this.money < amount + amount * tax) return;
        this.money -= amount + amount * tax;
        accountTo.deposit(amount);
    }
}
