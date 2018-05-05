package com.rob.monopoly;

import com.rob.monopoly.Interfaces.Functional;
import com.rob.monopoly.NOCList.twitterbotics.KnowledgeBaseModule;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Card{

	public Card(){

	}

    KnowledgeBaseModule NOC=new KnowledgeBaseModule(GameState.getInstance().getContext());
	private String str = "";
	private String pronoun = "he";
	private String possPro = "his";
	
	@Functional
	public String dangerousWeapon(){
		
		Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
		String villain = NOC.selectRandomlyFrom(Villains);
		
		Vector<String> villainsWeaponChoice = NOC.getFieldValues("Weapon of Choice", villain);
		String villainsWeapon = NOC.selectRandomlyFrom(villainsWeaponChoice);
                
        if (NOC.hasFieldValue("Gender", villain, "female"))
    		{
    			pronoun = "she";
    			possPro = "her";
    		}
        GameState.getInstance().getCurrentPlayer().withdraw(100);
        str = "You and "+ villain + " were seen running around with "+ possPro +" " + villainsWeapon + ". You receive a 100 Euro fine for carrying a dangerous weapon.";
        return str;
	}
	
	@Functional
	public String hospital(){
		
		Vector<String> Killers = NOC.getAllKeysWithFieldValue("Category", "Killer");
		String killer = NOC.selectRandomlyFrom(Killers);
		
		if (NOC.hasFieldValue("Gender", killer, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		
		GameState.getInstance().getCurrentPlayer().withdraw(150);
		str ="You get into a massive fight with " + killer + " in which you break your leg. You have to pay 150 Euro to get a cast at the hospital.";
		return str;
	}
	
	@Functional
	public String birthday(){
		
		Vector<String> Billionaires = NOC.getAllKeysWithFieldValue("Category", "Billionaire");
		String billionaire = NOC.selectRandomlyFrom(Billionaires);

		if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		GameState.getInstance().getCurrentPlayer().deposit(200);
		str = "It's your birthday! " + billionaire + " sent you a card that had 200 Euro in it.";
		return str;
    }
	
	@Functional
    public String birthday2(){
		
		Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
		String villain = NOC.selectRandomlyFrom(Villains);
		
		Vector<String> villainsWeaponChoice = NOC.getFieldValues("Weapon of Choice", villain);
		String villainsWeapon = NOC.selectRandomlyFrom(villainsWeaponChoice);
    	
    	int i = GameState.getInstance().getNumPlayers();
    	int amount = i*50;
        GameState.getInstance().getCurrentPlayer().deposit(amount);
    	
    	if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}

        str = "You made friends with " + villain + " who threatened every player in the game with " + possPro + " " + villainsWeapon + " to make sure they give you a birthday present. You end up getting " + amount + " Euro overall from every player.";
        return str;
    }
    
	@Functional
    public String christmas(){
		
		Vector<String> Male = NOC.getAllKeysWithFieldValue("Gender", "male");
		String man = NOC.selectRandomlyFrom(Male);
		
		Vector<String> mansPositiveTraitChoice = NOC.getFieldValues("Positive Talking Points", man);
		String mansPositiveTrait = NOC.selectRandomlyFrom(mansPositiveTraitChoice);
		
    	int i = GameState.getInstance().getNumPlayers();
    	int amount = i*20;
    	GameState.getInstance().getCurrentPlayer().withdraw(amount);
    	
    	str = "Your lovely and " + mansPositiveTrait + " friend " + man + " reminded you it's time for Kris Kindle. The presents end up costing you " + amount + " Euro.";
    	return str;
    }
    
	@Functional
    public String christmas2(){
		
		Vector<String> fatPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "fat");
		String fatPerson = NOC.selectRandomlyFrom(fatPeople);
		
    	int i = GameState.getInstance().getNumPlayers();
    	int amount = i*30;
    	
    	GameState.getInstance().getCurrentPlayer().deposit(amount);
    	
    	str = "Although you've been unpopular this week on twitter for slating " + fatPerson + " for being fat, all the players have been thoughtful and sent you 30 Euro each for christmas";
    	return str;
    }
    
	@Functional
    public String wildNight(){

		Vector<String> Females = NOC.getAllKeysWithFieldValue("Gender", "female");
		String female = NOC.selectRandomlyFrom(Females);
		Vector<String> femalesClothingChoice = NOC.getFieldValues("Seen Wearing", female);
		String femalesClothing = NOC.selectRandomlyFrom(femalesClothingChoice);
		Vector<String> femalesPositiveTraitChoice = NOC.getFieldValues("Positive Talking Points", female);
		String femalesPositiveTrait = NOC.selectRandomlyFrom(femalesPositiveTraitChoice);
        
        if(femalesClothing == null){
    		femalesClothing = "Underwear";
    	}

        GameState.getInstance().getCurrentPlayer().withdraw(25);
		str = "The " + femalesPositiveTrait + " " + female + " left her " + femalesClothing +  " at your house from your wild night yesterday. you've to meet up to give it back to her. The taxi cost you 25 Euro." ;
		return str;
    }

	@Functional
  public String meeting(){
		
		Vector<String> Male = NOC.getAllKeysWithFieldValue("Gender", "male");
		String man = NOC.selectRandomlyFrom(Male);
		Vector<String> mansNegativeTraitChoice = NOC.getFieldValues("Negative Talking Points", man);
		String mansNegativePoints = NOC.selectRandomlyFrom(mansNegativeTraitChoice);
		Vector<String> mansActivityChoice = NOC.getFieldValues("Typical Activity", man);
		String menActivities = NOC.selectRandomlyFrom(mansActivityChoice);

        GameState.getInstance().getCurrentPlayer().withdraw(75);
        str = "You've a meeting with the "+ mansNegativePoints+ " " + man + " to talk about how " + pronoun +" loves " + menActivities + ". You buy a bottle of wine for the meeting costing you 75 Euro.";
        return str;
    }

	@Functional
    public String sportsCompetition(){
		
		Vector<String> Athletes = NOC.getAllKeysWithFieldValue("Category", "Athlete");
		String athlete = NOC.selectRandomlyFrom(Athletes);
		
		Vector<String> athletesNegativeChoices = NOC.getFieldValues("Negative Talking Points", athlete);
		String athletesNegativePoints = NOC.selectRandomlyFrom(athletesNegativeChoices);
		
    	if (NOC.hasFieldValue("Gender", athlete, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().deposit(125);
    	str = "You enter a sports competition in which you face the famous athlete " + athlete + ". You use the fact that " + pronoun + " is " + athletesNegativePoints + " to your advantage and manage to win the competition and a nice prize of 125 Euro.";
    	return str;
    }
	
	@Functional
  public String magic(){
		
		Vector<String> Magicians = NOC.getAllKeysWithFieldValue("Category", "Magician");
		String magician = NOC.selectRandomlyFrom(Magicians);
    	
    	if (NOC.hasFieldValue("Gender", magician, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().withdraw(150);

     str = magician + " uses " + possPro + " magic powers to make you goodlooking. You pay 150 for the service.";
     return str;
    }

	@Functional
    public String robbery(){
		
		Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
		String villain = NOC.selectRandomlyFrom(Villains);
		
    	GameState.getInstance().getCurrentPlayer().withdraw(100);
		
    	if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	str = "You get pickpocketed by " + villain + ". " + pronoun + " manages to steal 100 Euro from your pocket.";
    	return str;
    }

	@Functional
    public String carCrash(){
		
		Vector<String> Billionaires = NOC.getAllKeysWithFieldValue("Category", "Billionaire");
		String billionaire = NOC.selectRandomlyFrom(Billionaires);
		
		Vector<String> billionairesCarChoises = NOC.getFieldValues("Vehicle of Choice", billionaire);
		String billionairesCar = NOC.selectRandomlyFrom(billionairesCarChoises);
		
    	if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().withdraw(200);
    	str = "You end up crashing into " + billionaire + "'s " + billionairesCar + ". You have to pay for some of the damages made to the vehicle which costs you 200 Euro.";
    	return str;
    }

	@Functional
    public String stealing(){
		
		Vector<String> Billionaires = NOC.getAllKeysWithFieldValue("Category", "Billionaire");
		String billionaire = NOC.selectRandomlyFrom(Billionaires);
		
		Vector<String> billionairesCarChoises = NOC.getFieldValues("Vehicle of Choice", billionaire);
		String billionairesCar = NOC.selectRandomlyFrom(billionairesCarChoises);
		
		Vector<String> billionairesClothesChoices = NOC.getAllKeysWithFieldValue("Seen Wearing", billionaire);
    	String billionairesClothes = NOC.selectRandomlyFrom(billionairesClothesChoices);
		
    	if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().deposit(250);
    	str = "You end up stealing the " + billionairesClothes + " from a known billionaire named " + billionaire + ", which you end up selling on eBay for a whopping 250. Sure it's not like " + pronoun + " would care anyways since " + pronoun + " owns a " + billionairesCar + ".";
    	return str;
    }

	@Functional
	public String bowling(){
		
		Vector<String> Directors = NOC.getAllKeysWithFieldValue("Category", "Director");
		Vector<String> Actors = NOC.getAllKeysWithFieldValue("Category", "Actor");
		Vector<String> Athletes = NOC.getAllKeysWithFieldValue("Category", "Athlete");
		
		String director = NOC.selectRandomlyFrom(Directors);
		String actor = NOC.selectRandomlyFrom(Actors);
		String athlete = NOC.selectRandomlyFrom(Athletes);
		
		Vector<String> directorsPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", director);
		String directorsPositivePoint = NOC.selectRandomlyFrom(directorsPositiveTraitChoices);
		
		Vector<String> actorsPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", actor);
		String actorsPositivePoint = NOC.selectRandomlyFrom(actorsPositiveTraitChoices);
		
		Vector<String> athletesNegativeChoices = NOC.getFieldValues("Negative Talking Points", athlete);
		String athletesNegativePoints = NOC.selectRandomlyFrom(athletesNegativeChoices);
		
		GameState.getInstance().getCurrentPlayer().withdraw(40);
		str = "You go bowling with the " + directorsPositivePoint + " " + director + ", the " + actorsPositivePoint + " " + actor + " and " + athlete + ", who's known to be " + athletesNegativePoints + ". You pay for everyone's game which costs you 40." ;
		return str;
	}

	@Functional
	public String onlineShop(){
	   
		Vector<String> Female = NOC.getAllKeysWithFieldValue("Gender", "female");
		String female = NOC.selectRandomlyFrom(Female);
		
		Vector<String> femalesClothingChoice = NOC.getFieldValues("Seen Wearing", female);
		String femalesClothing = NOC.selectRandomlyFrom(femalesClothingChoice);
		
		if (NOC.hasFieldValue("Gender", female, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		
		GameState.getInstance().getCurrentPlayer().withdraw(50);
		str = "You kinda have a crush on " + female + ", so you buy " + possPro + " something similar to the " + femalesClothing + " " + pronoun + " always wears. It costs you 50 Euro.";
		return str;
	}

	@Functional
    public String propertyDamages(){
		Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
		String villain = NOC.selectRandomlyFrom(Villains);
		
		Vector<String> villainsNegativeTraitChoice = NOC.getFieldValues("Negative Talking Points", villain);
		String villainsNegativePoint = NOC.selectRandomlyFrom(villainsNegativeTraitChoice);
		
		int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
		if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
   
		if(i==0){
			 str = "Since you own no properties, the " + villainsNegativePoint + " " + villain + " could not make any damages to properties but " + pronoun +" did try to break into your car leaving you with damages of 50 Euro.";
			 GameState.getInstance().getCurrentPlayer().withdraw(50);
		}else{
			 str = "The " + villainsNegativePoint + " " + villain + " made damages to all "+ i + " properties that you own. You pay 25 for each property damaged.";
    	   
			 GameState.getInstance().getCurrentPlayer().withdraw(i*25);
		}
		 return str;
	}
    
	@Functional
    public String coffee(){
		
		Vector<String> Female = NOC.getAllKeysWithFieldValue("Gender", "female");
		String female = NOC.selectRandomlyFrom(Female);
		
    	GameState.getInstance().getCurrentPlayer().withdraw(25);
    	str = female + " asked you on a cute coffee date. You think that she might be the one, but unfortunately the date did cost you 25 Euro.";
    	return str;
    }
    
	@Functional
    public String fan(){
		
		Vector<String> Heroes = NOC.getAllKeysWithFieldValue("Category", "Hero");
    	String hero = NOC.selectRandomlyFrom(Heroes);
		
    	if (NOC.hasFieldValue("Gender", hero, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().withdraw(15);
    	str = "You can't believe you just met " + hero + ", your favourite hero! " + pronoun + " charged you 15 Euro for the picture.";
    	return str;
    }
    
	@Functional
    public String underPaid(){
		
		Vector<String> Detectives = NOC.getAllKeysWithFieldValue("Category", "Detective");
		String detective = NOC.selectRandomlyFrom(Detectives);
		
    	int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
    	GameState.getInstance().getCurrentPlayer().withdraw(i*15);
    	
    	str = detective + " who is a detective, does some background check on you and finds out that you underpaid for your properties. You must repay 15 for every property you own.";
    	return str;
    }
    
	@Functional
    public String taxFraud(){
		
		Vector Male = NOC.getAllKeysWithFieldValue("Gender", "male");
		String man = NOC.selectRandomlyFrom(Male);
		
    	int i = GameState.getInstance().getCurrentPlayer().getBalance();
    	int amount = i/15;
    	
    	GameState.getInstance().getCurrentPlayer().withdraw(amount);
    	str = "You're caught for avoiding tax payments. Apparently " + man + " ratted you out for it. You must pay " + amount + " Euro.";
    	return str;
    	
    }
    
	@Functional
    public String droppingMoney(){
		
		Vector<String> Killers = NOC.getAllKeysWithFieldValue("Category", "Killer");
		Vector<String> Athletes = NOC.getAllKeysWithFieldValue("Category", "Athlete");
		Vector<String> Females = NOC.getAllKeysWithFieldValue("Category", "Female Impersonator");
		
		String killer = NOC.selectRandomlyFrom(Killers);
		String athlete = NOC.selectRandomlyFrom(Athletes);
		String female = NOC.selectRandomlyFrom(Females);
		
    	GameState.getInstance().getNextPlayer(GameState.getInstance().getCurrentPlayer()).withdraw(50);
    	GameState.getInstance().getCurrentPlayer().deposit(50);
    	
    	str = "The player infront of you dropped 50 euro. You, " + killer + ", " + athlete + " and " + female + " race for it, but you get there first." ;
    	return str;
    }
    
	@Functional
    public String geeks(){
    	Vector<String> geekyPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "geeky");
    	
    	String geekyPerson = NOC.selectRandomlyFrom(geekyPeople);
    	String geekyPerson2 = NOC.selectRandomlyFrom(geekyPeople);
    	
    	if(geekyPerson == geekyPerson2){
    		geekyPerson2 = NOC.selectRandomlyFrom(geekyPeople);
    	}
    	GameState.getInstance().getCurrentPlayer().withdraw(50);
    	str = "You entered a competition full of geeks such as "+ geekyPerson+ " and " + geekyPerson2 + " aswell as a few others. You obviously lose the competition since you're not on the same level as them. Entry cost you 50 Euro.";
    	return str;
    }
    
	@Functional
	public String taxMan(){
		Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
		String villain = NOC.selectRandomlyFrom(Villains);
		
		Vector<String> villainsNegativeTraitChoice = NOC.getFieldValues("Negative Talking Points", villain);
		String villainsNegativePoint = NOC.selectRandomlyFrom(villainsNegativeTraitChoice);
		
		int i = GameState.getInstance().getCurrentPlayer().getBalance();
		int amount = i/10;
	   
		GameState.getInstance().getCurrentPlayer().withdraw(amount);
		str = villain  + ", who is "+ villainsNegativePoint +", comes to your house disguised as a taxman and manages to scam you for " + amount + " Euro.";
		return str;
	}
   
	@Functional
	public String interest(){
		
		Vector<String> Boxers = NOC.getAllKeysWithFieldValue("Category", "Boxer");
		String boxer = NOC.selectRandomlyFrom(Boxers);
		
		int i = GameState.getInstance().getCurrentPlayer().getBalance();
		int amount = i/16;
		GameState.getInstance().getCurrentPlayer().deposit(amount);
	  
		str = boxer + " tells you about a loop-hole in the banks interest system. You receive 6% interest on your balance which comes to a nice " + amount + " Euro.";
		return str;
	   
	}
   
	@Functional
	public String moneyBack(){
	   GameState.getInstance().getCurrentPlayer().deposit(50);
	   
	   Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
	   String villain = NOC.selectRandomlyFrom(Villains);

	   Vector<String> villainsAddress1 = NOC.getFieldValues("Address 1", villain);
	   Vector<String> villainsAddress2 = NOC.getFieldValues("Address 2", villain);
	   Vector<String> villainsAddress3 = NOC.getFieldValues("Address 3", villain);

	   if(villainsAddress3 == null){
		   str = "You were invited to visit " + villain + "'s house, who you're a big fan off. He gave you a present of 50.";
	   }else if(villainsAddress2 == null){
		   str = "You were invited to " + villainsAddress3.get(0) + " to visit " + villain + ", who you're a big fan off. He gave you a present of 50.";
	   }else if(villainsAddress1 == null){
		   	str = "You were invited to " + villainsAddress2.get(0) +", "+ villainsAddress3.get(0) + " to visit " + villain + ", who you're a big fan off. He gave you a present of 50.";   		
	   }else{
		   str = "You were invited to " + villainsAddress1.get(0) +", "+ villainsAddress2.get(0) +", "+ villainsAddress3.get(0) + " to visit " + villain + ", who you're a big fan off. He gave you a present of 50.";
	   }
	   return str;
	}
   
	@Functional
	public String houseParty(){
		
		Vector<String> Heroes = NOC.getAllKeysWithFieldValue("Category", "Hero");
		Vector<String> Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
		Vector<String> Billionaires = NOC.getAllKeysWithFieldValue("Category", "Billionaire");
		Vector<String> fatPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "fat");
		Vector<String> Killers = NOC.getAllKeysWithFieldValue("Category", "Killer");
	
		String hero = NOC.selectRandomlyFrom(Heroes);
		String villain = NOC.selectRandomlyFrom(Villains);
		String billionaire = NOC.selectRandomlyFrom(Billionaires);
		String fatPerson = NOC.selectRandomlyFrom(fatPeople);
		String killer = NOC.selectRandomlyFrom(Killers);
		
		int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
		int amount = i*25;
		GameState.getInstance().getCurrentPlayer().withdraw(amount);
	   
		str = "You host a week of house parties where you welcomed guests such as " + hero + ", "+ villain + ", " + billionaire + ", " + fatPerson + " and " + killer + ". They ended up wrecking all of your properties partially. You have to pay 25 for each property damaged.";
		return str;
	}
    
	@Functional
	public String wallet(){
		
		Vector<String> Athletes = NOC.getAllKeysWithFieldValue("Category", "Athlete");
		String athlete = NOC.selectRandomlyFrom(Athletes);
		
		Vector<String> athletesTypicalActivityChoices = NOC.getFieldValues("Typical Activity", athlete);
		String athletesActivity = NOC.selectRandomlyFrom(athletesTypicalActivityChoices);
	   
		if (NOC.hasFieldValue("Gender", athlete, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
	   
	   GameState.getInstance().getCurrentPlayer().deposit(130);
	   str = "You eye up "+ athlete + " as " + pronoun + " is seen " + athletesActivity + ". Whilst " + pronoun + "'s not looking you manage to rob " + possPro + " wallet. You secured yourself a nice 130 Euro.";
	   return str;
	}
   
	@Functional
	public String smashedPhone(){
		
		GameState.getInstance().getCurrentPlayer().withdraw(150);
		
		Vector<String> Female = NOC.getAllKeysWithFieldValue("Gender", "female");
		String female = NOC.selectRandomlyFrom(Female);
	 
		Vector<String> femalesWeaponChoice = NOC.getFieldValues("Weapon of Choice", female);
		String femalesWeapon = NOC.selectRandomlyFrom(femalesWeaponChoice);
	 
		if(femalesWeapon==null){
			femalesWeaponChoice = NOC.getFieldValues("Weapon of Choice", female);
			femalesWeapon = NOC.selectRandomlyFrom(femalesWeaponChoice);
	 }
	   
	str = "Your ex-girlfriend "+ female + " smashes your phone with her " + femalesWeapon + ". It costs you 150 Euro to repair it.";
	return str;
	}
   
	@Functional
	public String brokenPhone(){
	   GameState.getInstance().getCurrentPlayer().withdraw(150);
	   
	   Vector<String> fatPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "fat");
	   String fatPerson = NOC.selectRandomlyFrom(fatPeople);
		   
	   if (NOC.hasFieldValue("Gender", fatPerson, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
	   
		str = fatPerson + " sits on your phone. Since " + pronoun + " is fat it breaks the phone and you have to pay 150 to fix it.";
		return str;
	}
   
	@Functional
	public String pawnShop(){
		GameState.getInstance().getCurrentPlayer().withdraw(80);
		
		Vector<String> Billionaires = NOC.getAllKeysWithFieldValue("Category", "Billionaire");
		String billionaire = NOC.selectRandomlyFrom(Billionaires);
		
		Vector<String> billionariesClothesChoices = NOC.getFieldValues("Seen Wearing", billionaire);
		String billionairesClothes = NOC.selectRandomlyFrom(billionariesClothesChoices);
		
		str = "You go to a pawn shop where you find " + billionaire + "'s famous " + billionairesClothes + ". You decide to spend 80 Euro on it.";
		return str;
	}
  
	@Functional
	public String cheapParking(){
		GameState.getInstance().getCurrentPlayer().withdraw(25);
		
		Vector<String> Fictional = NOC.getAllKeysWithFieldValue("Fictive Status", "fictional");
		String fictional = NOC.selectRandomlyFrom(Fictional);
		
		str = fictional + " tells you about cheap parking down the road which only costs you 25 for the week.";
		return str;
	}
  
	@Functional
	public String petrolMoney(){
		GameState.getInstance().getCurrentPlayer().withdraw(50);
		
		Vector<String> Male = NOC.getAllKeysWithFieldValue("Gender", "male");
		String man = NOC.selectRandomlyFrom(Male);
		
		Vector<String> mansCarChoice = NOC.getFieldValues("Vehicle of Choice", man);
		String mansCar = NOC.selectRandomlyFrom(mansCarChoice);
		
		str = "You borrow " + man + "'s " + mansCar + " for the week. Insurance will cost you 50 for the week.";
		return str;
  }
}
