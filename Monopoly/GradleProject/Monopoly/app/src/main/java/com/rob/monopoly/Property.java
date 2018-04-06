package com.rob.monopoly;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rob.monopoly.Interfaces.Improvable;
import com.rob.monopoly.Interfaces.Mortgageable;
import com.rob.monopoly.Interfaces.Ownable;
import com.rob.monopoly.Interfaces.Rentable;

import java.util.ArrayList;

import static com.rob.monopoly.MainActivity.properties;

public class Property extends AppCompatActivity implements Improvable,Rentable,Mortgageable,Ownable {

    private String ID;
    private int numHouses=0;
    private int housePriceModifier;
    private Player player;
    private int mortgageAmount;
    private boolean isMortgaged=false;
    private boolean isOwned=false;
    private int buyPrice;
    private String colourGroup;

    public Property(String ID,String colourGroup,int buyPrice,int mortgageAmount,int housePriceModifier)
    {
        this.ID=ID;
        this.colourGroup=colourGroup;
        this.buyPrice=buyPrice;
        this.mortgageAmount=mortgageAmount;
        this.housePriceModifier=housePriceModifier;
        properties.add(this);
    }

    @Override
    public boolean buildHouse() {
        if(ownsColourGroup())
        {
            numHouses++;
            return true;
        }
        return false;
    }

    @Override
    public boolean demolishHouse() {
        if(ownsColourGroup())
        {
            numHouses--;
            return true;
        }
        return false;
    }


    @Override
    public int getHousePrice() {
        if(numHouses==0)
        {
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
            if(property.colourGroup==colourGroup)
            {
                i++;
            }
        }

        if(colourGroup=="yellow"&&i==2||colourGroup=="orange"&&i==2)
        {
            return true;
        }
        else if(colourGroup=="brown"&&i==3||colourGroup=="grey"&&i==3||colourGroup=="green"&&i==3||
                colourGroup=="red"&&i==3||colourGroup=="pink"&&i==3||colourGroup=="blue"&&i==3)
        {
            return true;
        }


        return false;
    }


    @Override
    public int getMortgageAmount() {
        return mortgageAmount;
    }


    @Override
    public void mortgageProperty() {
        player.deposit(mortgageAmount);
        isMortgaged=true;
        isOwned=false;
    }


    @Override
    public void payRent(Player player) {
        player.withdraw(getRentalAmount());
    }

    @Override
    public int getRentalAmount(){
        return getHousePrice();
    }

    @Override
    public boolean isOwned() {
        return isOwned;
    }

    @Override
    public boolean isMortgaged() {
        return isMortgaged;
    }

    @Override
    public Player getOwner() {
        return player;
    }

    @Override
    public void buyProperty(Player player) {
        this.player=player;
        this.player.addToProperties(this);
        this.player.withdraw(buyPrice);
        isOwned=true;
        isMortgaged=false;

    }

    @Override
    public int buyPrice() {
        return buyPrice;
    }

    @Override
    public String getID() {
        return this.ID;
    }


}
