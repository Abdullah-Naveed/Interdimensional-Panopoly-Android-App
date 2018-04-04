package com.rob.monopoly.Interfaces;

import com.rob.monopoly.Player;

/**
 * Created by AbdullahNaveed on 30/03/2018.
 */

public interface Rentable extends Ownable {

    void payRent(Player player);
    int getRentalAmount();
    boolean isOwned();
    boolean isMorgaged();

}
