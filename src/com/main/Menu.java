package com.main;
import java.util.Scanner;

public class Menu {
    //VARIABLES
    Account[] accounts = new Account[]{
            new Account(0, true),
            new Account(1, true),
            new Account(2, false),
            new Account(3, false),
            new Account(4, false)};
    int accountIndex;
    String accountsNumberString = "";
    Scanner input = new Scanner(System.in);

    //METHODS
    public void mainMenu(){
        for(Account account : accounts){
            if(account.getSpecialAccount()) accountsNumberString += "S-";
            accountsNumberString += account.getAccountNumber() + " ";
        }
        int choose;
        do{
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
                    }while (verifyIndex(accountTo));
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
                return false;
        return true;
    }
}
