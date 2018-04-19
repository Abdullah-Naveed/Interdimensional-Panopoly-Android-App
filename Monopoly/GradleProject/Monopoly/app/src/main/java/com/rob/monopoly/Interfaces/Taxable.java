package com.rob.monopoly.Interfaces;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Taxable {

    int getHouseTaxAmount();
    int getPropertiesTaxAmount();
    int getBalanceTax();
    int getRandomTax();
    void payHouseTax();
    void payPropertyTax();
    void payBalanceTax();
    void payRandomTax();
    
}
