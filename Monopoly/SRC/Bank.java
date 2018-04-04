package com.rob.monopoly;

import com.rob.monopoly.Interfaces.Bankable;

/**
 * Created by AbdullahNaveed on 04/04/2018.
 */

public class Bank implements Bankable{

    protected int balance=0;

    /**
     Constructs a bank account with a given balance.
     @param initialBalance the initial balance
     */
    public Bank(int initialBalance){
        balance = initialBalance;
    }

    /**
     Deposits money into the bank account.
     @param amount the amount to deposit
     */
    public void deposit(int amount) {
        balance+= amount;
    }

    /**
     Withdraws money from the bank account.
     @param amount the amount to withdraw
     */
    public void withdraw(int amount) {
        balance-= amount;
    }

    /**
     Gets the current balance of the bank account.
     @return the current balance
     */
    public int getBalance(){
        return balance;
    }


}
