package com.rob.monopoly.Interfaces;

/**
 * Created by AbdullahNaveed on 30/03/2018.
 */

public interface Mortgageable extends Ownable {

    int getMortgageAmount();
    void mortgageProperty();

}
