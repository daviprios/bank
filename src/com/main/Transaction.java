package com.main;

import java.util.Date;

public class Transaction {
    private int senderAccountNumber = 0, receiverAccountNumber = 0;
    long time;
    float tax = 0, value;
    String type;

    Transaction(String type, int value, float tax, int sender, int receiver){
        this.type = type;
        this.tax = tax;
        this.value = value * tax;
        this.senderAccountNumber = sender;
        this.receiverAccountNumber = receiver;
        this.time = new Date().getTime();
    }

    Transaction(String type, int value, int sender, int receiver){
        this.type = type;
        this.value = value;
        this.senderAccountNumber = sender;
        this.receiverAccountNumber = receiver;
        this.time = new Date().getTime();
    }

    Transaction(String type, int value){
        this.type = type;
        this.value = value;
        this.time = new Date().getTime();
    }

    public String getTransactionText(){
        String text = "";
        text += "Transaction type: " + type;
        text += "\nDate: " + new Date(time).toString();
        if(type.equals("Transference")){
            if(tax != 0){
                text += "\nTax: " + tax;
            }
            text += "\nSender: " + senderAccountNumber;
            text += "\nReceiver: " + receiverAccountNumber;
        }
        text += "\nValue: " + value;
        return text;
    }
}
