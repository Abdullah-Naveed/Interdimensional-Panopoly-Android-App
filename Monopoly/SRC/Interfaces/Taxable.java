package com.rob.monopoly.Interfaces;

/**
 * Created by AbdullahNaveed on 30/03/2018.
 */

public interface Taxable extends Ownable {

    int getTaxAmount();
    void payTax();

}
