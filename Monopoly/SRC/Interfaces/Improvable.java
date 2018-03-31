package com.rob.monopoly;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Improvable extends Ownable {
    void buildHouse();
    void buildHotel();

    void demolishHouse();
    void demolishHotel();

    int getHousePrice();
    int getHotelPrice();

    int getNumHouses();
    int getNumHotels();
}
