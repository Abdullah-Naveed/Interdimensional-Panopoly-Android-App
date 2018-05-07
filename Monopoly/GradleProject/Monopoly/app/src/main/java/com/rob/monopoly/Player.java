package com.rob.monopoly;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;

import com.rob.monopoly.Interfaces.Playable;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

public class Player extends UserMover implements Playable{

    private int playerNum;
    private int playerLocation = 0;
    private boolean playerWasRemoved = false;
    private boolean isInJail = false;
    private boolean hasRolled = false;
    private ViewGroup viewGroup;
    private String ID;
    private Bank bankAccount;
    private ArrayList<Property> properties=new ArrayList<Property>();

    public Player(Context context,ViewGroup viewGroup, String ID,int playerNum)
    {
        super(context);
        this.viewGroup=viewGroup;
        this.ID=ID;
        this.playerNum=playerNum;
        bankAccount=new Bank(2000);
    }

    public int getPlayerNum() {
        return playerNum;
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
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        this.isInJail = inJail;
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
        int oldPlayerLocation=playerLocation;
        playerLocation+=amountToMove;
        playerLocation = playerLocation %40;
        if(oldPlayerLocation>playerLocation)
        {
            GameState.getInstance().getCurrentPlayer().deposit(200);
            TastyToast.makeText(GameState.getInstance().getContext(),"Gongrats You Survived Another Round. Here Is 200 Euro",TastyToast.LENGTH_LONG,TastyToast.SUCCESS).setGravity(Gravity.CENTER,0,0);
        }
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

    public boolean isHasRolled() {
        return hasRolled;
    }

    public void setHasRolled(boolean hasRolled) {
        this.hasRolled = hasRolled;
    }
}
