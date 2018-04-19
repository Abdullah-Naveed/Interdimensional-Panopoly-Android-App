package com.rob.monopoly;

import java.util.Iterator;
import java.util.Random;

public class Tax implements Taxable{

	private int taxModifier1 = 10;
	private int taxModifier2 = 20;
	private int taxModifier3 = 30;
	
	private int houseModifier1 = 5;
	private int houseModifier2 = 10;
	private int houseModifier3 = 15;
	
	private int totalHouses=0;
	private int amountToPay=0;
	
	@Override
	public int getHouseTaxAmount() {
		Iterator<Property> iter = GameState.getInstance().getCurrentPlayer().getProperties().iterator();
		
		while(iter.hasNext()){
			totalHouses += iter.next().getNumHouses();
		}
		
		if(totalHouses<=5){
			amountToPay = totalHouses*houseModifier1;
		}else if(totalHouses>5 && totalHouses <=9){
			amountToPay = ((totalHouses-5)*houseModifier2)+(5*houseModifier1);
		}else{
			amountToPay = (5*houseModifier1)+(4*houseModifier2)+((totalHouses-9)*houseModifier3);
		}
		return amountToPay;
		// You are taxed on every house that you own.
	}

	@Override
	public int getPropertiesTaxAmount() {

		int numberOfProperties = GameState.getInstance().getCurrentPlayer().getNumProperties();
		
		if(numberOfProperties<=3){
			
			amountToPay = numberOfProperties*taxModifier1;
			
		}else if(numberOfProperties>3 && numberOfProperties<=6){
			
			amountToPay = (taxModifier1*3)+((numberOfProperties-3)*taxModifier2);
		}else{
			amountToPay = (taxModifier1*3)+(taxModifier2*3)+((numberOfProperties-6)*taxModifier3);
		}
		return amountToPay;
		// You are taxed on every property that you own
	}
	
	@Override
	public int getBalanceTax() {
		
		int playerBalance =  GameState.getInstance().getCurrentPlayer().getBalance();
		
		amountToPay = (playerBalance/12);
		return amountToPay;
		// You are taxed on the amount of income that you have
	}

	@Override
	public int getRandomTax() {
		
		Random randomNumber = new Random();
	    int i = randomNumber.nextInt(5)+2;
	    int totalTax =0;  
	    
	    totalTax = getBalanceTax()+getHouseTaxAmount()+getPropertiesTaxAmount();
	    amountToPay = (totalTax/i);
	    
		return amountToPay;
		// You are taxed a random percentage based on your overall possession.
	}
	
	@Override
	public void payHouseTax() {
		GameState.getInstance().getCurrentPlayer().withdraw(getPropertiesTaxAmount());
	}

	@Override
	public void payPropertyTax() {
		GameState.getInstance().getCurrentPlayer().withdraw(getHouseTaxAmount());
	}


	@Override
	public void payBalanceTax() {
		GameState.getInstance().getCurrentPlayer().withdraw(getBalanceTax());
		
	}

	@Override
	public void payRandomTax() {
		GameState.getInstance().getCurrentPlayer().withdraw(getRandomTax());
		
	}

}
