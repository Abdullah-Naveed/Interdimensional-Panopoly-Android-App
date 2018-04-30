package com.rob.monopoly;

import com.rob.monopoly.NOCList.twitterbotics.KnowledgeBaseModule;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Card{

	public Card(){

	}

    KnowledgeBaseModule NOC=new KnowledgeBaseModule(GameState.getInstance().getContext());

	Vector Heroes = NOC.getAllKeysWithFieldValue("Category", "Hero");
	Vector Villains = NOC.getAllKeysWithFieldValue("Category", "Villain");
	Vector Comedians = NOC.getAllKeysWithFieldValue("Category", "Comedian");
	Vector Magicians = NOC.getAllKeysWithFieldValue("Category", "Magician");
	Vector Singers = NOC.getAllKeysWithFieldValue("Category", "Singer");
	Vector Directors = NOC.getAllKeysWithFieldValue("Category", "Director");
	Vector Boxers = NOC.getAllKeysWithFieldValue("Category", "Boxer");
	Vector Presidents = NOC.getAllKeysWithFieldValue("Category", "President");
	Vector Detectives = NOC.getAllKeysWithFieldValue("Category", "Detective");
	Vector Billionaires = NOC.getAllKeysWithFieldValue("Category", "Billionaire");
	Vector Killers = NOC.getAllKeysWithFieldValue("Category", "Killer");
//	Vector Judges = NOC.getAllKeysWithFieldValue("Category", "Judge");
	Vector Athletes = NOC.getAllKeysWithFieldValue("Category", "Athlete");
	Vector Actors = NOC.getAllKeysWithFieldValue("Category", "Actor");
	Vector Fictional = NOC.getAllKeysWithFieldValue("Fictive Status", "fictional");
	Vector fatPeople = NOC.getAllKeysWithFieldValue("Negative Talking Points", "fat");


	Vector Male = NOC.getAllKeysWithFieldValue("Gender", "male");
	Vector Female = NOC.getAllKeysWithFieldValue("Gender", "female");

	String fatPerson = NOC.selectRandomlyFrom(fatPeople);
	String killer = NOC.selectRandomlyFrom(Killers);
	String billionaire = NOC.selectRandomlyFrom(Billionaires);
	String hero = NOC.selectRandomlyFrom(Heroes);
	String villain = NOC.selectRandomlyFrom(Villains);
	String female = NOC.selectRandomlyFrom(Female);
	String man = NOC.selectRandomlyFrom(Male);
	String athlete = NOC.selectRandomlyFrom(Athletes);
	String magician = NOC.selectRandomlyFrom(Magicians);
	String comedian = NOC.selectRandomlyFrom(Comedians);
//	String judge = NOC.selectRandomlyFrom(Judges);
	String actor = NOC.selectRandomlyFrom(Actors);
	String director = NOC.selectRandomlyFrom(Directors);

//	Vector<String> judgesPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", judge);
	Vector<String> directorsPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", director);
	Vector<String> actorsPositiveTraitChoices = NOC.getFieldValues("Positive Talking Points", actor);

	Vector<String> mansPositiveTraitChoice = NOC.getFieldValues("Positive Talking Points", man);
	Vector<String> mansNegativeTraitChoice = NOC.getFieldValues("Negative Talking Points", man);
	Vector<String> mansActivityChoice = NOC.getFieldValues("Typical Activity", man);

	Vector<String> villainsWeaponChoice = NOC.getFieldValues("Weapon of Choice", villain);
	Vector<String> villainsNegativeTraitChoice = NOC.getFieldValues("Negative Talking Points", villain);

	Vector<String> femalesClothingChoice = NOC.getFieldValues("Seen Wearing", female);
	Vector<String> femalesPositiveTraitChoice = NOC.getFieldValues("Positive Talking Points", female);

	Vector<String> athletesNegativeChoices = NOC.getFieldValues("Negative Talking Points", athlete);

	Vector<String> billionairesCarChoises = NOC.getFieldValues("Vehicle of Choice", billionaire);
	Vector<String> billionariesClothesChoices = NOC.getFieldValues("Seen Wearing", billionaire);

//	String judgesPositivePoint = NOC.selectRandomlyFrom(judgesPositiveTraitChoices);
	String actorsPositivePoint = NOC.selectRandomlyFrom(actorsPositiveTraitChoices);
	String directorsPositivePoint = NOC.selectRandomlyFrom(directorsPositiveTraitChoices);

	String mansPositivePoint = NOC.selectRandomlyFrom(mansPositiveTraitChoice);
	String mansNegativePoints = NOC.selectRandomlyFrom(mansNegativeTraitChoice);
	String menActivities = NOC.selectRandomlyFrom(mansActivityChoice);

	String villainsWeapon = NOC.selectRandomlyFrom(villainsWeaponChoice);

	String billionairesCar = NOC.selectRandomlyFrom(billionairesCarChoises);
	String billionairesClothes = NOC.selectRandomlyFrom(billionariesClothesChoices);

	String athletesNegativePoints = NOC.selectRandomlyFrom(athletesNegativeChoices);

	String femalesClothing = NOC.selectRandomlyFrom(femalesClothingChoice);
	String femalesPositiveTrait = NOC.selectRandomlyFrom(femalesPositiveTraitChoice);

	String pronoun   = "he";
	String possPro	 = "his";
	
	public void runAway(){
		Random randomNumber = new Random();
       		int i = randomNumber.nextInt(10)+1;
                
        if (NOC.hasFieldValue("Gender", villain, "female"))
    		{
    			pronoun = "she";
    			possPro = "her";
    		}
		
        GameState.getInstance().getCurrentPlayer().move(i);
        System.out.println("You see " + villain + " and "+ possPro +" " + villainsWeapon + " so you run away as far as you can. You move " + i + " places and find yourself in a new location" );
	}
	
	public void hospital(){
		
		if (NOC.hasFieldValue("Gender", killer, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		
		GameState.getInstance().getCurrentPlayer().withdraw(150);
		System.out.println("you get into a massive fight with " + killer + " in which you break your leg. You have to pay 150 to get a cast at the hospital.");
    }
	
	public void birthday(){

		if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
		GameState.getInstance().getCurrentPlayer().deposit(200);
		System.out.println("It's your birthday! " + billionaire + " sent you a card that had 200 in it.");
    }

    public void birthday2(){

    	int i = GameState.getInstance().getNumPlayers();
        GameState.getInstance().getCurrentPlayer().deposit(i*50);

    	if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}

        System.out.println("You made friends with " + villain + " who threatened every player playing the game with " + possPro + " " + villainsWeapon + " to make sure they give you a birthday present. You receive " + i + " from every player.");
    }

    public void christmas(){

    	int i = GameState.getInstance().getNumPlayers();
    	GameState.getInstance().getCurrentPlayer().withdraw(i*20);

    	System.out.println("Your lovely and " + mansPositivePoint + " friend " + man + " reminded you it's time for Kris Kindle. The presents end up costing you " + i );
    }

    public void christmas2(){
    	int i = GameState.getInstance().getNumPlayers();
    	GameState.getInstance().getCurrentPlayer().deposit(i*30);

    	System.out.println("Although you've been unpopular this week on twitter for slating " + fatPerson + " for being fat, all the players have been thoughtful and sent you 30 each." );
    }

    public void wildNight(){
    	Random randomNumber = new Random();
        int i = randomNumber.nextInt(10)+1;

        if(femalesClothing == null){
    		femalesClothing = "Underwear";
    	}

        GameState.getInstance().getCurrentPlayer().setPlayerLocation(i);
		System.out.println("The " + femalesPositiveTrait + " " + female + " left her " + femalesClothing +  " at your house from your wild night yesterday. you've to meet up to give it back to her. You move " + i + " places." );

    }

    public void meeting(){

        Random randomNumber = new Random();
        int i = randomNumber.nextInt(10)+1;

        GameState.getInstance().getCurrentPlayer().setPlayerLocation(i);
        System.out.println("You go to a meeting with the "+ mansNegativePoints+ " " + man + " to talk about how " + pronoun +" loves " + menActivities + ". You move " + i + " places and find yourself on a new location on the board.");
    }

    public void sportsCompetition(){
    	if (NOC.hasFieldValue("Gender", athlete, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}

    	GameState.getInstance().getCurrentPlayer().deposit(125);
    	System.out.println("You enter a sports competition in which you face the famous athlete " + athlete + ". You use the fact that " + pronoun + " is " + athletesNegativePoints + " to your advantage and manage to win the competition and a nice prize of 150.");
    }

    public void backToStart(){

    	if (NOC.hasFieldValue("Gender", magician, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}

    	GameState.getInstance().getCurrentPlayer().setPlayerLocation(0);
    	GameState.getInstance().getCurrentPlayer().deposit(200);

     System.out.println(magician + " uses " + possPro + " magic powers to send you to the start.");
    }

    public void robbery(){
    	GameState.getInstance().getCurrentPlayer().deposit(100);
    	if (NOC.hasFieldValue("Gender", villain, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}

    	System.out.println("You get pickpocketed by " + villain + ". " + pronoun + " manages to steal 100 from your pocket.");
    }

    public void carCrash(){
    	if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}
    	GameState.getInstance().getCurrentPlayer().withdraw(200);
    	System.out.println("You end up crashing into " + billionaire + "'s " + billionairesCar + ". You have to pay for some of the damages made to the vehicle which costs you 200.");
    }

    public void stealing(){

    	if (NOC.hasFieldValue("Gender", billionaire, "female"))
		{
			pronoun = "she";
			possPro = "her";
		}

    	GameState.getInstance().getCurrentPlayer().deposit(125);
    	System.out.println("You end up stealing the " + billionairesClothes + " from a known billionaire named " + billionaire + ", which you end up selling on eBay for a whopping 250. Sure it's not like " + pronoun + " would care anyways since " + pronoun + " owns a " + billionairesCar + ".");
    }

//   public void bowling(){
//	GameState.getInstance().getCurrentPlayer().withdraw(40);
//       	System.out.println("You go bowling with the " + directorsPositivePoint + " " + director + ", the " + actorsPositivePoint + " " + actor + " and " + judge + ", who's known to be " + judgesPositivePoint + ". You pay for everyone's game which costs you 40." );
//   }

   public void onlineShop(){
	   //GameState.getInstance().getCurrentPlayer().withdraw(50);

       // You bought hero a new jacket similar to his favourite clothing.
   }

    public void propertyDamages(){
    	// int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
    	// GameState.getInstance().getCurrentPlayer().withdraw(i*25);

        // the NEGATIVE villain made damages to all of your properties. pay 25 for every property you own
    }
    
    public void coffee(){
    	//GameState.getInstance().getCurrentPlayer().withdraw(25);
    	// hero picked you up in his vehicle. yous go for a coffee which cost you 25
    }
    
    public void fan(){
    	//GameState.getInstance().getCurrentPlayer().withdraw(15);
    	// You see your favourite hero on the streets, he charges you 15 for a picture
    }
    
    public void underPaid(){
    	//int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
    	//GameState.getInstance().getCurrentPlayer().withdraw(i*15);
    	
    	// Villain rats you out for underpaying for your properties. you must pay an extra 15 for every property owned
    
    }
    
    public void taxFraud(){
    	//int i = GameState.getInstance().getCurrentPlayer().getBalance();
    	//i = i/15;
    	
    	//GameState.getInstance().getCurrentPlayer().withdraw(i);
    	
    	// you're caught for not paying the right amount of tax.
    	
    }
    
//    public void droppingMoney(){
//    	GameState.getInstance().getNextPlayer().withdraw(50);
//    	GameState.getInstance().getCurrentPlayer().deposit(50);
//    	
//    	// the player infront of you dropped 50 euro. you and HERO race for it but you get it first.
//    }
    
    
   public void taxMan(){
	   //  int i = GameState.getInstance().getCurrentPlayer().getBalance();
	   // i = i/10;
	   // GameState.getInstance().getCurrentPlayer().withdraw(i);
	   
	  // Villain comes to your house disguised as a taxman and scammed you.
   }
   
   public void interest(){
	   // int i = GameState.getInstance().getCurrentPlayer().getBalance();
	   // i = i/16;
	   // GameState.getInstance().getCurrentPlayer().deposit(i);
	  
	   // you gained interest on your savings from the bank
   }
   
   public void moneyBack(){
	   // GameState.getInstance().getCurrentPlayer().deposit(50);
	   // Hero gave you a little present for being a loyal fan
   }
   
   public void jail(){
	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(34);
	   // You hate villain because he is NEGATIVE, NEGATIVE, NEGATIVE. you get sent to jail for assaulting him.
	   
   }
   
   public void jail2(){
	   //GameState.getInstance().getCurrentPlayer().setPlayerLocation(34);
	   // you are caught stealing Villains vehicle. you get caught and sent to jail
   }
   
   public void game(){
	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(5);
	   
	   // it's been a long weekend so you and HERO go to play a game
   }
   
   public void game2(){
	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(29);
	   
	   // it's been a long weekend so you and Villain go to play a game
   }
   
   public void freeParking(){
	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(20);
	   
	   // You manage to find free parking before you meet up with HERO.
   }
   
   public void teleporting(){
	   // GameState.getInstance().getCurrentPlayer().setPlayerLocation(16);
	   
	   // HERO bring you in his vehicle to a secret location. You's find a teleporting device and decide to try it out
   }
   
   public void houseParty(){
	   // int i = GameState.getInstance().getCurrentPlayer().getNumProperties();
	   // GameState.getInstance().getCurrentPlayer().withdraw(i*25);
	   
	   // You host a house party in every property you own that week inviting people such as HERO,HERO,HERO,HERO. Some damages were made to all properties.
   }
   
   public void negativeSocialMedia(){
	   Random randomNumber = new Random();
       int i = randomNumber.nextInt(15)+1;

       // GameState.getInstance().getCurrentPlayer().move(i);
	   // you receive a lot of backlash for saying that HERO is NEGATIVE, NEGATIVE and NEGATIVE online and are chased on the streets until you find yourself somewhere safe.
   }
   
   public void wallet(){
	 //  GameState.getInstance().getCurrentPlayer().deposit(130);
	   // you see a villain doing his favourite activity. You manage to rob his wallet whilst he wasn't looking
   }
   
   public void smashedPhone(){
	 //  GameState.getInstance().getCurrentPlayer().withdraw(150);
	   // Villain smashes your new phone with this weapon of choice. You have to pay 150 to repair it
   }
   
   public void goToInsurance(){
	  // GameState.getInstance().getCurrentPlayer().setPlayerLocation(11);
	   
	   // Villain spread rumours you're a tax fraud so you head to the tax office.
   }
   
  public void pawnShop(){
	 // GameState.getInstance().getCurrentPlayer().withdraw(80);
	  
	  // you go to a pawn shop and end up seeing HEROS CLOTHING. you end up buying it.
  }
  
  public void freeParking1(){
	 // GameState.getInstance().getCurrentPlayer().withdraw(25);
	  
  }


   
    // Possible ideas for chance card //

    // - steal someones property.
    // - Destroy someones house(if they have one).
    // - Steal money from another player
    // - throw dice and pay 10x what you thrown,
}
