package com.rob.monopoly.Interfaces;

public interface Bankable {

    int getBalance();
    void deposit(int amount);
    void withdraw(int amount);

}
