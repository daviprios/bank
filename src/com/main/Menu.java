package com.main;
import java.util.Scanner;
import java.util.Arrays;

public class Menu {
    //VARIABLES
    Account[] accounts = new Account[]{
            new Account(0),
            new Account(1),
            new Account(2),
            new Account(3),
            new Account(4)};
    int accountIndex;
    String accountsNumberString = "";
    Scanner input = new Scanner(System.in);

    //METHODS
    public void mainMenu(){
        for(Account account : accounts) accountsNumberString += account.getAccountNumber() + " ";
        int choose;
        do{
            choose = 1;
            System.out.println("JAVA-BANK\nBank accounts: " + accountsNumberString + "\n\nEnter your account number:");
            choose = accountMenu(input.nextInt());
        }while (choose == 0);
    }

    private int accountMenu(int accountNumber){
        int choose = 0;
        if(!verifyIndex(accountNumber)) return 0;
        else accountIndex = accountNumber;
        while(true){
            switch (choose) {
                case 1:
                    accounts[accountIndex].deposit(input);
                    break;
                case 2:
                    int accountTo;
                    do {
                        System.out.println("Account to transfer:\n" + accountsNumberString);
                        accountTo = input.nextInt();
                    }while (!verifyIndex(accountTo));
                    accounts[accountIndex].transfer(accounts[accountTo], input);
                    break;
                case 4:
                    return 0;
                case 5:
                    return 1;
            }
            System.out.println("\nAccount " + accountNumber + "\nMoney:" + accounts[accountIndex].getMoney() + "\n\nChoose your action:\n1 - Deposit\n2 - Transfer\n\n4 - Change Account\n5 - Exit");
            choose = input.nextInt();
        }
    }

    private boolean verifyIndex(int accountNumber){
        for(Account account : accounts)
            if(account.getAccountNumber() == accountNumber)
                return true;
        return false;
    }
}
