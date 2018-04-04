package com.rob.monopoly;

import com.rob.monopoly.Interfaces.Improvable;
import com.rob.monopoly.Interfaces.Mortgageable;
import com.rob.monopoly.Interfaces.Ownable;
import com.rob.monopoly.Interfaces.Rentable;

import java.util.ArrayList;

public class Property implements Improvable, Rentable, Mortgageable, Ownable{

    private String ID;
    private int numHouses = 0;
    private int housePriceModifier;
    private Player player;
    private int mortgageAmount;
    private boolean isMortgaged = false;
    private boolean isOwned = false;
    private int buyPrice;
    private String colourGroup;

    public Property(String ID, int buyPrice, int housePriceModifier, int mortgageAmount, String colourGroup){
        this.ID = ID;
        this.housePriceModifier = housePriceModifier;
        this.mortgageAmount = mortgageAmount;
        this.buyPrice = buyPrice;
        this.colourGroup = colourGroup;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public boolean buildHouse() {
        if(ownsColourGroup()) {
            numHouses++;
            return true;
        }
        return false;
    }

    @Override
    public boolean demolishHouse() {
        if(ownsColourGroup()) {
            numHouses--;
            return true;
        }
        return false;
    }

    @Override
    public int getHousePrice() {
        if(numHouses==0){
            return housePriceModifier;
        }
        return numHouses*housePriceModifier;
    }

    @Override
    public int getNumHouses() {
        return numHouses;
    }

    @Override
    public String getColourGroup() {
        return colourGroup;
    }

    @Override
    public boolean ownsColourGroup() {

        ArrayList<Property> properties=player.getProperties();
        int i=0;
        for (Property property : properties) {
            if(property.colourGroup==colourGroup) {
                i++;
            }
        }

        if(colourGroup=="yellow"&&i==2||colourGroup=="orange"&&i==2) {
            return true;
        }
        else if(colourGroup=="brown"&&i==3||colourGroup=="grey"&&i==3||colourGroup=="green"&&i==3||
                colourGroup=="red"&&i==3||colourGroup=="pink"&&i==3||colourGroup=="blue"&&i==3) {
            return true;
        }

        return false;
    }

    @Override
    public Player getOwner() {
        return player;
    }

    @Override
    public void buyProperty(Player player) {
        this.player = player;
        this.player.addToProperties(this);
        this.player.withdraw(buyPrice);
        isOwned = true;
        isMortgaged = false;
    }

    @Override
    public void payRent(Player player) {
        player.withdraw(getRentalAmount());
    }

    @Override
    public int getRentalAmount() {
        return getHousePrice();
    }

    @Override
    public boolean isOwned() {
        return isOwned;
    }

    @Override
    public boolean isMorgaged() {
        return isMortgaged;
    }

    @Override
    public int getMortgageAmount() {
        return mortgageAmount;
    }

    @Override
    public void mortgageProperty() {
        player.deposit(mortgageAmount);
        isMortgaged = true;
        isOwned = false;
    }


}
