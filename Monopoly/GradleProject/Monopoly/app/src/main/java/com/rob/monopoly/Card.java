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
	
	private Vector Heroes = NOC.getAllKeysWithFieldValue("Category", "Hero");
	private Vector Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
	private Vector Comedians = NOC.getAllKeysWithFieldValue("Category", "Comedian");
	private Vector Magicians = NOC.getAllKeysWithFieldValue("Category", "Magician");
	private Vector Singers = NOC.getAllKeysWithFieldValue("Category", "Singer");
	private Vector Directors = NOC.getAllKeysWithFieldValue("Category", "Director");
	private Vector Boxers = NOC.getAllKeysWithFieldValue("Category", "Boxer");
	private Vector Presidents = NOC.getAllKeysWithFieldValue("Category", "President");
	private Vector Detectives = NOC.getAllKeysWithFieldValue("Category", "Detective");
	private Vector Billionaires = NOC.getAllKeysWithFieldValue("Category", "Billionaire");
	private Vector Killers = NOC.getAllKeysWithFieldValue("Category", "Killer");
	private Vector Judges = NOC.getAllKeysWithFieldValue("Category", "Judge");
	private Vector Athletes = NOC.getAllKeysWithFieldValue("Category", "Athlete");
	private Vector Actors = NOC.getAllKeysWithFieldValue("Category", "Actor");
	private Vector Fictional = NOC.getAllKeysWithFieldValue("Fictive Status", "fictional");
	private Vector fatPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "fat");
	private Vector geekyPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "geeky");

	
	private Vector Male = NOC.getAllKeysWithFieldValue("Gender", "male");
	private Vector Female = NOC.getAllKeysWithFieldValue("Gender", "female");

	private String boxer = NOC.selectRandomlyFrom(Boxers);
	private String detective = NOC.selectRandomlyFrom(Detectives);
	private String geekyPerson = NOC.selectRandomlyFrom(geekyPeople);
	private String fatPerson = NOC.selectRandomlyFrom(fatPeople);
	private String killer = NOC.selectRandomlyFrom(Killers);
	private String billionaire = NOC.selectRandomlyFrom(Billionaires);
	private String hero = NOC.selectRandomlyFrom(Heroes);
	private String villain = NOC.selectRandomlyFrom(Villains);
	private String female = NOC.selectRandomlyFrom(Female);
	private String man = NOC.selectRandomlyFrom(Male);
	private String athlete = NOC.selectRandomlyFrom(Athletes);
	private String magician = NOC.selectRandomlyFrom(Magicians);
	private String comedian = NOC.selectRandomlyFrom(Comedians);
	private String judge = NOC.selectRandomlyFrom(Judges);
	private String actor = NOC.selectRandomlyFrom(Actors);
	private String director = NOC.selectRandomlyFrom(Directors);
	private String fictional = NOC.selectRandomlyFrom(Fictional);

	private Vector<String> judgesPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", judge);
	private Vector<String> directorsPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", director);
	private Vector<String> actorsPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", actor);
	
	private Vector<String> mansPositiveTraitChoice = NOC.getFieldValues("Positive Talking Points", man);
	private Vector<String> mansNegativeTraitChoice = NOC.getFieldValues("Negative Talking Points", man);
	private Vector<String> mansActivityChoice = NOC.getFieldValues("Typical Activity", man);
	private Vector<String> mansCarChoice = NOC.getFieldValues("Vehicle of Choice", man);
	
	private Vector<String> villainsWeaponChoice = NOC.getFieldValues("Weapon of Choice", villain);
	private Vector<String> villainsNegativeTraitChoice = NOC.getFieldValues("Negative Talking Points", villain);
	
	private Vector<String> femalesClothingChoice = NOC.getFieldValues("Seen Wearing", female);
	private Vector<String> femalesPositiveTraitChoice = NOC.getFieldValues("Positive Talking Points", female);
	
	private Vector<String> athletesNegativeChoices = NOC.getFieldValues("Negative Talking Points", athlete);
	private Vector<String> athletesTypicalActivityChoices = NOC.getFieldValues("Typical Activity", athlete);
	
	private Vector<String> billionairesCarChoises = NOC.getFieldValues("Vehicle of Choice", billionaire);
	private Vector<String> billionariesClothesChoices = NOC.getFieldValues("Seen Wearing", billionaire);
	

	private String judgesPositivePoint = NOC.selectRandomlyFrom(judgesPositiveTraitChoices);
	private String actorsPositivePoint = NOC.selectRandomlyFrom(actorsPositiveTraitChoices);
	private String directorsPositivePoint = NOC.selectRandomlyFrom(directorsPositiveTraitChoices);
	
	private String mansPositivePoint = NOC.selectRandomlyFrom(mansPositiveTraitChoice);
	private String mansNegativePoints = NOC.selectRandomlyFrom(mansNegativeTraitChoice);
	private String menActivities = NOC.selectRandomlyFrom(mansActivityChoice);
	private String mansCar = NOC.selectRandomlyFrom(mansCarChoice);
	
	private String villainsWeapon = NOC.selectRandomlyFrom(villainsWeaponChoice);
	private String villainsNegativePoint = NOC.selectRandomlyFrom(villainsNegativeTraitChoice);
	
	private String billionairesCar = NOC.selectRandomlyFrom(billionairesCarChoises);
	private String billionairesClothes = NOC.selectRandomlyFrom(billionariesClothesChoices);
	
	private String athletesNegativePoints = NOC.selectRandomlyFrom(athletesNegativeChoices);
	private String athletesActivity = NOC.selectRandomlyFrom(athletesTypicalActivityChoices);
	
	private String femalesClothing = NOC.selectRandomlyFrom(femalesClothingChoice);
	private String femalesPositiveTrait = NOC.selectRandomlyFrom(femalesPositiveTraitChoice);
	
	private String pronoun = "he";
	private String possPro = "his";
	
	public String runAway(){
		Random randomNumber = new Random();
        int i = randomNumber.nextInt(10)+1;
                
        if (NOC.hasFieldValue("Gender", villain, "female"))
    		{
    			pronoun = "she";
    			possPro = "her";
    		}
        GameState.getInstance().getCurrentPlayer().move(i);
        str = "You see " + villain + " and "+ possPro +" " + villainsWeapon + " so you run away as far as you can. You move " + i + " places and find yourself in a new location";
        return str;
	}
	
	public String hospital(){
		if (NOC.hasFieldValue("Gender", killer, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		
		GameState.getInstance().getCurrentPlayer().withdraw(150);
		str ="You get into a massive fight with " + killer + " in which you break your leg. You have to pay 150 to get a cast at the hospital.";
		return str;
	}
	
	public String birthday(){

		if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		GameState.getInstance().getCurrentPlayer().deposit(200);
		str = "It's your birthday! " + billionaire + " sent you a card that had 200 in it.";
		return str;
    }
	
    public String birthday2(){
    	
    	int i = GameState.getInstance().getNumPlayers();
        GameState.getInstance().getCurrentPlayer().deposit(i*50);
    	
    	if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}

        str = "You made friends with " + villain + " who threatened every player playing the game with " + possPro + " " + villainsWeapon + " to make sure they give you a birthday present. You receive " + i + " from every player.";
        return str;
    }
    
    public String christmas(){
    	
    	int i = GameState.getInstance().getNumPlayers();
    	GameState.getInstance().getCurrentPlayer().withdraw(i*20);
    	str = "Your lovely and " + mansPositivePoint + " friend " + man + " reminded you it's time for Kris Kindle. The presents end up costing you " + i + " Euro.";
    	return str;
    }
    
    public String christmas2(){
    	int i = GameState.getInstance().getNumPlayers();
    	GameState.getInstance().getCurrentPlayer().deposit(i*30);
    	str = "Although you've been unpopular this week on twitter for slating " + fatPerson + " for being fat, all the players have been thoughtful and sent you 30 each.";
    	return str;
    }
    
    public String wildNight(){
    	Random randomNumber = new Random();
        int i = randomNumber.nextInt(10)+1;
        
        if(femalesClothing == null){
    		femalesClothing = "Underwear";
    	}

        // GameState.getInstance().getCurrentPlayer().setPlayerLocation(i);
		str = "The " + femalesPositiveTrait + " " + female + " left her " + femalesClothing +  " at your house from your wild night yesterday. you've to meet up to give it back to her. You move " + i + " places." ;
		return str;
    }

    public String meeting(){

        Random randomNumber = new Random();
        int i = randomNumber.nextInt(10)+1;

        GameState.getInstance().getCurrentPlayer().setPlayerLocation(i);
        str = "You go to a meeting with the "+ mansNegativePoints+ " " + man + " to talk about how " + pronoun +" loves " + menActivities + ". You move " + i + " places and find yourself on a new location on the board.";
        return str;
    }

    public String sportsCompetition(){
    	if (NOC.hasFieldValue("Gender", athlete, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().deposit(125);
    	str = "You enter a sports competition in which you face the famous athlete " + athlete + ". You use the fact that " + pronoun + " is " + athletesNegativePoints + " to your advantage and manage to win the competition and a nice prize of 150.";
    	return str;
    }

    public String backToStart(){
    	
    	if (NOC.hasFieldValue("Gender", magician, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().setPlayerLocation(0);
    	GameState.getInstance().getCurrentPlayer().deposit(200);

     str = magician + " uses " + possPro + " magic powers to send you to the start.";
     return str;
    }

    public String robbery(){
    	GameState.getInstance().getCurrentPlayer().deposit(100);
    	if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	str = "You get pickpocketed by " + villain + ". " + pronoun + " manages to steal 100 from your pocket.";
    	return str;
    }

    public String carCrash(){
    	if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	GameState.getInstance().getCurrentPlayer().withdraw(200);
    	str = "You end up crashing into " + billionaire + "'s " + billionairesCar + ". You have to pay for some of the damages made to the vehicle which costs you 200.";
    	return str;
    }

    public String stealing(){
    	
    	if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().deposit(125);
    	str = "You end up stealing the " + billionairesClothes + " from a known billionaire named " + billionaire + ", which you end up selling on eBay for a whopping 250. Sure it's not like " + pronoun + " would care anyways since " + pronoun + " owns a " + billionairesCar + ".";
    	return str;
    }

   public String bowling(){
	   GameState.getInstance().getCurrentPlayer().withdraw(40);
       str = "You go bowling with the " + directorsPositivePoint + " " + director + ", the " + actorsPositivePoint + " " + actor + " and " + athlete + ", who's known to be " + athletesNegativePoints + ". You pay for everyone's game which costs you 40." ;
       return str;
   }

   public String onlineShop(){
	   
	   if (NOC.hasFieldValue("Gender", female, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
	   GameState.getInstance().getCurrentPlayer().withdraw(50);
	   str = "You kinda have a crush on " + female + ", so you buy " + possPro + " something similar to the " + femalesClothing + " " + pronoun + " always wears.";
	   return str;
   }

    public String propertyDamages(){
    	
    	int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
    	if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
   
    	if(i==0){
    		str = "Since you own no properties, the " + villainsNegativePoint + " " + villain + " could not make any damages to properties but " + pronoun +" did try to break into your car leaving you with damages of 50 euro.";
    		GameState.getInstance().getCurrentPlayer().withdraw(50);
    	}else{
    	   	str = "The " + villainsNegativePoint + " " + villain + " made damages to all "+ i + " properties that you own. You pay 25 for each property damaged.";
    	   
        	GameState.getInstance().getCurrentPlayer().withdraw(i*25);
    	}
    	return str;
    }
    
    public String coffee(){
    	GameState.getInstance().getCurrentPlayer().withdraw(25);
    	str = female + " asked you on a cute coffee date. You think that she might be the one, but the date did cost you 25.";
    	return str;
    }
    
    public String fan(){
    	
    	if (NOC.hasFieldValue("Gender", hero, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	
    	GameState.getInstance().getCurrentPlayer().withdraw(15);
    	str = "You can't believe you just met " + hero + ", your favourite hero! " + pronoun + " charged you 15 for the picture.";
    	return str;
    }
    
    public String underPaid(){
    	int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
    	GameState.getInstance().getCurrentPlayer().withdraw(i*15);
    	
    	str = detective + " who is a detective, does some background check on you and finds out that you underpaid for your properties. You must repay 15 for every property you own.";
    	return str;
    }
    
    public String taxFraud(){
    	int i = GameState.getInstance().getCurrentPlayer().getBalance();
    	i = i/15;
    	
    	GameState.getInstance().getCurrentPlayer().withdraw(i);
    	str = "You're caught for aStringing tax payments. Apparently " + man + " ratted you out for tax fraud.";
    	return str;
    	
    }
    
    public String droppingMoney(){
    	GameState.getInstance().getNextPlayer(GameState.getInstance().getCurrentPlayer()).withdraw(50);
    	GameState.getInstance().getCurrentPlayer().deposit(50);
    	
    	str = "The player infront of you dropped 50 euro. You, " + killer + ", " + athlete + " and " + female + " race for it, but you get there first." ;
    	return str;
    }
    
    public String geeks(){
    	Vector geekyPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "geeky");
    	
    	String geekyPerson = NOC.selectRandomlyFrom(geekyPeople);
    	String geekyPerson2 = NOC.selectRandomlyFrom(geekyPeople);
    	
    	if(geekyPerson == geekyPerson2){
    		geekyPerson2 = NOC.selectRandomlyFrom(geekyPeople);
    	}
    	GameState.getInstance().getCurrentPlayer().withdraw(50);
    	str = "You entered a competition full of geeks such as "+ geekyPerson+ " and " + geekyPerson2 + " aswell as a few others. You obviously lose the competition since you're not on the same level as them. Entry cost you 50.";
    	return str;
    }
    
   public String taxMan(){
	   int i = GameState.getInstance().getCurrentPlayer().getBalance();
	   i = i/10;
	   
	   GameState.getInstance().getCurrentPlayer().withdraw(i);
	   str = villain  + ", who is "+ villainsNegativePoint +", comes to your house disguised as a taxman and manages to scam you for " + i;
	   return str;
   }
   
   public String interest(){
	   int i = GameState.getInstance().getCurrentPlayer().getBalance();
	   i = i/16;
	   GameState.getInstance().getCurrentPlayer().deposit(i);
	  
	  str = boxer + " tells you about a loop-hole in the banks interest system. You receive 6% interest on your balance.";
	  return str;
	   
   }
   
   public String moneyBack(){
	   GameState.getInstance().getCurrentPlayer().deposit(50);

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
   
//   public String jail(){
//	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(34);
//	   // You hate villain because he is NEGATIVE, NEGATIVE, NEGATIVE. you get sent to jail for assaulting him.
//	   
//   }
//   
//   public String jail2(){
//	   //GameState.getInstance().getCurrentPlayer().setPlayerLocation(34);
//	   // you are caught stealing Villains vehicle. you get caught and sent to jail
//   }
//   
//   public String game(){
//	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(5);
//	   
//	   // it's been a long weekend so you and HERO go to play a game
//   }
//   
//   public String game2(){
//	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(29);
//	   
//	   // it's been a long weekend so you and Villain go to play a game
//   }
//   
//   public String freeParking(){
//	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(20);
//	   
//	   // You manage to find free parking before you meet up with HERO.
//   }
//   
//   public String teleporting(){
//	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(16);
//	   
//	   // HERO bring you in his vehicle to a secret location. You's find a teleporting device and decide to try it out
//   }
   
   public String houseParty(){
	   int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
	   GameState.getInstance().getCurrentPlayer().withdraw(i*25);
	   
	   // You host a house party in every property you own that week inviting people such as HERO,HERO,HERO,HERO. Some damages were made to all properties.
   str = "You host a week of house parties where you welcomed guests such as " + hero + ", "+ villain + ", " + billionaire + ", " + fatPerson + " and " + killer + ". They ended up wrecking all of your properties partially. You have to pay 25 for each property damaged.";
   return str;
   }
   
//   public String negativeSocialMedia(){
//	   Random randomNumber = new Random();
//       int i = randomNumber.nextInt(15)+1;
//
//       // GameState.getInstance().getCurrentPlayer().move(i);
//	   // you receive a lot of backlash for saying that HERO is NEGATIVE, NEGATIVE and NEGATIVE online and are chased on the streets until you find yourself somewhere safe.
//   }
   
   public String wallet(){
	   
	   if (NOC.hasFieldValue("Gender", athlete, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
	   
	GameState.getInstance().getCurrentPlayer().deposit(130);
	   str = "You eye up "+ athlete + " as " + pronoun + " is seen " + athletesActivity + ". Whilst " + pronoun + "'s not looking you manage to rob " + possPro + " wallet. You secured yourself a nice 130 euro.";
	   return str;
   }
   
   public String smashedPhone(){
	 GameState.getInstance().getCurrentPlayer().withdraw(150);
	 
	 Vector<String> femalesWeaponChoice = NOC.getFieldValues("Weapon of Choice", female);
	 String femalesWeapon = NOC.selectRandomlyFrom(femalesWeaponChoice);
	 
	 if(femalesWeapon==null){
		 femalesWeaponChoice = NOC.getFieldValues("Weapon of Choice", female);
		 femalesWeapon = NOC.selectRandomlyFrom(femalesWeaponChoice);
	 }
	   
	str = "Your ex-girlfriend "+ female + " smashes your phone with her " + femalesWeapon + ". It costs you 150 to repair it.";
	return str;
   }
   
   public String brokenPhone(){
	   GameState.getInstance().getCurrentPlayer().withdraw(150);
		   
	   if (NOC.hasFieldValue("Gender", fatPerson, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
	   
		str = fatPerson + " sits on your phone. Since " + pronoun + " is fat it breaks the phone and you have to pay 120 to fix it.";
		return str;
   }
   
//   public String goToInsurance(){
//	  // GameState.getInstance().getCurrentPlayer().setPlayerLocation(11);
//	   
//	   // Villain spread rumours you're a tax fraud so you head to the tax office.
//   }
   
  public String pawnShop(){
	 GameState.getInstance().getCurrentPlayer().withdraw(80);
	  
	  
	 str = "You go to a pawn shop where you find " + billionaire + "'s famous " + billionairesClothes + ". You decide to spend 80 euro on it.";
	 return str;
  }
  
  public String cheapParking(){
	 GameState.getInstance().getCurrentPlayer().withdraw(25);
	  
	 str = fictional + " tells you about cheap parking down the road which only costs you 25 for the week.";
	 return str;
  }
  
  public String petrolMoney(){
	  GameState.getInstance().getCurrentPlayer().withdraw(50);
	  str = "You borrow " + man + "'s " + mansCar + " for the week. Insurance will cost you 50 for the week.";
	  return str;
  }
	
	
	// beyonce and nosomethingcoward, toms d tuckarumento, pep la pyu, nicole pagannini, nicole makiveli, 
}
