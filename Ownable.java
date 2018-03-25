package com.rob.monopoly;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Ownable extends Identifiable{

    Playable getOwner();
}
