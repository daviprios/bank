package com.main;

import java.io.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ManageFiles {
    private static final String path = "C:\\JAVAPROGRAMSAVEFILES\\";
    private static final String accountsPath = "C:\\JAVAPROGRAMSAVEFILES\\ACCOUNTS\\";
    private static final String transactionsPath = "C:\\JAVAPROGRAMSAVEFILES\\TRANSACTIONS\\";

    public static void initialize(){
        try {
            new File(path).mkdir();
            new File(accountsPath).mkdir();
            new File(transactionsPath).mkdir();
            new File(path + "config.config").createNewFile();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static ArrayList<Account> loadAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        File fileList = new File(accountsPath);
        if(fileList.list() == null) return new ArrayList<>();
        try {
            for (String name : fileList.list()) {
                ObjectInputStream file = new ObjectInputStream(new FileInputStream(accountsPath + name));
                accounts.add((Account) file.readObject());
                file.close();
            }
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return accounts;
    }

    public static void saveAccounts(List<Account> accounts) {
        try {
            for (Account account : accounts){
                File fileItem = new File(accountsPath + account.getAccountNumber());
                fileItem.delete();
                fileItem.createNewFile();
                ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(accountsPath + account.getAccountNumber()));
                file.writeObject(account);
                file.close();
                createTransactionsFile(account.getTransactions(), account.getAccountNumber());
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void createTransactionsFile(List<String> transactions, int accountNumber){
        try {
            File fileItem = new File(transactionsPath + accountNumber + ".txt");
            fileItem.delete();
            fileItem.createNewFile();
            FileWriter fileWriter = new FileWriter(transactionsPath + accountNumber + ".txt");
            for (String transaction : transactions) {
                if (fileItem.canWrite()) {
                    fileWriter.write(transaction);
                    fileWriter.write("\n\n");
                }
            }
            fileWriter.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static int loadConfig(){
        int lastAccountNumber = 100;
        try {
            File file = new File(path + "config.config");
            if(file.canWrite()) {
                FileReader fileReader = new FileReader(path + "config.config");
                lastAccountNumber = fileReader.read();
                fileReader.close();
                if(lastAccountNumber < 100) lastAccountNumber = 100;
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return lastAccountNumber;
    }

    public static void saveConfig(int lastAccountNumber){
        try {
            File file = new File(path + "config.config");
            if(file.canRead()) {
                FileWriter fileWriter = new FileWriter(path + "config.config");
                fileWriter.write(lastAccountNumber);
                fileWriter.close();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
