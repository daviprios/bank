package com.main;
import java.util.Scanner;

public class Account {
    //CONSTRUCTOR
    public Account(int accountNumber){
        this.accountNumber = accountNumber;
        money = 10000;
    }

    //VARIABLE
    private int accountNumber;
    private float money;

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
        if(money < amount) {
            System.out.println("NOT ENOUGH MONEY");
            return;
        }
        this.money -= amount;
        accountTo.transferEnd(amount);
        System.out.println("\nTransfered\n");
    }

    public Account getAccount(){
        return this;
    }
}
