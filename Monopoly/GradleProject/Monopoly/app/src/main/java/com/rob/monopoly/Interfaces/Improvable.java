package com.rob.monopoly.Interfaces;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Improvable extends Ownable {

    boolean buildHouse();
    boolean demolishHouse();
    int getHousePrice();
    int getNumHouses();
    String getColourGroup();
    boolean ownsColourGroup();
}
