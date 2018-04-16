package com.rob.monopoly;

import android.content.Context;
import android.view.ViewGroup;

import com.rob.monopoly.Interfaces.Playable;

import java.util.ArrayList;

import static com.rob.monopoly.MainActivity.players;


public class Player extends UserMover implements Playable{

    private int playerNum = 1;
    private int playerLocation = 0;
    private boolean playerWasRemoved = false;
    private ViewGroup viewGroup;
    private String ID;
    private Bank bankAccount=new Bank(1000);
    private ArrayList<Property> properties=new ArrayList<Property>();

    public Player(Context context,ViewGroup viewGroup, String ID)
    {
        super(context);
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

    public void removeFromProperties(Property property) {properties.remove(property);}

    public int getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(int playerLocation) {
        this.playerLocation = playerLocation;
    }

    public void move(int amountToMove)
    {
        playerLocation+=amountToMove;
        playerLocation = playerLocation %40;
        if(playerLocation==7||playerLocation==16||playerLocation==27||playerLocation==36)
        {
            playerLocation=move(viewGroup, playerLocation,playerNum);
        }
        else{
            move(viewGroup, playerLocation,playerNum);
        }

    }

    public boolean boolPlayerWasRemoved() {
        return playerWasRemoved;
    }

    public void setPlayerWasRemoved(boolean playerWasRemoved) {
        this.playerWasRemoved = playerWasRemoved;
    }

}
