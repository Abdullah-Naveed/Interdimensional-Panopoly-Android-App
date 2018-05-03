package com.rob.monopoly.Interfaces;

/**
 * Created by ErikBurka on 22/03/2018.
 */

public interface Taxable {

    int getHouseTaxAmount();
    int getPropertiesTaxAmount();
    int getBalanceTax();
    int getRandomTax();
    String payHouseTax();
    String payPropertyTax();
    String payBalanceTax();
    String payRandomTax();
    
}
