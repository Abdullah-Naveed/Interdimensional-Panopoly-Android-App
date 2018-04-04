package com.rob.monopoly.Interfaces;

import com.rob.monopoly.Player;

/**
 * Created by AbdullahNaveed on 30/03/2018.
 */

public interface Ownable extends Identifiable {

    Player getOwner();
    void buyProperty(Player player);

}
