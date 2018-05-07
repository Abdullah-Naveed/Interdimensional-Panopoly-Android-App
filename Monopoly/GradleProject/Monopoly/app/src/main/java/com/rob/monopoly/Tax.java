package com.rob.monopoly;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import com.rob.monopoly.Interfaces.TaxFunctional;
import com.rob.monopoly.Interfaces.Taxable;
import com.rob.monopoly.NOCList.twitterbotics.KnowledgeBaseModule;

public class Tax implements Taxable{
	
	
	
	KnowledgeBaseModule NOC= GameState.getInstance().getKnowledgeBaseModule();
	
	String str = "";
	String pronoun = "he";
	String possPro = "his";

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
	@TaxFunctional
	public String payHouseTax() {
		
		GameState.getInstance().getCurrentPlayer().withdraw(getHouseTaxAmount());
		
		Vector<String> Detectives = NOC.getAllKeysWithFieldValue("Category", "Detective");
		String detective = NOC.selectRandomlyFrom(Detectives);
		
		Vector<String> DetectivesVehicleChoices = NOC.getFieldValues("Vehicle of Choice", detective);
		String detectivesVehicle = NOC.selectRandomlyFrom(DetectivesVehicleChoices);
		
		if(detectivesVehicle == null){
			detective = NOC.selectRandomlyFrom(Detectives);
			detectivesVehicle = NOC.selectRandomlyFrom(DetectivesVehicleChoices);
		}
		
		if (NOC.hasFieldValue("Gender", detective, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		if(getHouseTaxAmount()==0){
			str = "You're lucky that you have no houses built yet because " + detective + " would've made you pay a fortune for every single one of them.";
		}else{
		str = detective + " creeps around all of your houses in "+ possPro + " " + detectivesVehicle + " and uses " + possPro + " detective skills to find out you avoided tax on your houses. You now must pay " + getPropertiesTaxAmount() + " of the tax that you avoided.";
		}
		return str;
	}

	@Override
	@TaxFunctional
	public String payPropertyTax() {
		int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
		GameState.getInstance().getCurrentPlayer().withdraw(getPropertiesTaxAmount());
		
		Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
		String villain = NOC.selectRandomlyFrom(Villains);
		
		if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		if(i==0){
			str = "Aren't you lucky that you have no properties to pay tax on.";
		}else{
		str = "After a recent argument with your ex-bestfriend " + villain + ", " + pronoun + " stiches you up and reveals you've been avoiding tax on all " + i + " properties that you own. You must pay a fee for each one which adds up to "+ getHouseTaxAmount() + ".";
		}
		return str;
	}


	@Override
	@TaxFunctional
	public String payBalanceTax() {
		GameState.getInstance().getCurrentPlayer().withdraw(getBalanceTax());
		
		Vector<String> Heros = NOC.getAllKeysWithFieldValue("Category", "Hero");
		String hero = NOC.selectRandomlyFrom(Heros);
		
		Vector<String> HerosClothes = NOC.getFieldValues("Seen Wearing", hero);
		String herosClothing = NOC.selectRandomlyFrom(HerosClothes);
		
		if(herosClothing == null){
			hero = NOC.selectRandomlyFrom(Heros);
			herosClothing = NOC.selectRandomlyFrom(HerosClothes);
		}
		
		if (NOC.hasFieldValue("Gender", hero, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		
		str =hero + " who usually wears "+ possPro + " " + herosClothing + ", is disguised as a taxperson. You believe " + possPro + " scam and end up paying " + getBalanceTax() + " cash in hand.";
		return str;
		
	}

	@Override
	@TaxFunctional
	public String payRandomTax() {
		GameState.getInstance().getCurrentPlayer().withdraw(getRandomTax());
		
		Vector<String> Presidents = NOC.getAllKeysWithFieldValue("Category", "President");
		String president = NOC.selectRandomlyFrom(Presidents);
		
		if (NOC.hasFieldValue("Gender", president, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		
		str = president + " gets elected as the president for your fictional world. " + pronoun + " brings in a completely new random tax that you have to pay now. " + getRandomTax() + " Has been deducted from your balance.";
		return str;
	}

}
