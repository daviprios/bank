package com.main;
import java.util.Scanner;

public class Account {
    //CONSTRUCTOR
    public Account(int accountNumber, boolean specialAccount){
        this.accountNumber = accountNumber;
        this.specialAccount = specialAccount;
        money = 10000;
    }

    //VARIABLE
    private final int accountNumber;
    private float money;
    private final boolean specialAccount;

    //METHODS
    public int getAccountNumber() {
        return accountNumber;
    }

    public float getMoney(){
        return money;
    }

    public void deposit(Scanner input){
        System.out.println("How much to deposit: ");
        int amount = input.nextInt();
        money += amount;
        System.out.println("\nDeposited\n");
    }

    public void transferEnd(int amount){
        money += amount;
    }

    public void transfer(Account accountTo, Scanner input){
        System.out.println("How much to transfer:");
        int amount = input.nextInt();
        if(money < amount || specialAccount && money < amount + amount * 0.05) {
            System.out.println("NOT ENOUGH MONEY");
            return;
        }
        this.money -= amount;
        if(!specialAccount) this.money -= amount * 0.05;
        accountTo.transferEnd(amount);
        System.out.println("\nTransferred\n");
    }

    public boolean getSpecialAccount(){
        return specialAccount;
    }
}
