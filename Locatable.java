package com.rob.monopoly;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Locatable extends Identifiable{
    Locatable getLocation();
    Locatable goLeft();
    Locatable goRight();
}
