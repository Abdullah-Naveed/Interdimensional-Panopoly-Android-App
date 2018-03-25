package com.rob.monopoly;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Mortgageable extends Ownable{

   int getMortgageAmount();
   int getUnmortgageAmount();

   void mortgageProperty();
   void unmortgageProperty();
}