package com.rob.monopoly;

/**
 * Created by AbdullahNaveed on 04/04/2018.
 */

import android.view.ViewGroup;

import com.rob.monopoly.Interfaces.Playable;

import java.util.ArrayList;

public class Player extends UserMover implements Playable {

    private int playerNum =1;
    private int playerLocation =0;
    private ViewGroup viewGroup=null;
    private String ID;
    private Bank bankAccount;
    private ArrayList<Property> properties = new ArrayList<Property>();

    public Player(ViewGroup viewGroup, String ID)
    {
        this.ID = ID;
        this.viewGroup=viewGroup;
        bankAccount = new Bank(2000);
    }

    public void move()
    {
        playerLocation++;
        playerLocation = playerLocation %40;
        move(viewGroup, playerLocation, playerNum);
    }

    @Override
    public String getID() {
        return this.ID;
    }

    public int getPosition(){
        return playerLocation;
    }

    @Override
    public boolean isInJail() {
        return false;
    }

    @Override
    public ArrayList<Property> getProperties() {
        return properties;
    }

    @Override
    public int getBalance() {
        return bankAccount.getBalance();
    }

    @Override
    public void deposit(int amount) {
        bankAccount.deposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        bankAccount.withdraw(amount);
    }

    @Override
    public void addToProperties(Property property) {
        properties.add(property);
    }

    @Override
    public int getNumProperties() {
        return properties.size();
    }

    @Override
    public boolean isGroupOwner(Property property) {
        return property.ownsColourGroup();
    }

    @Override
    public String toString() {
        return ID;
    }

}