package com.rob.monopoly.Interfaces;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Mortgageable extends Ownable{

   int getMortgageAmount();
   void mortgageProperty();
}