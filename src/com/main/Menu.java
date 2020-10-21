package com.main;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    //CONSTRUCTOR
    public Menu(){
        for(Account account : accounts) {
            if (account instanceof SpecialAccount) accountsNumberString += "S-";
            accountsNumberString += account.getAccountNumber() + " ";
        }
    }

    //VARIABLES
    String accountsNumberString = "";
    Scanner input = new Scanner(System.in);
    public static Account[] accounts = {
        new SpecialAccount(111),
        new SpecialAccount(222),
        new CommonAccount(333),
        new CommonAccount(444),
        new CommonAccount(555)
    };

    //METHODS
    public void mainMenu(){
        int choose;
        do{
            System.out.println("JAVA-BANK\nBank accounts: " + accountsNumberString + "\n\nEnter your account number:");
            choose = accountMenu(input.nextInt());
        }while (choose == 0);
    }

    private int accountMenu(int accountNumber){
        int choose = 0, amount;

        if(verifyIndex(accountNumber)) return 0;
        else accountNumber = convertNumberToIndex(accountNumber);

        while(true){
            switch (choose) {
                case 1:
                    System.out.println("How much to deposit:\n");
                    amount = input.nextInt();
                    accounts[accountNumber].deposit(amount);
                    break;
                case 2:
                    int accountTo;
                    do {
                        System.out.println("Account to transfer:\n" + accountsNumberString);
                        accountTo = input.nextInt();
                    }while (verifyIndex(accountTo));
                    accountTo = convertNumberToIndex(accountTo);
                    System.out.println("How much to deposit:\n");
                    amount = input.nextInt();
                    accounts[accountNumber].transfer(accounts[accountTo], amount);
                    break;
                case 4:
                    return 0;
                case 5:
                    return 1;
            }
            System.out.println("\nAccount " + accounts[accountNumber].getAccountNumber() + "\nMoney:" + accounts[accountNumber].getMoney() + "\n\nChoose your action:\n1 - Deposit\n2 - Transfer\n\n4 - Change Account\n5 - Exit");
            choose = input.nextInt();
        }
    }

    private boolean verifyIndex(int accountNumber){
        for(Account account : accounts)
            if(account.getAccountNumber() == accountNumber)
                return false;
        return true;
    }

    private int convertNumberToIndex(int accountNumber){
        for(Account account : accounts){
            if(account.getAccountNumber() == accountNumber) {
                return Arrays.asList(accounts).indexOf(account);
            }
        } return 0;
    }
}
