package com.rob.monopoly.Interfaces;

/**
 * Created by AbdullahNaveed on 04/04/2018.
 */

public interface Bankable {

    int getBalance();
    void deposit(int amount);
    void withdraw(int amount);

}
