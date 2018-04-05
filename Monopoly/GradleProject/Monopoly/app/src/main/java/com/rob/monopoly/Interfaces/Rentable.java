package com.rob.monopoly.Interfaces;

import com.rob.monopoly.Player;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Rentable extends Ownable{

    void payRent(Player player);
    int getRentalAmount();
    boolean isOwned();
    boolean isMortgaged();
}