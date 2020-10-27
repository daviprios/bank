package com.main;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    String accountsNumberString;
    Scanner input = new Scanner(System.in);
    public static ArrayList<Account> accounts = new ArrayList<>();

    public Menu(){
        ManageFiles.initialize();
        Account.setInitialAccountNumber(ManageFiles.loadConfig());
        accounts = ManageFiles.loadAccounts();
        updateAccountList();
    }

    public void mainMenu(){
        int choose;
        do{
            System.out.println("JAVA-BANK\n\nEnter your account number to access your account\nBank accounts: " + accountsNumberString + "\n\nOther options:\n1 - Create Account\n5 - Exit\n");
            choose = input.nextInt();
            if(choose == 5) break;
            else if(choose == 1) choose = createAccount();
            else choose = accountMenu(choose);
        }while (choose == 0);
        ManageFiles.saveAccounts(accounts);
        ManageFiles.saveConfig(Account.getAccountNumberGenerator());
    }

    private int createAccount() {
        System.out.println("\nCreate Account:\n");
        System.out.println("What is your name?:");
        String name = input.next();
        String isSpecialAccount = "";
        while(!isSpecialAccount.equals("false") && !isSpecialAccount.equals("true")){
            System.out.println("\nAre your account a Special Account?\ntrue - for yes\tfalse - for no:\n");
            isSpecialAccount = input.next();
        }
        if(isSpecialAccount.equals("true")) accounts.add(new SpecialAccount(name));
        else accounts.add(new CommonAccount(name));
        updateAccountList();
        System.out.println("\nAccount successfully created:\n\n");
        return 0;
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
                    accounts.get(accountNumber).deposit(amount);
                    break;
                case 2:
                    int accountTo;
                    do {
                        System.out.println("Account to transfer:\n" + accountsNumberString);
                        accountTo = input.nextInt();
                    }while (verifyIndex(accountTo));
                    accountTo = convertNumberToIndex(accountTo);
                    System.out.println("How much to transfer:\n");
                    amount = input.nextInt();
                    accounts.get(accountNumber).transfer(accounts.get(accountTo), amount);
                    break;
                case 3:
                    System.out.println("Transactions history:\n");
                    for(String text : accounts.get(accountNumber).getTransactions()){
                        System.out.println(text + "\n\n");
                    }
                    break;
                case 4:
                    return 0;
                case 5:
                    return 1;
            }
            System.out.println("\nAccount: " + accounts.get(accountNumber).getAccountNumber() + "\nName: " + accounts.get(accountNumber).getName() + "\nMoney:" + accounts.get(accountNumber).getMoney() + "\n\nChoose your action:\n1 - Deposit\n2 - Transfer\n3 - History\n4 - Change Account\n5 - Exit");
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
                return accounts.indexOf(account);
            }
        } return 0;
    }

    private void updateAccountList(){
        accountsNumberString = "";
        for(Account account : accounts) {
            if (account instanceof SpecialAccount) accountsNumberString += "S-";
            accountsNumberString += account.getAccountNumber() + " ";
        }
    }
}
