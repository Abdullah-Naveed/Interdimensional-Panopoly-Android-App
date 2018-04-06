package com.rob.monopoly;

import android.view.ViewGroup;

import com.rob.monopoly.Interfaces.Playable;

import java.util.ArrayList;

import static com.rob.monopoly.MainActivity.players;


public class Player extends UserMover implements Playable{

    private int playerNum=1;
    private int playerLocation =0;
    private ViewGroup viewGroup;
    private String ID;
    private Bank bankAccount;
    private ArrayList<Property> properties=new ArrayList<Property>();

    public Player(ViewGroup viewGroup,String ID)
    {
        this.viewGroup=viewGroup;
        this.ID=ID;
        bankAccount=new Bank(2000);
        players.add(this);
    }

    @Override
    public String getID()
    {
        return this.ID;
    }

    @Override
    public int getPosition(){
        return playerLocation;
    }

    @Override
    public boolean isInJail()
    {
        return false;
    }

    @Override
    public ArrayList<Property> getProperties() {
        return properties;
    }

    @Override
    public int getNumProperties()
    {
        return properties.size();
    }

    @Override
    public boolean isGroupOwner(Property property) {
        return property.ownsColourGroup();
    }

    @Override
    public String toString()
    {
        return ID;
    }

    @Override
    public int getBalance()
    {
        return bankAccount.getBalance();
    }

    @Override
    public void deposit(int amount)
    {
        bankAccount.deposit(amount);
    }

    @Override
    public void withdraw(int amount)
    {
        bankAccount.withdraw(amount);
    }

    @Override
    public void addToProperties(Property property) {
        properties.add(property);
    }

    public void move()
    {
        playerLocation++;
        playerLocation = playerLocation %40;
        move(viewGroup, playerLocation,playerNum);
    }

}
