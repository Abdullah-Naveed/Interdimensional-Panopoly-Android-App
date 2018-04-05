package com.rob.monopoly.Interfaces;


import com.rob.monopoly.Property;

import java.util.ArrayList;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Playable extends Identifiable{

    int getPosition();
    boolean isInJail();
    ArrayList<Property> getProperties();
    int getNumProperties();
    boolean isGroupOwner(Property property);
    String toString();
    int getBalance();
    void deposit(int amount);
    void withdraw(int amount);
    void addToProperties(Property property);
}

